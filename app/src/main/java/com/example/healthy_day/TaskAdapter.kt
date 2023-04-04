package com.example.healthy_day

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy_day.databinding.TaskItemBinding
//import com.example.healthy_day.datamodel.TaskModel

class TaskAdapter (val taskList: ArrayList<TaskModel>, val context: Context) :
    RecyclerView.Adapter<TaskAdapter.MyHoler>() {


    class MyHoler(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHoler {

        val binding = TaskItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHoler(binding)
    }

    override fun onBindViewHolder(holder: MyHoler, position: Int) {

        val task = taskList[position]
        with(holder) {
            binding.tvTaskName.text = "Waga: " + task.waga + "kg"
            binding.tvWaga.text = "Data: " + task.data
            binding.chBox.isChecked = task.isChecked

        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}