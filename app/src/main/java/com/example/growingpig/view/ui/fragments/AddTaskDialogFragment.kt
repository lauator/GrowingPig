package com.example.growingpig.view.ui.fragments



import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.growingpig.R
import com.example.growingpig.databinding.DialogAddTaskBinding
import com.example.growingpig.model.Task
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.CompletableOnSubscribe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.IllegalStateException


class AddTaskDialogFragment: DialogFragment() {

    private lateinit var binding: DialogAddTaskBinding

    private lateinit var title: String
    private lateinit var priority: String
    private lateinit var test: EditText


//TODO hacer funcionar este fragment




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

        var task: Task = createTask(title, priority)

        saveTask(task)

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

//TODO revisar porque no funciona esto
/*
        lifecycleScope.launch(){
            app.room.taskDAO().createTask(task)
            var result  = app.room.taskDAO().getAllTasks().size

        }
*/



    }



}