package com.cookie.note.domain.result

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface Result<out D, out E: DomainError> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Failure<out E: DomainError>(val error: E) : Result<Nothing, E>
}

fun <D> Result<D, *>.getOrNull(): D? = (this as? Result.Success)?.data
fun <E: DomainError> Result<*, E>.getErrorOrNull(): DomainError? = (this as? Result.Failure)?.error
fun <D, E: DomainError> Result<D, E>.isSuccess(): Boolean = this is Result.Success<D>
fun <D, E: DomainError> Result<D, E>.isFailure(): Boolean = this is Result.Failure

@OptIn(ExperimentalContracts::class)
inline fun <D, E: DomainError> Result<D, E>.onSuccess(action: (data: D) -> Unit): Result<D, E> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Result.Success) action(data)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <D, E: DomainError> Result<D, E>.onFailure(action: (error: E) -> Unit): Result<D, E> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is Result.Failure) action(error)
    return this
}