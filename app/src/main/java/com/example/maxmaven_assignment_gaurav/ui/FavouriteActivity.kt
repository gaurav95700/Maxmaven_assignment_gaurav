package com.example.maxmaven_assignment_gaurav.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maxmaven_assignment_gaurav.*
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import com.example.maxmaven_assignment_gaurav.databinding.ActivityFavouriteBinding
import com.example.maxmaven_assignment_gaurav.databinding.ActivityMainBinding
import com.example.maxmaven_assignment_gaurav.ui.adapter.BookAdapter
import com.example.maxmaven_assignment_gaurav.ui.adapter.FavouriteAdapter
import com.example.maxmaven_assignment_gaurav.ui.viewmodel.FavouriteBookViewModel
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var viewModel: FavouriteBookViewModel
    lateinit var favouriteAdapter: FavouriteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FavouriteBookViewModel::class.java]
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setUpObserver()
        viewModel.getFavouriteBookDataFromLocalDb()
    }

    private fun setupRecyclerView() {
        favouriteAdapter = FavouriteAdapter {

        }
        binding.rvSearchBook.apply {
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(this@FavouriteActivity)
            val deleteSwipeHandler = object : SwipeToDeleteCallback(this@FavouriteActivity) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val positionToDelete = viewHolder.absoluteAdapterPosition
                    if (positionToDelete != RecyclerView.NO_POSITION) {
                        val itemToDelete = favouriteAdapter.bookList.getOrNull(positionToDelete)
                        if (itemToDelete != null) {
                            viewModel.deleteBook(itemToDelete)
                        }
                        favouriteAdapter.bookList.removeAt(positionToDelete)
                        favouriteAdapter.notifyDataSetChanged()
                    }
                }

            }
            val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
            deleteItemTouchHelper.attachToRecyclerView(binding.rvSearchBook)
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@FavouriteActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(list: List<BookModel>) {
        favouriteAdapter.updateItems(list)
    }
}
