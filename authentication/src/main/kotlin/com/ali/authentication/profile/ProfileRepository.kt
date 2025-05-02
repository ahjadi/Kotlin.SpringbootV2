package com.ali.authentication.profile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<ProfileEntity, Long> {
    fun findByUserId(userId: Long): ProfileEntity?
}