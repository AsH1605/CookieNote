package com.cookie.note.data.manager

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.util.Log
import com.cookie.note.domain.managers.NoteLocationManager
import com.cookie.note.domain.models.NoteLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import java.io.IOException
import java.util.Locale
import kotlin.coroutines.resume

class LocationManagerImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteLocationManager {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getLocation(): NoteLocation? {
        try {
            return userAddressFromLocationService()
        }catch (e: Exception){
            Log.e(TAG, e.message.toString(), e)
            return null
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun userAddressFromLocationService(): NoteLocation? {
        val cancellationTokenSource = CancellationTokenSource()
        return try {
            withTimeout(TIMEOUT_MS) {
                val location: Location? = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_LOW_POWER,
                    object : CancellationToken() {
                        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                            return cancellationTokenSource.token
                        }

                        override fun isCancellationRequested(): Boolean {
                            return false
                        }
                    }
                ).await()
                if (location == null) {
                    return@withTimeout null
                }
                val geocoder = Geocoder(context, Locale.getDefault())
                suspendCancellableCoroutine { continuation ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        ) { addresses ->
                            val address = addresses.firstOrNull()
                            val noteLocation = NoteLocation(
                                location.latitude,
                                location.longitude,
                                address?.locality
                            )
                            continuation.resume(noteLocation)
                        }
                    } else {
                        val addresses = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        val address = addresses?.firstOrNull()
                        val noteLocation = NoteLocation(
                            location.latitude,
                            location.longitude,
                            address?.locality
                        )
                        continuation.resume(noteLocation)
                    }
                }
            }
        } catch (e: IOException) {
            null
        } catch (e: TimeoutCancellationException) {
            cancellationTokenSource.cancel()
            null
        }
    }

    companion object {
        const val TIMEOUT_MS = 6_000L
        const val TAG = "LocationManagerImpl"
    }
}