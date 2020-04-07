package com.marvelapp.br.sdk

import com.marvelapp.br.sdk.response.PersonagensResponse
import com.marvelapp.br.sdk.response.comic.PersonagemDetalheResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("v1/public/characters?ts=1&orderBy=name&limit=10&apikey=1e7d31b8cbf2b3f2b3af61c8b2228bd9&hash=cab3a9d3ab5aa1640a6e677ab91234ed")
    fun getPersonagens(@Query("offset") offset: Int = 0) : Deferred<Response<PersonagensResponse>>

    @GET("v1/public/characters/{characterId}/comics?ts=1&&apikey=1e7d31b8cbf2b3f2b3af61c8b2228bd9&hash=cab3a9d3ab5aa1640a6e677ab91234ed")
    fun getPersonagem(@Path("characterId") characterId: Int) : Deferred<Response<PersonagemDetalheResponse>>
}