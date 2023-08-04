package com.example.maxmaven_assignment_gaurav.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maxmaven_assignment_gaurav.data.model.BookModel
import com.example.maxmaven_assignment_gaurav.databinding.ItemActivityMainBinding
import com.example.maxmaven_assignment_gaurav.utils.Util


class BookAdapter(private var onItemClicked: ((bookModel: BookModel) -> Unit)) :
    RecyclerView.Adapter<BookAdapter.DataViewHolder>() {

    val bookList: MutableList<BookModel> = mutableListOf()

    fun updateItems(list: List<BookModel>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

    inner class DataViewHolder(private val binding: ItemActivityMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookModel) {
            val position = absoluteAdapterPosition + 1
            if (position != 2 && Util.isPrimeNumber(position)) {
                binding.root.setBackgroundColor(Color.parseColor("#0000FF"))
            } else if (position % 2 == 0) {
                binding.root.setBackgroundColor(Color.parseColor("#00FF00"))
            } else {
                binding.root.setBackgroundColor(Color.parseColor("#FFA500"))
            }

            binding.tvTitle.text = book.title
            if (position % 2 == 0) {
                binding.tvTitle.text = book.title + "Even"
            } else {
                binding.tvTitle.text = book.title + "Odd"
            }
            binding.tvDescription.text = book.description
            binding.tvAuthors.text = book.authors
            Glide.with(binding.ivThumbnail.context)
                .load(book.thumbnail)
                .into(binding.ivThumbnail)

            binding.root.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com")
                )
                binding.root.context.startActivity(browserIntent)
            }

            binding.btnFavourite.setOnClickListener {
                onItemClicked(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        ItemActivityMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(bookList[position])


}