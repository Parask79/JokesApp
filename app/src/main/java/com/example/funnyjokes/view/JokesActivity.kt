package com.example.funnyjokes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.funnyjokes.databinding.ActivityJokesBinding
import com.example.funnyjokes.utils.NetworkResult
import com.example.funnyjokes.viewmodel.JokeViewModel
import kotlinx.coroutines.cancel
import org.koin.android.ext.android.inject


class JokesActivity : AppCompatActivity() {

    private val viewModel by inject<JokeViewModel>()

    private var _binding: ActivityJokesBinding? = null
    private val binding get() = _binding!!
    private lateinit var _adapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getJokes()
        initialiseAdapter()
        observeData()
    }

    private fun initialiseAdapter() {
        _adapter = JokeAdapter()
        binding.jokeList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.jokeList.adapter = _adapter
    }

    private fun observeData() {

        viewModel.liveData.observe(this) {
            binding.progressDialog.visibility = View.GONE
            when (it) {
                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {binding.progressDialog.visibility = View.VISIBLE}
                is NetworkResult.Success -> {
                    viewModel.liveDataDao.observe(this){jokesList ->
                        _adapter.submitList(jokesList)
                    }
                }
            }
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.coroutineScope.cancel()
    }
}