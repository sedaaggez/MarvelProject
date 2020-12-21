package com.sedaaggez.marvelproject.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.sedaaggez.marvelproject.R
import com.sedaaggez.marvelproject.model.character.CharacterResult
import com.sedaaggez.marvelproject.util.downloadFromUrl
import com.sedaaggez.marvelproject.util.placeholderProgressBar
import com.sedaaggez.marvelproject.view.CharactersFragmentDirections
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(val resultList: ArrayList<CharacterResult>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.view.textViewCharacterName.text = resultList[position].name


        holder.view.imageViewCharacter.downloadFromUrl(
            resultList[position].thumbnail?.path + "." + resultList[position].thumbnail?.extension,
            placeholderProgressBar(holder.view.context)
        )

        holder.view.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, resultList[position].name)
            Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                    resultList[position]
                )
            Navigation.findNavController(it).navigate(action)
        })


    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateResultList(newResultList: List<CharacterResult>) {
        resultList.clear()
        resultList.addAll(newResultList)
        notifyDataSetChanged()
    }
}