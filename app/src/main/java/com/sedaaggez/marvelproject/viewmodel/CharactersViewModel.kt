package com.sedaaggez.marvelproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sedaaggez.marvelproject.model.character.CharacterMarvelData
import com.sedaaggez.marvelproject.model.character.CharacterResult
import com.sedaaggez.marvelproject.service.MarvelAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : ViewModel() {
    private val marvelAPIService = MarvelAPIService()
    private val disposable = CompositeDisposable()

    val results = MutableLiveData<List<CharacterResult>>()
    val total = MutableLiveData<Int>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getMarvelDataCharacter(apiKey: String, hash: String, ts: Long, offset: Int, limit: Int) {
        getDataFromAPI(apiKey, hash, ts, offset, limit)
    }

    private fun getDataFromAPI(apiKey: String, hash: String, ts: Long, offset: Int, limit: Int) {
        disposable.add(
            marvelAPIService.getMarvelDataCharacter(apiKey, hash, ts, offset, limit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CharacterMarvelData>() {
                    override fun onSuccess(t: CharacterMarvelData) {
                        total.value = t.data?.total
                        results.value = t.data?.results
                        error.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                        loading.value = false
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