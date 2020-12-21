package com.sedaaggez.marvelproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.marvelproject.R
import com.sedaaggez.marvelproject.adapter.CharacterAdapter
import com.sedaaggez.marvelproject.common.API_KEY
import com.sedaaggez.marvelproject.common.CHARACTER_LIMIT
import com.sedaaggez.marvelproject.common.PRIVATE_KEY
import com.sedaaggez.marvelproject.util.generate
import com.sedaaggez.marvelproject.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import java.util.concurrent.TimeUnit

class CharactersFragment : Fragment() {
    private lateinit var viewModel: CharactersViewModel
    private val characterAdapter = CharacterAdapter(arrayListOf())
    private var offset = 0
    private var page = 1
    private var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        val timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        if (offset == 0) {
            viewModel.getMarvelDataCharacter(
                API_KEY,
                generate(timeStamp, PRIVATE_KEY, API_KEY),
                timeStamp,
                offset,
                CHARACTER_LIMIT
            )
        }
        recyclerViewCharacters.layoutManager = LinearLayoutManager(context)
        recyclerViewCharacters.adapter = characterAdapter

        recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (offset < total) {
                        page++
                        offset = (page - 1) * CHARACTER_LIMIT

                        viewModel.getMarvelDataCharacter(
                            API_KEY,
                            generate(timeStamp, PRIVATE_KEY, API_KEY),
                            timeStamp,
                            offset,
                            CHARACTER_LIMIT
                        )


                    }
                }
            }
        })


        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.results.observe(viewLifecycleOwner, Observer { results ->
            results.let {
                recyclerViewCharacters.visibility = View.VISIBLE
                characterAdapter.updateResultList(results)
            }
        })

        viewModel.total.observe(viewLifecycleOwner, Observer { totalLiveData ->
            totalLiveData.let {
                total = totalLiveData
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                    textViewError.visibility = View.GONE
                    recyclerViewCharacters.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    textViewError.visibility = View.VISIBLE
                    recyclerViewCharacters.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE
                }
            }
        })
    }

}