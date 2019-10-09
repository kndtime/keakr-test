package com.example.keakr.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.keakr.data.model.Response
import com.example.keakr.data.remote.RemoteContract
import com.example.keakr.di.KeakrModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val km: KeakrModule) : RepositoryInterface{

    private val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun get_user_profile(userId: String, take: Int): LiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = km.providePostApi(km.provideRetrofitInterface())
            .get_user_profile(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.user != null) {
                    mutableLiveData.value = res
                } else {
                    throw Throwable("Repository -> on Error occurred - ${object{}.javaClass.enclosingMethod?.name}")
                }
            }, { t: Throwable? -> t?.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun get_user_beats(userdId: String, take: Int): LiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = km.providePostApi(km.provideRetrofitInterface())
            .get_user_beats(userdId, take)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.items.isNotEmpty()) {
                    mutableLiveData.value = res
                } else {
                    throw Throwable("Repository -> on Error occurred - ${object{}.javaClass.enclosingMethod?.name}")
                }
            }, { t: Throwable? -> t?.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }
}