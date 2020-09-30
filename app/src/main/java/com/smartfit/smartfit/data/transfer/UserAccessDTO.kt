package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserAccess

data class UserAccessDTO(
    var accessToken: String,
    var refreshToken: String,
    var expiryTime: String
) {
    companion object {
        fun mapToUserAccess(ua: UserAccessDTO): UserAccess {
            return UserAccess(
                id = 1,
                accessToken = ua.accessToken,
                refreshToken = ua.refreshToken,
                expiryTime = ua.expiryTime
            )
        }
    }
}