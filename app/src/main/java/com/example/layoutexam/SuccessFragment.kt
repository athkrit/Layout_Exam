package com.example.layoutexam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.layoutexam.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSuccessBinding>(
            inflater,
            R.layout.fragment_success, container, false
        )

        binding.btnBackToHome.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(SuccessFragmentDirections.actionSuccessFragmentToHomeFragment())
        }

        return binding.root
    }

}
