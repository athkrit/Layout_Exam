package com.example.layoutexam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutexam.databinding.ListChoiceItemBinding

class ExamAdapter(val examListener: ExamListener): RecyclerView.Adapter<ExamAdapter.ViewHolder>() {
//    lateinit var liveData: MutableLiveData<MutableList<ExamFragment.Choice>>
    var data =  listOf<ExamFragment.Choice>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ExamAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,examListener)
    }
    class ViewHolder(val binding: ListChoiceItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ExamFragment.Choice,examListener :ExamListener) {
            binding.imgChoice.setImageResource(item.icon)
            binding.examListener = examListener
            binding.choice = item
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListChoiceItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
    override fun getItemCount()= data.size
}
class ExamListener(val clickListener: (iconName: String) -> Unit) {
    fun onClick(choice: ExamFragment.Choice) = clickListener(choice.iconName)
}