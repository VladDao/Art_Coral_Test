package com.example.vlad.art_coral_test

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.LOADING
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.NOT_LOADING
import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.SUCCESS
import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.ERROR
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var stateError:Boolean = false


    private val viewModel by lazy {
        viewModel{ MainViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeViewModel()

        val logIn = findViewById<Button>(R.id.ButLogIn)
        logIn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailA)
            val password = findViewById<EditText>(R.id.passwordA)

            UserName = email.text.toString()

            textViewTest.text = UserName
            UserPassword = password.text.toString()

            MainViewModel.logPass(UserName, UserPassword)


            a(logIn)

        }


    }



    var a = if(stateError)
    {fun(view:View) = viewModel.destroy()}
    else {fun(view:View) = viewModel.getToken()}


    private fun observeViewModel(){

        viewModel.sendLoadStatus.observe(this, Observer{
            when(it){
                LOADING -> {
                    loader_single_joke.show()
                    ButLogIn.disable()
                }
                NOT_LOADING ->{
                    loader_single_joke.hide()
                    ButLogIn.enable()
                }
            }

        })
        viewModel.resultToken.observe(this, Observer {
            textViewTest.text = it ?: EMPTY_STRING
            fun getTokenStart(view: View) = viewModel.destroy()
        })

        viewModel.installation.observe(this, Observer {
            when(it) {
                SUCCESS -> toast("Installation succeed")
                ERROR -> {  stateError = true}
            }
        })

    }



    companion object AuthA{
        private const val EMPTY_STRING = ""

         var UserName = ""
        var UserPassword = ""
//        UserPassword

    }

}


