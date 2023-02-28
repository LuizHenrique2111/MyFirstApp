package com.luizhenrique.myfirstapp.ui.fragment.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.luizhenrique.myfirstapp.R
import com.luizhenrique.myfirstapp.model.Category
import kotlinx.android.synthetic.main

class CategoriesAdapter(
    val categories: MutableList<Category>,
    val onClick: (String) -> Unit
) : Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item_categories, parent, false)
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        val view = holder.itemView
        with(view) {
            cardview_title.text = category
            cardview_image.setImageResource(category.imageCategory)
            cardview.setOnClickListener {
                onClick(category.titleCategory)
            }
        }
    }

    override fun getItemCount() = categories.size

    class CategoriesViewHolder(view: View) : ViewHolder(view)

}