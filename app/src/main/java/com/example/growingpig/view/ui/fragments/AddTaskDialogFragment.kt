package com.example.growingpig.view.ui.fragments



import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.growingpig.R
import com.example.growingpig.databinding.DialogAddTaskBinding
import com.example.growingpig.model.Task
import com.example.growingpig.view.database.AppDatabase


import kotlinx.coroutines.launch
import java.lang.IllegalStateException


class AddTaskDialogFragment: DialogFragment(){

    private lateinit var binding: DialogAddTaskBinding

    private lateinit var title: String
    private lateinit var priority: String




     override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.dialog_add_task, null)

            binding = DialogAddTaskBinding.bind(view)



            binding.btnAddTask.setOnClickListener()
            {
                title = binding.etTaskTitle.text.toString()
                priority = binding.etTaskPriority.text.toString()
                onClickAddTask()

                dialog?.dismiss()


            }

            binding.ivClose.setOnClickListener {
                dialog?.cancel()
            }


            builder.setView(view)


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }




    private fun onClickAddTask() {
        if(!userInputValid())
        {
            Toast.makeText(activity, getString(R.string.verifyInput), Toast.LENGTH_SHORT).show()
            return
        }

        val task = createTask(title, priority)

       saveTask(task)

        parentFragmentManager.setFragmentResult("dataChanged", bundleOf("OK" to true))


        Toast.makeText(activity, getString(R.string.taskCreated), Toast.LENGTH_SHORT).show()


    }

    private fun userInputValid(): Boolean {
        var inputIsValid = true

        if(title.isEmpty() || priority.isEmpty())
        {
            inputIsValid = false
        }

        return inputIsValid

    }


    private fun createTask(title: String, priority: String): Task {
        return Task(title, priority)
    }


  private fun saveTask(task: Task) {
      lifecycleScope.launch {
          context?.let {

              val room = Room
                  .databaseBuilder(it, AppDatabase::class.java, "tasks")
                  .build()

              room.taskDAO().createTask(task)

          }
      }



    }



}