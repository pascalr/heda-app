package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var term = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        term = arguments?.getString("term") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSearchTerm.text = term
    }
}

fun searchBundle(term: String) : Bundle {
    val bundle = Bundle()
    bundle.putSerializable("term", term)
    return bundle
}