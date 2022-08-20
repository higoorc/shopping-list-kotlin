package com.hsilva.myshoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hsilva.myshoppinglist.databinding.FragmentHomeBinding
import com.hsilva.myshoppinglist.service.dto.Item
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@OptIn(DelicateCoroutinesApi::class)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var items: RecyclerView
    lateinit var navigateButton: FloatingActionButton
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getAllItems()
    }

    override fun onResume() {
        super.onResume()

        val allItemsObserver: Observer<List<Item>> =
            Observer<List<Item>> { allItems ->
                if (allItems.isNotEmpty()) {
                    items.adapter = ShoppingListAdapter(requireContext(), allItems, viewModel)
                } else {
                    //
                }
            }

        viewModel.getItems().observe(this, allItemsObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = binding.shopRecyclerView
        navigateButton = binding.navigateButton
        items.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}