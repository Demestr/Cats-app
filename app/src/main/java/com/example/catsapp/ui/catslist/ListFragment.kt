package com.example.catsapp.ui.catslist

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsapp.CatApplication
import com.example.catsapp.R
import com.example.catsapp.model.Cat
import com.example.catsapp.ui.adapters.CatsListAdapter
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

const val WRITE_PERMISSION = 100

class ListFragment : Fragment(), CatsListAdapter.CardClickListener {

    @Inject lateinit var viewModelFactory: ListViewModelFactory
    private val catsListAdapter = CatsListAdapter(this)

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CatApplication).appComponent.catListComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_cats.layoutManager = LinearLayoutManager(context)
        recycler_cats.adapter = catsListAdapter

        viewModel.pagedLiveData.observe(viewLifecycleOwner, Observer {
            catsListAdapter.submitList(it)
        })
    }

    override fun onFavoriteButtonClick(cat: Cat) {
        if(!cat.isExistInRoom)
            viewModel.insertCat(cat)
        else
            viewModel.deleteCat(cat)
    }

     override fun onDownloadButtonClick(cat: Cat) {
         hasStoragePermission()
         viewModel.savePicturesInDownloads(cat)
    }

    private fun hasStoragePermission() : Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_PERMISSION)
                false;
            } else {
                true;
            }
        } else {
            true;
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_PERMISSION) {
            if (grantResults.size == 1 &&
                grantResults[0] == PermissionChecker.PERMISSION_GRANTED
            ) {
                Toast.makeText(context, "Разрешение получено, можно сохранять котиков", Toast.LENGTH_LONG).show()
            } else {
                hasStoragePermission()
            }
        }
    }
}