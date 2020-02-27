package com.example.layoutexam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.layoutexam.database.ExamDatabaseDao
import com.example.layoutexam.database.ExamScoreDatabase
import com.example.layoutexam.databinding.FragmentSummaryBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class SummaryFragment : Fragment() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSummaryBinding>(
            inflater, R.layout.fragment_summary, container, false
        )
        var examDatabaseDao: ExamDatabaseDao = ExamScoreDatabase.getInstance(activity!!.application).examDatabaseDao

        val summaryViewModel = SummaryViewModel(examDatabaseDao,activity!!.application)

        summaryViewModel.data.observe(viewLifecycleOwner, Observer{
            it?.let {
                binding.textSummary.text = it.toString()
            }
        } )

        return binding.root
    }

}
