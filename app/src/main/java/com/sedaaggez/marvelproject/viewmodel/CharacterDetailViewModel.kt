package com.sedaaggez.marvelproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedaaggez.marvelproject.common.COMIC_LIMIT
import com.sedaaggez.marvelproject.model.comic.ComicMarvelData
import com.sedaaggez.marvelproject.model.comic.ComicResult
import com.sedaaggez.marvelproject.service.MarvelAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : ViewModel() {
    private val marvelAPIService = MarvelAPIService()
    private val disposable = CompositeDisposable()
    val comicResults = MutableLiveData<List<ComicResult>>()

    fun getCharacterComics(
        characterId: Int,
        apiKey: String,
        hash: String,
        ts: Long,
        dateRange: String,
        limit: Int
    ) {
        disposable.add(
            marvelAPIService.getCharacterComics(
                characterId,
                apiKey,
                hash,
                ts,
                dateRange,
                limit
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ComicMarvelData>() {
                    override fun onSuccess(t: ComicMarvelData) {
                        comicResults.value = t.data?.results
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}