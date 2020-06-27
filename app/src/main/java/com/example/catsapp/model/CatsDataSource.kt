package com.example.catsapp.model

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.catsapp.network.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CatsDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Cat>() {

    private val api = ApiFactory.createApi()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Cat>
    ) {
        scope.launch {
            try {
                val response = api.getPageAsync(params.requestedLoadSize, 0)
                val cats = response.await()
                callback.onResult(
                    cats.orEmpty(),
                    null,
                    1
                )
            } catch (exception: Exception) {
                Log.e("CatsDataSource", "Failed to fetch data!")
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) {
        scope.launch {
            try {
                val response = api.getPageAsync(params.requestedLoadSize, params.key)
                val cats = response.await()
                callback.onResult(
                    cats.orEmpty(),
                    params.key.inc()
                )
            } catch (exception: Exception) {
                Log.e("CatsDataSource", "Failed to fetch data!")
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) {
        scope.launch {
            try {
                val response = api.getPageAsync(params.requestedLoadSize, params.key)
                val cats = response.await()
                callback.onResult(
                    cats.orEmpty(),
                    params.key.dec()
                )
            } catch (exception: Exception) {
                Log.e("CatsDataSource", "Failed to fetch data!")
            }

        }
    }
}