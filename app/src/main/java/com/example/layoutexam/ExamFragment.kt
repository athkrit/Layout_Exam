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
import androidx.navigation.fragment.findNavController
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
        val icon: Int
    )

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            question = "SETTING",
            answers = "setting"
        ),
        Question(
            question = "HOME",
            answers = "Home"
        ),
        Question(
            question = "REDEEM",
            answers = "Redeem"
        ),
        Question(
            question = "HISTORY",
            answers = "History"
        ),
        Question(
            question = "PROMOTION",
            answers = "Promotion"
        ),
        Question(
            question = "VOUCHER",
            answers = "Voucher"
        )
    )
    private val choicesLayoutFirst: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.circular_setting
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.circular_home
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.circular_redeem
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.circular_history
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.circular_promotion
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.circular_voucher
        )
    )
    private val choicesLayoutSecond: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.circular_setting_2
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.circular_home_2
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.circular_redeem_2
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.circular_history_2
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.circular_promotion_2
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.circular_voucher_2
        )
    )
    private val choicesLayoutThird: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = android.R.color.transparent
        ),
        Choice(
            iconName = "home",
            icon = android.R.color.transparent
        ),
        Choice(
            iconName = "redeem",
            icon = android.R.color.transparent
        ),
        Choice(
            iconName = "History",
            icon = android.R.color.transparent
        ),
        Choice(
            iconName = "Promotion",
            icon = android.R.color.transparent
        ),
        Choice(
            iconName = "Voucher",
            icon = android.R.color.transparent
        )
    )
    private val choicesLayoutFourth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "",
            icon = R.drawable.circular_setting
        ),
        Choice(
            iconName = "",
            icon = R.drawable.circular_home
        ),
        Choice(
            iconName = "",
            icon = R.drawable.circular_redeem
        ),
        Choice(
            iconName = "",
            icon = R.drawable.circular_history
        ),
        Choice(
            iconName = "",
            icon = R.drawable.circular_promotion
        ),
        Choice(
            iconName = "",
            icon = R.drawable.circular_voucher
        )
    )
    private val choicesLayoutFifth: MutableList<Choice> = choicesLayoutFirst
    private val choicesLayoutSixth: MutableList<Choice> = choicesLayoutSecond
    private val choicesLayoutSeventh: MutableList<Choice> = choicesLayoutThird
    private val choicesLayoutEighth: MutableList<Choice> = choicesLayoutFourth

    private val choiceLayout: MutableList<MutableList<Choice>> = mutableListOf(
        choicesLayoutFirst,
        choicesLayoutSecond,
        choicesLayoutThird,
        choicesLayoutFourth,
        choicesLayoutFifth,
        choicesLayoutSixth,
        choicesLayoutSeventh,
        choicesLayoutEighth
    )
    lateinit var currentQuestion: Question
    private var questionIndex = 0
    private var layoutIndex = 0
    var startTime:Long =0
    var stopTime:Long =0
    var allAnswer = ArrayList<String>(0)
    var allTimeUsed = ArrayList<String>(0)

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
        setAnswerLayout(adapter)
        binding.recyclerView.adapter = adapter
        setItemsPerRow(binding)
        setQuestion(binding)
        examViewModel.clickEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                stopTime = System.currentTimeMillis()
                var time = (stopTime- startTime)
                if(it.toLowerCase().equals(currentQuestion.answers.toLowerCase())){
                    allAnswer.add("Correct")
                    allTimeUsed.add("$time ms")
                    Toast.makeText(activity, "Correct $time ms",Toast.LENGTH_SHORT).show()
                } else{
                    allAnswer?.add("Wrong")
                    allTimeUsed?.add("$time ms")
                    Toast.makeText(activity, "Wrong $time ms",Toast.LENGTH_SHORT).show()
                }
                changeQuestionAndAnswerLayout(examViewModel)
                setQuestion(binding)
                setAnswerLayout(adapter)
                setItemsPerRow(binding)
                examViewModel.finish()
            }
        })
        return binding.root
    }

    private fun setQuestion(binding: FragmentExamBinding) {
        startTime = System.currentTimeMillis()
        currentQuestion = questions[questionIndex]
        binding.textQuestion.text = currentQuestion.question
    }

    private fun changeQuestionAndAnswerLayout(examViewModel: ExamViewModel) {
        if(questionIndex > 1 && layoutIndex > 6){
            examViewModel.insertData(allAnswer,allTimeUsed)
            Log.d("asdasd", "add")
            findNavController().navigate(R.id.action_examFragment_to_successFragment)
            layoutIndex =0
            questionIndex = 0
            questions.shuffle()
            allAnswer = ArrayList<String>(0)
            allTimeUsed = ArrayList<String>(0)
        }
        else if (questionIndex > 1) {
            layoutIndex++
            questionIndex = 0
            questions.shuffle()
        } else {
            questionIndex++
        }

    }
    private fun setAnswerLayout(adapter: ExamAdapter) {
        choiceLayout[layoutIndex].shuffle()
        adapter.data = choiceLayout[layoutIndex]
    }
    private fun setItemsPerRow(binding: FragmentExamBinding) {
        if(layoutIndex >3){
            binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
    }
}
