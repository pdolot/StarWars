package com.pdolecinski.myapplication.content.page.filmDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdolecinski.myapplication.R
import com.pdolecinski.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.page_film_details.*

class FilmDetails : BaseFragment() {

    private val viewModel by lazy { FilmDetailsViewModel() }
    private val adapter by lazy { PeopleAdapter() }
    private val args: FilmDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_film_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

        viewModel.filmDao.getFilmById(args.filmId).observe(viewLifecycleOwner, Observer {

            filmTitle.text = it?.title

            it?.let { film ->
                viewModel.personDao.getPeopleByIds(film.characters_ids)
                    .observe(viewLifecycleOwner, Observer {
                        adapter.setData(it)
                    })
            }
        })
    }

    private fun setAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FilmDetails.adapter
        }
    }
}

