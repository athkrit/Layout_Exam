package com.example.layoutexam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutexam.databinding.ListChoiceItemBinding

class ExamAdapter(val examViewModel: ExamViewModel): RecyclerView.Adapter<ExamAdapter.ViewHolder>() {
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
        holder.bind(item,examViewModel)
    }
    class ViewHolder(val binding: ListChoiceItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ExamFragment.Choice,examViewModel :ExamViewModel) {
            binding.imgChoice.setImageResource(item.icon)
            binding.textChoice.text = item.iconName
            binding.layout.setOnClickListener(View.OnClickListener {
                examViewModel.onItemclick(item.iconName)
            })
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