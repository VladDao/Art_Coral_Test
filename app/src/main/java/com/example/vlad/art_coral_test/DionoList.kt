package com.example.vlad.art_coral_test

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.vlad.art_coral_test.adapter.RecyclerAdapter
import com.example.vlad.art_coral_test.model.Dino
import android.arch.lifecycle.Observer
import android.view.View
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.LOADING
import com.example.vlad.art_coral_test.MainViewModel.LoadingStatus.NOT_LOADING
import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.SUCCESS
import com.example.vlad.art_coral_test.MainViewModel.InstallationStatus.ERROR

import kotlinx.android.synthetic.main.activity_diono_list.*

class DionoList : AppCompatActivity() {

    private val DinoViewModel by lazy {
        viewModel{ MainViewModel() }
    }
    internal var recyclerView:RecyclerView? = null
    internal var posts: MutableList<Dino>? = null

    internal lateinit var responseDino:List<Dino>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diono_list)

        observeViewModel()
        setSupportActionBar(toolbar)
      //  try{
         /*dino_Content_DinoList.setOnClickListener {

            getDino(dino_Content_DinoList)
        }*/
      /*  }catch (e:Exception){
            Snackbar.make(dino_Content_DinoList, "try again", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        fab.setOnClickListener { view ->
            val intent = Intent(this, AddDinoContent::class.java)
            this.startActivity(intent)
            getDino(fab)
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        recyclerView = findViewById(R.id.posts_recycle_view)
        }

    }
/*
    override fun onStart() {
        super.onStart()
        getDino()

    }
*/

    fun getDino(view: View) = DinoViewModel.getDinoData()
    private fun observeViewModel (){
        DinoViewModel.ExError.observe(this, android.arch.lifecycle.Observer {
            textMessage.text = it?: EMPTY_STRING
        })
        DinoViewModel.sendDataToDino.observe(this, android.arch.lifecycle.Observer { it ->
            val a:List<Dino> = it!!
           // textMessage.text = it.dinoAbout
            val layoutManager = LinearLayoutManager(this)
            recyclerView!!.layoutManager = layoutManager

            val adapter = RecyclerAdapter(this,a)
            recyclerView!!.adapter = adapter

            //textMessage.text = a.get(0).dino!!.dinoAbout
            //textMessage.text = it?: EMPTY_STRING
        })


        DinoViewModel.resultDinoData.observe(this, android.arch.lifecycle.Observer {
            dinoTest.text = it?.toString() ?: EMPTY_STRING
        })
        DinoViewModel.installation.observe(this, Observer {
            when(it) {
                MainViewModel.InstallationStatus.SUCCESS -> {
                    recyclerView?.let { it -> getDino(it) }
                }
                MainViewModel.InstallationStatus.ERROR -> {   }
            }
        })

      /*  DinoViewModel.sendDataToDino.observe(this, Observer{
            responseDino =

            dinoTest.text = it?.  :EMPTY_STRING
        })*/
    }

    companion object AuthA{
        private const val EMPTY_STRING = ""


    }

}
