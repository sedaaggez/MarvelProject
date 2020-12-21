package com.sedaaggez.marvelproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedaaggez.marvelproject.R
import com.sedaaggez.marvelproject.adapter.ComicAdapter
import com.sedaaggez.marvelproject.common.API_KEY
import com.sedaaggez.marvelproject.common.COMIC_LIMIT
import com.sedaaggez.marvelproject.common.DATE_1
import com.sedaaggez.marvelproject.common.PRIVATE_KEY
import com.sedaaggez.marvelproject.databinding.FragmentCharacterDetailBinding
import com.sedaaggez.marvelproject.model.character.CharacterResult
import com.sedaaggez.marvelproject.util.downloadFromUrl
import com.sedaaggez.marvelproject.util.generate
import com.sedaaggez.marvelproject.util.placeholderProgressBar
import com.sedaaggez.marvelproject.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class CharacterDetailFragment : Fragment() {
    private lateinit var viewModel: CharacterDetailViewModel
    private val comicAdapter = ComicAdapter(arrayListOf())
    private lateinit var characterResult: CharacterResult
    private lateinit var dataBinding: FragmentCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)
        val timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val today = Date()
        val date2: String = dateFormatter.format(today)

        recyclerViewDetailComics.layoutManager = LinearLayoutManager(context)
        recyclerViewDetailComics.adapter = comicAdapter


        arguments?.let {
            characterResult = CharacterDetailFragmentArgs.fromBundle(it).characterResult

            imageViewDetailCharacter.downloadFromUrl(
                characterResult.thumbnail?.path + "." + characterResult.thumbnail?.extension,
                placeholderProgressBar(requireContext())
            )
            textViewDetailCharacterName.text = characterResult.name
            textViewDetailCharacterDescription.text = characterResult.description
        }


        val dateRange = DATE_1 + "," + date2

        viewModel.getCharacterComics(
            characterResult.id!!,
            API_KEY,
            generate(timeStamp, PRIVATE_KEY, API_KEY),
            timeStamp,
            dateRange,
            COMIC_LIMIT
        )
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_character_detail,
            container,
            false
        )
        return dataBinding.root
    }

    private fun observeLiveData() {
        viewModel.comicResults.observe(viewLifecycleOwner, Observer { comicResultList ->
            comicResultList?.let {
                comicAdapter.updateComicsList(comicResultList)
            }
        })
    }

}