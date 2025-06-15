package com.mohamedabdelaziz.feature.home.core.api

import com.mohamedabdelaziz.feature.home.data.remote.dto.GetHomeSectionsResponseDto
import com.mohamedabdelaziz.feature.home.data.remote.dto.GetSearchSectionsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface HomeApiService {
    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int = 1
    ): Response<GetHomeSectionsResponseDto>
    @GET("https://mock.apidog.com/m1/735111-711675-default/search")
    suspend fun search(@Query("q") query: String): Response<GetSearchSectionsResponseDto>
}
