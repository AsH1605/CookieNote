package com.cookie.note.domain.result

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface Result<out D> {
    data class Success<out D>(val data: D) : Result<D>
    data class Failure(val error: String) : Result<Nothing>
}

fun <D> Result<D>.getOrNull(): D? = (this as? Result.Success)?.data
fun Result<*>.getErrorOrNull(): String? = (this as? Result.Failure)?.error
fun <D> Result<D>.isSuccess(): Boolean = this is Result.Success<D>
fun <D> Result<D>.isFailure(): Boolean = this is Result.Failure

@OptIn(ExperimentalContracts::class)
inline fun <D> Result<D>.onSuccess(action: (data: D) -> Unit): Result<D> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Result.Success) action(data)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <D> Result<D>.onFailure(action: (error: String) -> Unit): Result<D> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Result.Failure) action(error)
    return this
}