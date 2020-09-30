package com.smartfit.smartfit.data.transfer

import com.smartfit.smartfit.data.entity.UserProfile

data class UserProfileDTO(
    var name: String,
    var imageUrl: String,
    var weight: Int,
    var height: Int,
    var age: Int,
    var gender: Boolean,
    var goal: String
) {
    companion object {
        fun mapToUserProfile(up: UserProfileDTO): UserProfile {
            return UserProfile(
                id = 1,
                name = up.name,
                imageUrl = up.imageUrl,
                weight = up.weight,
                height = up.height,
                age = up.age,
                gender = up.gender,
                goal = up.goal
            )
        }
    }
}