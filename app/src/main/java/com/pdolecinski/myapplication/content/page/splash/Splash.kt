package com.pdolecinski.myapplication.content.page.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pdolecinski.myapplication.R
import com.pdolecinski.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.page_splash.*

class Splash : BaseFragment() {
    private val viewModel by lazy { SplashViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.callResult.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                findNavController().navigate(SplashDirections.actionSplashToFilms())
            }
        })

        viewModel.requestCounter.observe(viewLifecycleOwner, Observer {
            progressBar.setProgress(it.toFloat()/10f, 0)
        })
    }
}

