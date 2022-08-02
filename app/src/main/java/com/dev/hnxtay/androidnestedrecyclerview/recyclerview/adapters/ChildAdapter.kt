package com.dev.hnxtay.androidnestedrecyclerview.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.hnxtay.androidnestedrecyclerview.databinding.ChildItemBinding
import com.dev.hnxtay.androidnestedrecyclerview.models.ChildItem

class ChildAdapter(
    var data: List<ChildItem>,
    private val onChildItemClickListener: (ChildItem) -> Unit
) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(
        private val binding: ChildItemBinding,
        private val onChildItemClickListener: (ChildItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun createViewHolder(
                parent: ViewGroup,
                onChildItemClickListener: (ChildItem) -> Unit
            ): ChildViewHolder {
                return ChildViewHolder(
                    ChildItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ),
                        parent, false
                    ), onChildItemClickListener
                )
            }
        }

        fun bind(item: ChildItem, position: Int) {
            with(binding) {
                tvNumber.text = (position + 1).toString()
                root.setOnClickListener {
                    onChildItemClickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChildViewHolder.createViewHolder(parent, onChildItemClickListener)

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount() = data.size

}