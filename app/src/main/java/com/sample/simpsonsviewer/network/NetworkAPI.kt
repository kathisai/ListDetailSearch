package com.sample.simpsonsviewer.network

import SEARCH
import com.sample.simpsonsviewer.models.Simpsons
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkAPI {

    @GET(SEARCH)
    fun fetchData(): Observable<Simpsons>
}