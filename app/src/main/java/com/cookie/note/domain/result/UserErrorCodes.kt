package com.cookie.note.domain.result

enum class UserErrorCodes(
    val code: Int
) {
    USER_DOES_NOT_EXIST ( 600), // User does not exist in the database), needs register
    USER_ALREADY_EXISTS ( 601), // User with username/email already exists in the database), register not required
    USER_WRONG_PASSWORD ( 602), // Provided password does not match the stored password
    USER_INVALID_EMAIL ( 603), // email does not match required format
    USER_INVALID_PASSWORD ( 604),  // password does not match required format
    MISSING_USER_ID_IN_HEADER ( 605), // User_id is missing in the request headers
    UNKNOWN_USER_ERROR ( 699), // Unknown error related to userUSER_DOES_NOT_EXIST ( 600), // User does not exist in the database), needs register
}