package com.example.layoutexam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.layoutexam.databinding.FragmentExamBinding
import com.example.layoutexam.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        binding.buttonExam.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections
                    .actionHomeFragmentToExamFragment2())
        }
        binding.buttonSummary.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections
                    .actionHomeFragmentToSummaryFragment())
        }
        return binding.root
    }
}
