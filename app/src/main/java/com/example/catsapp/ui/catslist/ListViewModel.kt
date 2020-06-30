package com.example.catsapp.ui.catslist

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.example.catsapp.database.CatsDao
import com.example.catsapp.model.Cat
import com.example.catsapp.model.CatsFetcher
import com.example.catsapp.network.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val context: Context,
    val api: ServiceApi,
    private val dao: CatsDao) : ViewModel() {

    val pagedLiveData: LiveData<PagedList<Cat>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        pagedLiveData = initializedPagedListBuilder(config).build()
    }

    fun insertCat(cat: Cat) {
        viewModelScope.launch {
            cat.isExistInRoom = !cat.isExistInRoom
            cat.url = savePictureInDB(cat)
            dao.insertAll(cat)
        }
    }

    fun deleteCat(cat: Cat) {
        viewModelScope.launch {
            cat.isExistInRoom = !cat.isExistInRoom
            dao.deleteCat(cat)
            deletePictureInDB(cat)
        }
    }


    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Cat> {

        val dataSourceFactory = object : DataSource.Factory<Int, Cat>() {
            override fun create(): DataSource<Int, Cat> {
                return CatsFetcher(viewModelScope, api)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    private fun savePictureInDB(cat: Cat): String = runBlocking(Dispatchers.IO) {
        val bitmap = Glide.with(context).asBitmap().load(cat.url).submit().get()
        val storage: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val fname = "Image-" + Calendar.getInstance().time.time + cat.url.takeLast(4)
        val file = File(storage, fname)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            return@runBlocking file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            return@runBlocking ""
        }
    }

    private fun deletePictureInDB(cat: Cat) {
        val image = File(cat.url)
        if (image.exists()) image.delete()
    }

    fun savePicturesInDownloads(cat: Cat?) {

        viewModelScope.launch(Dispatchers.Main) {
            val queue = async(Dispatchers.IO) {
                val bitmap = Glide.with(context).asBitmap().load(cat?.url).submit().get()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    //TODO сделать
                    //val downloads = MediaStore.Downloads.getContentUri(MediaStore.Downloads.VOLUME_NAME)
                } else {
                    val downloads =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val fname = "Image-" + Calendar.getInstance().time.time + ".jpg"
                    val file = File(downloads, fname)
                    if (file.exists()) file.delete()
                    try {
                        val out = FileOutputStream(file)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                        out.flush()
                        out.close()

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            queue.await()
            Toast.makeText(context, "Изображение сохранено", Toast.LENGTH_SHORT).show()
        }


    }
}