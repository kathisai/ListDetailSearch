package com.sample.simpsonsviewer.screens.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sample.simpsonsviewer.base.BaseViewModel
import com.sample.simpsonsviewer.models.RelatedTopic
import com.sample.simpsonsviewer.models.Simpsons
import com.sample.simpsonsviewer.network.NetworkAPI
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainListViewModel : BaseViewModel() {

    @Inject
    lateinit var netApi: NetworkAPI
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val resultData: MutableLiveData<List<RelatedTopic>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        subscription = netApi.fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { error -> onRetrievePostListError(error) }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(result: Simpsons) {
        resultData.postValue(result.RelatedTopics)
    }

    private fun onRetrievePostListError(error: Throwable) {
        Log.d("test", "onRetrievePostListError called " + error.message)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}