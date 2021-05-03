package com.example.growingpig.view.ui.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentAddTaskDialogBinding
import com.example.growingpig.model.Task
import com.example.growingpig.view.database.TaskApp
import kotlinx.coroutines.launch


private lateinit var binding: FragmentAddTaskDialogBinding

private lateinit var title: String
private lateinit var priority: String
private lateinit var test: EditText



class AddTaskDialogFragment: DialogFragment() {



     //val app = activity?.applicationContext as TaskApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //para obtener el contexto de un fragment
         //appContext = requireContext().applicationContext

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTaskDialogBinding.bind(view)

        title = binding.etTaskTitle.text.toString()
        priority = binding.etTaskPriority.text.toString()


        binding.btnAddTask.setOnClickListener()
        {
            onClickAddTask()
        }

    }

    private fun onClickAddTask() {
        if(userInputValid())
        {
            Toast.makeText(activity, getString(R.string.verifyInput), Toast.LENGTH_SHORT).show()
            return
        }

        var task: Task = createTask(title, priority)

        saveTask(task)

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

//Esto permite que se muestre como DialogFragment
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

}