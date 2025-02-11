package com.cookie.note.data.manager

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
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

//TODO: Use actual location service from Google Playstore
class LocationManagerImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteLocationManager {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getLocation(): NoteLocation? {
        return NoteLocation(21.128686, 81.765711, "IIIT Naya Raipur")
    }

    @SuppressLint("MissingPermission")
    private suspend fun userAddressFromLocationService(): Address? {
        val cancellationTokenSource = CancellationTokenSource()
        return try {
            withTimeout(TIMEOUT_MS) {
                val location: Location? = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
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
                            continuation.resume(addresses.firstOrNull())
                        }
                    } else {
                        val addresses = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        continuation.resume(addresses?.firstOrNull())
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
        const val TIMEOUT_MS = 60_000L
    }
}