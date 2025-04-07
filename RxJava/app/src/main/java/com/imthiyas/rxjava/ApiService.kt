package com.imthiyas.rxjava

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    fun getProducts(): Observable<List<ProductItem>>
}