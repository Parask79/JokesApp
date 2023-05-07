package com.example.funnyjokes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.funnyjokes.databinding.JokeItemBinding
import com.example.funnyjokes.models.JokesEntity


class JokeAdapter : ListAdapter<JokesEntity, JokeAdapter.JokeViewHolder>(ComparatorDiffUtil()) {

    inner class JokeViewHolder(private val binding: JokeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(jokes: JokesEntity) {
            binding.desc.text = jokes.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
       val joke = getItem(position)
        joke?.let {
           holder.bind(it)
        }
    }


    class ComparatorDiffUtil : DiffUtil.ItemCallback<JokesEntity>() {
        override fun areContentsTheSame(oldItem: JokesEntity, newItem: JokesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: JokesEntity, newItem: JokesEntity): Boolean {
            return oldItem.joke == newItem.joke
        }

    }
}