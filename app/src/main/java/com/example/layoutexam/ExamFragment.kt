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
        val hasIconName:Boolean
    )

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            question = "ถ้าคุณต้องการไปหน้าตั้งค่าคุณจะกดปุ่มไหน",
            answers = "setting"
        ),
        Question(
            question = "ถ้าคุณต้องการไปหน้าหลักคุณจะกดปุ่มไหน",
            answers = "Home"
        ),
        Question(
            question = "ถ้าคุณต้องการไปหน้ารีดีมคุณจะกดปุ่มไหน",
            answers = "Redeem"
        ),
        Question(
            question = "ถ้าคุณต้องการไปหน้าประวัติคุณจะกดปุ่มไหน",
            answers = "History"
        ),
        Question(
            question = "ถ้าคุณต้องการไปหน้าตโปรโมชันุณจะกดปุ่มไหน",
            answers = "Promotion"
        ),
        Question(
            question = "Voucher",
            answers = "Voucher"
        )
    )
    private val choicesLayoutFirst: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_1,
            hasIconName = true
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_1,
            hasIconName = true
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_1,
            hasIconName = true
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_1,
            hasIconName = true
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_1,
            hasIconName = true
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_1,
            hasIconName = true
        )
    )
    private val choicesLayoutSecond: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_2,
            hasIconName = true
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_2,
            hasIconName= true
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_2,
            hasIconName= true
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_2,
            hasIconName= true
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_2,
            hasIconName= true
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_2,
            hasIconName= true
        )
    )
    private val choicesLayoutThird: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_3,
            hasIconName= true
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_3,
            hasIconName= true
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_3,
            hasIconName= true
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_3,
            hasIconName= true
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_3,
            hasIconName= true
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_3,
            hasIconName= true
        )
    )
    private val choicesLayoutFourth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_4,
            hasIconName= false
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_4,
            hasIconName= false
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_4,
            hasIconName= false
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_4,
            hasIconName= false
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_4,
            hasIconName= false
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_4,
            hasIconName= false
        )
    )
    private val choicesLayoutFifth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_5,
            hasIconName= false
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_5,
            hasIconName= false
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_5,
            hasIconName= false
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_5,
            hasIconName= false
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_5,
            hasIconName= false
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_5,
            hasIconName= false
        )
    )
    private val choicesLayoutSixth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_6,
            hasIconName= false
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_6,
            hasIconName= false
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_6,
            hasIconName= false
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_6,
            hasIconName= false
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_6,
            hasIconName= false
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_6,
            hasIconName= false
        )
    )

    private val choiceLayout: MutableList<MutableList<Choice>> = mutableListOf(
        choicesLayoutFirst,
        choicesLayoutSecond,
        choicesLayoutThird,
        choicesLayoutFourth,
        choicesLayoutFifth,
        choicesLayoutSixth
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
                var time = (stopTime-startTime)/1000
                if(it.toLowerCase().equals(currentQuestion.answers.toLowerCase())){
                    allAnswer.add("Correct")
                    allTimeUsed.add("$time sec")
                    Toast.makeText(activity, "Correct $time sec",Toast.LENGTH_SHORT).show()
                } else{
                    allAnswer?.add("Wrong")
                    allTimeUsed?.add("$time sec")
                    Toast.makeText(activity, "Wrong $time sec",Toast.LENGTH_SHORT).show()
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
        if(questionIndex >1 && layoutIndex >4){
            examViewModel.insertData(allAnswer,allTimeUsed)
            Log.d("asdasd", "add")
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
