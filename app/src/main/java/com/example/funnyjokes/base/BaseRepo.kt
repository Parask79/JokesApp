package com.example.funnyjokes.base


import android.util.Log
import com.example.funnyjokes.utils.NetworkResult

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo {
    suspend fun <T> safeCall(
        dispatchers: CoroutineDispatcher,
        apiToCall: suspend () -> Response<T>
    ): NetworkResult<T> {

        return withContext(dispatchers)
        {
            try {
                val response: Response<T> = apiToCall()
                if (response.isSuccessful && response.body() != null) {
                    NetworkResult.Success(response.body()!!)
                } else  {
                    NetworkResult.Error(message = "Something Went wrong")
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        NetworkResult.Error(message = "IO Exception")
                    }
                    is HttpException -> {
                        NetworkResult.Error(message = "Http Exception")
                    }
                    else -> {
                        NetworkResult.Error(message = "Some thing went wrong")
                    }
                }

            }

        }


    }
}