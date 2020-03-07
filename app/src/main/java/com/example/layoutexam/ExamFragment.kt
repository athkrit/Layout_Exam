package com.example.layoutexam


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.layoutexam.database.ExamDatabaseDao
import com.example.layoutexam.database.ExamScoreDatabase
import com.example.layoutexam.databinding.FragmentExamBinding
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ExamFragment : Fragment() {
    data class Question(
        val question: String,
        val answers: String
    )

    data class Choice(
        val iconName: String,
        val icon: Int,
        val isShowIconName:Boolean
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentExamBinding>(
            inflater, R.layout.fragment_exam, container, false
        )
        var examDatabaseDao:ExamDatabaseDao = ExamScoreDatabase.getInstance(activity!!.application).examDatabaseDao
        var examViewModel = ExamViewModel(examDatabaseDao,activity!!.application)
        var adapter = ExamAdapter(ExamListener {
            examViewModel.onItemclick(it)
        })

        examViewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            binding.textQuestion.text = it.question
        })

        examViewModel.currentChoice.observe(viewLifecycleOwner, Observer {
            adapter.data = it

        })
        examViewModel.toast.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                examViewModel.clearToast()
            }
        })
        examViewModel.currentSpanCount.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recyclerView.layoutManager = GridLayoutManager(context, it)
            }
        })
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}
