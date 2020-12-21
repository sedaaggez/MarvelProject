package com.sedaaggez.marvelproject.service

import com.sedaaggez.marvelproject.model.character.CharacterMarvelData
import com.sedaaggez.marvelproject.model.comic.ComicMarvelData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    // http://gateway.marvel.com/
    // v1/public/characters?apikey=""&hash=""&ts=0

    @GET("v1/public/characters")
    fun getMarvelDataCharacter(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<CharacterMarvelData>


    @GET("v1/public/characters/{characterId}/comics")
    fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("dateRange") dateRange: String,
        @Query("limit") limit: Int
    ): Single<ComicMarvelData>
}