package com.hsilva.myshoppinglist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.hsilva.myshoppinglist.R
import com.hsilva.myshoppinglist.service.dto.Item
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
class ShoppingListAdapter(
    context: Context,
    private val items: List<Item>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: CheckedTextView
        var itemRemoveButton: ExtendedFloatingActionButton

        init {
            item = itemView.findViewById(R.id.item)
            itemRemoveButton = itemView.findViewById(R.id.button_remove_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        holder.item.text = currentItem.name
        holder.item.isChecked = currentItem.isChecked == true
        holder.item.setOnClickListener {
            val item = Item(currentItem.id, currentItem.name, holder.item.isChecked)

            viewModel.toggleItem(item)
            notifyDataSetChanged()
        }

        holder.itemRemoveButton.setOnClickListener {
            viewModel.removeItem(currentItem)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int =
        items.size
}