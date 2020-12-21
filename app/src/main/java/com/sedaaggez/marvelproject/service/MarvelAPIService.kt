package com.sedaaggez.marvelproject.service

import com.sedaaggez.marvelproject.model.character.CharacterMarvelData
import com.sedaaggez.marvelproject.model.comic.ComicMarvelData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelAPIService {

    private val BASE_URL = "http://gateway.marvel.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MarvelAPI::class.java)

    fun getMarvelDataCharacter(
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int,
        limit: Int
    ): Single<CharacterMarvelData> {
        return api.getMarvelDataCharacter(apiKey, hash, ts, offset, limit)
    }

    fun getCharacterComics(
        characterId: Int,
        apiKey: String,
        hash: String,
        ts: Long,
        dateRange: String,
        limit: Int
    ): Single<ComicMarvelData> {
        return api.getCharacterComics(characterId, apiKey, hash, ts, dateRange, limit)
    }

}