package com.dev.hnxtay.androidnestedrecyclerview.recyclerview.adapters

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.hnxtay.androidnestedrecyclerview.databinding.ParentItemBinding
import com.dev.hnxtay.androidnestedrecyclerview.models.ChildItem
import com.dev.hnxtay.androidnestedrecyclerview.models.ParentItem

class ParentAdapter(
    var items: List<ParentItem>,
    private val onChildItemClickListener: (ChildItem) -> Unit,
    private val onParentItemClickListener: (ParentItem) -> Unit
) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    private val scrollStates = mutableMapOf<Int, Parcelable?>()

    class ParentViewHolder(
        private val binding: ParentItemBinding,
        private val onChildItemClickListener: (ChildItem) -> Unit,
        private val onParentItemClickListener: (ParentItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val recyclerView = binding.recyclerView

        companion object {
            fun createViewHolder(
                parent: ViewGroup,
                onChildItemClickListener: (ChildItem) -> Unit,
                onParentItemClickListener: (ParentItem) -> Unit
            ): ParentViewHolder {
                return ParentViewHolder(
                    ParentItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onChildItemClickListener, onParentItemClickListener
                )
            }
        }

        fun bind(item: ParentItem) {
            with(binding) {
                tvTitle.text = item.title
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    adapter = ChildAdapter(item.items) {
                        onChildItemClickListener(it)
                    }
                    setHasFixedSize(true)
                }
                tvTitle.setOnClickListener {
                    onParentItemClickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder.createViewHolder(
            parent,
            onChildItemClickListener,
            onParentItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(items[position])
        val key = holder.layoutPosition
        val state = scrollStates[key]
        if (state != null) {
            holder.recyclerView.layoutManager?.onRestoreInstanceState(state)
        } else {
            holder.recyclerView.layoutManager?.scrollToPosition(0)
        }
    }

    override fun getItemCount() = items.size

    override fun onViewRecycled(holder: ParentViewHolder) {
        super.onViewRecycled(holder)
        val key = holder.layoutPosition
        scrollStates[key] = holder.recyclerView.layoutManager?.onSaveInstanceState()
    }
}