package com.example.vlad.art_coral_test

import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.ERROR
import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.SUCCESS
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.LOADING
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.NOT_LOADING
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.vlad.art_coral_test.MainActivity.AuthA.UserName
import com.example.vlad.art_coral_test.MainActivity.AuthA.UserPassword
import com.example.vlad.art_coral_test.R.id.emailA

import com.example.vlad.art_coral_test.R.id.textViewTest
import com.example.vlad.art_coral_test.model.Authorization
import com.example.vlad.art_coral_test.model.Dino
import com.example.vlad.art_coral_test.model.DinoDetails
import com.example.vlad.art_coral_test.toast
import com.example.vlad.art_coral_test.model.RegistrationBody
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {


    companion object Auth {
        private lateinit var rBody: RegistrationBody

        fun logPass (username:String,password:String) {
           /* if(!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)){
            */

            rBody = RegistrationBody(username, password)
           // getBody(rBody)
          //  }
           // else
        }
      /*  private fun getBody(a:RegistrationBody){
            rBody = a
        }
*/
        lateinit var HeadAuth : Authorization

        const val X_CSRF_Token:String = "X-CSRF-Token:"
        const val Cookie :String = "Cookie:"

    }

    enum class LoadingStatus { LOADING, NOT_LOADING }

    enum class InstallationStatus { SUCCESS, ERROR }

    private val getTokenL  by lazy {
        ApiServiceFactory.createService()

    }
    private val getData by lazy {

      //  val rBody = RegistrationBody(userName , userPasssword)
        getTokenL.loginWithCredentials(rBody)
    }

    private val AuthWhithToken by lazy{
//HeadAuth.X_CSRF_Token,
       // HeadAuth.Cookie
        getTokenL.getDinos(HeadAuth.X_CSRF_Token,HeadAuth.Cookie)
           // .map { it }
    }


    private val _resultToken = MutableLiveData<String>()
    val resultToken: LiveData<String> = _resultToken


        //LoadingStatus
    private val loadStatus = MutableLiveData<LoadingStatus>()
    val sendLoadStatus: LiveData<LoadingStatus> = loadStatus

    private val _installation = MutableLiveData<InstallationStatus>()
    val installation: LiveData<InstallationStatus> = _installation

    private val _sendDataToDino = MutableLiveData<List<Dino>>()
    val sendDataToDino:LiveData<List<Dino>> = _sendDataToDino

    private val _resultDinoData = MutableLiveData<InstallationStatus>()
    val resultDinoData:LiveData<InstallationStatus> = _resultDinoData

    private val _ExError = MutableLiveData<String>()
    val ExError: LiveData<String> = _ExError

    fun getDinoData() = AuthWhithToken
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .doOnSubscribe{ /*do something*/ }
        .doFinally { /* doSomething */ }
        .subscribeBy(
            onSuccess = {//_sendDataToDino.postValue(it.subList(1,10))
                _resultDinoData.postValue(SUCCESS)
                _sendDataToDino.postValue(it.dinos)},

            onError = {_ExError.postValue(it.message)
                _resultDinoData.postValue(ERROR)}
        )

 /*   fun destroy() = getData
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .doOnSubscribe { loadStatus.postValue(LOADING) }
        .doFinally { loadStatus.postValue(NOT_LOADING) }
        .subscribeBy(
            onSuccess = {_resultToken.postValue(it.user.name)
                _installation.postValue(SUCCESS)
            HeadAuth = Authorization(it.token,it.sessionName,it.sessid)},
            //  onError = { }
            onError = {_resultToken.postValue(it.message)
                _installation.postValue(ERROR)
            }
        )
        */
    fun getToken() = getData
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .doOnSubscribe { loadStatus.postValue(LOADING) }
        .doFinally { loadStatus.postValue(NOT_LOADING) }
        .subscribeBy(
            onSuccess = {_resultToken.postValue(it.user.name)
                _installation.postValue(SUCCESS)
                HeadAuth = Authorization(it.token,it.sessionName,it.sessid)},
            //  onError = { }
            onError = {_resultToken.postValue(it.message)
                _installation.postValue(ERROR)
            }
        )



}