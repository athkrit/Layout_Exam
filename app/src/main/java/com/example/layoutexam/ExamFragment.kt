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
import com.example.layoutexam.databinding.FragmentExamBinding
import kotlin.math.log

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
            icon = R.drawable.dice_1
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_1
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_1
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_1
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_1
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_1
        )
    )
    private val choicesLayoutSecond: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_2
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_2
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_2
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_2
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_2
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_2
        )
    )
    private val choicesLayoutThird: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_3
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_3
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_3
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_3
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_3
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_3
        )
    )
    private val choicesLayoutFourth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_4
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_4
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_4
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_4
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_4
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_4
        )
    )
    private val choicesLayoutFifth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_5
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_5
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_5
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_5
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_5
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_5
        )
    )
    private val choicesLayoutSixth: MutableList<Choice> = mutableListOf(
        Choice(
            iconName = "setting",
            icon = R.drawable.dice_6
        ),
        Choice(
            iconName = "home",
            icon = R.drawable.dice_6
        ),
        Choice(
            iconName = "redeem",
            icon = R.drawable.dice_6
        ),
        Choice(
            iconName = "History",
            icon = R.drawable.dice_6
        ),
        Choice(
            iconName = "Promotion",
            icon = R.drawable.dice_6
        ),
        Choice(
            iconName = "Voucher",
            icon = R.drawable.dice_6
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
    var time:Int =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentExamBinding>(
            inflater, R.layout.fragment_exam, container, false
        )
        var examViewModel = ExamViewModel(activity!!.application)
        var adapter = ExamAdapter(examViewModel)

        setAnswerLayout(adapter)
        binding.recyclerView.adapter = adapter
        setItemsPerRow(binding)
        setQuestion(binding)
        examViewModel.clickEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                stopTime = System.currentTimeMillis()
                if(it.toLowerCase().equals(currentQuestion.answers.toLowerCase())){
                    Toast.makeText(activity,"Correct "+((stopTime-startTime)/1000 )+" sec",Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(activity,"Wrong",Toast.LENGTH_SHORT).show()
                }
                examViewModel.finish()
                changeQuestionAndAnswerLayout()
                setQuestion(binding)
                setAnswerLayout(adapter)
                setItemsPerRow(binding)
                startTime =System.currentTimeMillis()
            }
        })
        startTime =System.currentTimeMillis()
        return binding.root
    }

    private fun setQuestion(binding: FragmentExamBinding) {
        currentQuestion = questions[questionIndex]
        binding.textQuestion.text = currentQuestion.question
    }

    private fun changeQuestionAndAnswerLayout() {
        if(questionIndex >1 && layoutIndex >4){
            layoutIndex =0
            questionIndex = 0
            questions.shuffle()
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
