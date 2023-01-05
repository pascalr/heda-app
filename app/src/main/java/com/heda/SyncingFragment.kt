package com.heda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.heda.databinding.SyncingWaitingScreenBinding

class SyncingFragment : Fragment() {

    private var _binding: SyncingWaitingScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SyncingWaitingScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    //override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    super.onViewCreated(view, savedInstanceState)
    //}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}