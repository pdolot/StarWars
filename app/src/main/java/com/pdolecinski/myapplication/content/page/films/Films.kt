package com.pdolecinski.myapplication.content.page.films


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdolecinski.myapplication.R
import com.pdolecinski.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.page_films.*

class Films : BaseFragment() {
    private val viewModel by lazy { FilmsViewModel() }
    private val adapter by lazy { FilmsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        viewModel.filmDao.getAll().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    private fun setAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@Films.adapter
        }

        adapter.showDetailsClickListener = ::navigateToDetails
    }

    private fun navigateToDetails(id: Long){
        findNavController().navigate(FilmsDirections.actionFilmsToFilmDetails(id))
    }
}

