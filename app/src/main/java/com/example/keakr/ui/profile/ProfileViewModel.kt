package com.example.keakr.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.keakr.data.model.Response
import com.example.keakr.data.remote.RemoteContract
import com.example.keakr.ui.common.BaseViewModel

class ProfileViewModel : BaseViewModel() {
    var liveResponseData : MutableLiveData<Response> = MutableLiveData()

    fun get_profile(page : Int){
        symplRepository.get_user_profile(RemoteContract.USER_ID.toString(), page).observeForever(object : Observer<Response> {
            override fun onChanged(someData: Response?) {
                // do something with someData
                if (someData == null)
                    return
                liveResponseData.value = someData
            }
        })
    }

    fun get_keaks(page : Int){
        symplRepository.get_user_beats(RemoteContract.USER_ID.toString(), page).observeForever(object : Observer<Response> {
            override fun onChanged(someData: Response?) {
                // do something with someData
                if (someData == null)
                    return
                liveResponseData.value = someData
            }
        })
    }

    fun get_response() : LiveData<Response>{
        return liveResponseData
    }
}
