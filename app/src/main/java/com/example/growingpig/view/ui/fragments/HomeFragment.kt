package com.example.growingpig.view.ui.fragments

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast


import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.growingpig.R

import com.example.growingpig.databinding.FragmentHomeBinding
import com.example.growingpig.model.Task
import com.example.growingpig.view.adapter.TaskAdapter
import com.example.growingpig.view.adapter.TaskListener
import com.example.growingpig.viewmodel.HomeViewModel


class HomeFragment : Fragment(), TaskListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var rvList: RecyclerView


    private lateinit var taskAdapter: TaskAdapter

    private lateinit var btnAddTask: Button

    private lateinit var title: String

    private lateinit var priority: String

    private lateinit var time: TextView


    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        rvList = binding.rvList

        taskAdapter = TaskAdapter(this)

        btnAddTask = binding.btnAddTask

        time = binding.tvZero

        btnAddTask.setOnClickListener {
            addTask()
        }



        rvList.apply {
            rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvList.adapter = taskAdapter
        }

        viewModel.getAllTasksObserver().observe(viewLifecycleOwner, Observer {
            taskAdapter.setListData(ArrayList(it))
            taskAdapter.notifyDataSetChanged()
        })


        val preferences = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        time.text = preferences.getInt(getString(R.string.time_key), 0).toString()


    }


    private fun addTask() {
        title = binding.etTitle.text.toString()
        priority = binding.etPriority.text.toString()

        if (!userInputValid()) {
            Toast.makeText(activity, getString(R.string.verifyInput), Toast.LENGTH_SHORT).show()
            return
        }

        val task = createTask(title, priority)

        viewModel.createTask(task)

        Toast.makeText(activity, getString(R.string.taskCreated), Toast.LENGTH_SHORT).show()

    }

    private fun userInputValid(): Boolean {
        var inputIsValid = true

        if (title.isEmpty() || priority.isEmpty()) {
            inputIsValid = false
        }

        return inputIsValid

    }

    private fun createTask(title: String, priority: String): Task {
        return Task(title, priority)
    }


    override fun onDeleteTaskClickListener(task: Task) {
        viewModel.deleteTask(task)
    }


}