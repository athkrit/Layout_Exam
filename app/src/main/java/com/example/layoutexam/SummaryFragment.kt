package com.example.layoutexam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.layoutexam.databinding.FragmentHomeBinding
import com.example.layoutexam.databinding.FragmentSummaryBinding

/**
 * A simple [Fragment] subclass.
 */
class SummaryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSummaryBinding>(
            inflater, R.layout.fragment_summary, container, false
        )
        return binding.root
    }


}
