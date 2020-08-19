package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import com.example.retrofitdemo.MainViewModel

import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    val viewModel: MainViewModel by inject<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        getRequestWithQueryParameters()
        //getRequestWithPathParameters()
        //uploadAlbum()
        val student:Student = get<Student>()
        student.beSmart()

        val student2: Student = get<Student>()
        student2.beSmart()

        //val viewModel: MainViewModel=get<MainViewModel>()
        //viewModel.performAction()
        doSomething()
    }
    fun doSomething(){
        viewModel.performAction()
    }

    private fun getRequestWithQueryParameters(){
        val responseLiveData:LiveData<Response<Albums>>  = liveData {
            val response:Response<Albums> = retService.getSortedAlbums(3)//be careful while choosing from multiple choice
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumsItem = albumList.next()
                    //Log.i("MyTag", albumItem.title)
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    text_view.text = result
                }

            }

        })

    }
    private fun getRequestWithPathParameters(){
        val pathResponse:LiveData<Response<AlbumsItem>>  = liveData {
            val response:Response<AlbumsItem> = retService.getAlbum(3)//be careful while choosing from multiple choice
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()

        })

    }
    private fun uploadAlbum() {
        val album = AlbumsItem(0,"My Title",3)
        val postResponse :LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)


        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result= " "+ "Album Title : ${receivedAlbumsItem?.title} "+"\n" +
                    " "+"Album Id : ${receivedAlbumsItem?.id}"+"\n"
            " "+" Album UserId : ${receivedAlbumsItem?.userId}"+"\n\n\n"
            text_view.text =result
        })
    }
}
