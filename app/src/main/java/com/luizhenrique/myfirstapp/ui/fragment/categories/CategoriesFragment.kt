package com.luizhenrique.myfirstapp.ui.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luizhenrique.myfirstapp.R
import com.luizhenrique.myfirstapp.ui.activity.MainActivity
import com.luizhenrique.myfirstapp.ui.fragment.categories.adapter.CategoriesAdapter
import com.luizhenrique.myfirstapp.ui.fragment.categorydetails.CategoryDetailsFragment
import java.util.Locale.Category

class CategoriesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).title = "Principal - Categorias"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        initializeViews(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
            it.adapter = CategoriesAdapter(addCategories()) { categoryTitle ->
                arguments = Bundle().apply {
                    putString("category", categoryTitle)
                }
                val fragmentDetails = CategoryDetailsFragment()
                fragmentDetails.arguments = arguments
                (activity as MainActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_fragment, fragmentDetails)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun addCategories() = mutableListOf(
        com.luizhenrique.myfirstapp.model.Category(getString(R.string.txt_limpeza), R.drawable.ic_cleaning),
        com.luizhenrique.myfirstapp.model.Category(getString(R.string.txt_alimentos), R.drawable.ic_foods),
        com.luizhenrique.myfirstapp.model.Category(getString(R.string.txt_higiene), R.drawable.ic_hygiene),
        com.luizhenrique.myfirstapp.model.Category(getString(R.string.txt_bebidas), R.drawable.ic_drinks),
        com.luizhenrique.myfirstapp.model.Category(getString(R.string.txt_others), R.drawable.ic_others)
    )

    private fun initializeViews(root: View) {
        recyclerView = root.findViewById(R.id.recyclerView_categories)
    }

}