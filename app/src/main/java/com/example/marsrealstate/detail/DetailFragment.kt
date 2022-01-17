package com.example.marsrealstate.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.marsrealstate.R
import com.example.marsrealstate.databinding.FragmentDetailBinding

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application

        binding.lifecycleOwner = this
        return binding.root
    }
}
