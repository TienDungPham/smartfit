package com.smartfit.smartfit.data.source

import com.smartfit.smartfit.data.source.local.LocalDataSource
import com.smartfit.smartfit.data.source.remote.RemoteDataSource
import com.smartfit.smartfit.data.transfer.UserAccessDTO
import com.smartfit.smartfit.data.transfer.up.SignIn
import com.smartfit.smartfit.data.transfer.up.SignUp
import com.smartfit.smartfit.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*

class AppRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun isUserSignIn(): Boolean = withContext(dispatcher) {
        val localUserAccess = localDataSource.findUserAccess() ?: return@withContext false
        try {
            val expiryTime = Utils.parseDate(localUserAccess.expiryTime)
            if (expiryTime!!.before(Date())) {
                val remoteUserAccess = remoteDataSource
                    .refreshToken(localUserAccess.refreshToken)
                localDataSource
                    .saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } else {
                return@withContext true
            }
        } catch (e: HttpException) {
            return@withContext false
        }
    }

    suspend fun signIn(signIn: SignIn): Boolean =
        withContext(dispatcher) {
            try {
                val remoteUserAccess = remoteDataSource.signIn(signIn)
                localDataSource.saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } catch (e: HttpException) {

                return@withContext false
            }
        }

    suspend fun signUp(signUp: SignUp): Boolean =
        withContext(dispatcher) {
            try {
                val remoteUserAccess = remoteDataSource.signUp(signUp)
                localDataSource.saveUserAccess(UserAccessDTO.mapToUserAccess(remoteUserAccess))
                return@withContext true
            } catch (e: HttpException) {
                return@withContext false
            }
        }
}