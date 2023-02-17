package com.example.mycocktailsapp.ui.home

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktailsapp.databinding.FragmentHomeBinding
import com.example.mycocktailsapp.model.CocktailResponse
import com.example.mycocktailsapp.model.Drink
import com.example.mycocktailsapp.ui.adapter.CocktailAdapter
import com.example.mycocktailsapp.utils.BaseFragment
import com.example.mycocktailsapp.utils.UIState
import com.example.mycocktailsapp.viewmodel.MyCocktailAppViewModel

class HomeFragment : BaseFragment() {

    /*private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!*/
    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val cocktailAdapter by lazy {
        CocktailAdapter {
            Log.d(ContentValues.TAG, "Item Clicked. Preview URL: $it")
            //myCocktailAppViewModel.cocktail = it
            //findNavController().navigate(R.id.)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MyCocktailAppViewModel::class.java)

        adapter()
        /*_binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root*/

        //val textView: TextView = binding.textHome
        /*homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
    }

    private fun adapter() {

        binding.rvcocktail.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2
            )
            adapter = cocktailAdapter
        }
        Log.d(ContentValues.TAG, "onCreateView: ")
        myCocktailAppViewModel.cocktail.observe(viewLifecycleOwner) { state ->
            when(state){
                is UIState.LOADING -> {}
                is UIState.SUCCESS<CocktailResponse> -> {
                    Log.d(ContentValues.TAG, "onCreateView: ${state.response}")
                    cocktailAdapter.updateItems((state.response.drinks ?: emptyList()) as List<Drink>)
                }
                is UIState.ERROR -> {
                    state.error.localizedMessage?.let {
                        showError(it) {

                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}