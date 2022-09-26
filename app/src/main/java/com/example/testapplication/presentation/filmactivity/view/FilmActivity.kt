package com.example.testapplication.presentation.filmactivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityFilmBinding
import com.example.testapplication.network.remotedatasource.data.Result
import com.example.testapplication.presentation.filmactivity.adapter.FilmActivityAdapter
import com.example.testapplication.presentation.filmactivity.viewmodel.FilmActivityViewModel


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {


    private var _binding: ActivityFilmBinding? = null
    private val binding: ActivityFilmBinding
        get() = _binding!!

    private val viewModel: FilmActivityViewModel by viewModels()
    private var adapter: FilmActivityAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        collectStateUi()

    }

    private fun initAdapter() {
        adapter = FilmActivityAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun collectStateUi() {
        lifecycleScope.launch {
            viewModel.getMovies()
                .collectLatest { Result ->
                    adapter?.submitData(lifecycle, Result)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter = null
    }

}



