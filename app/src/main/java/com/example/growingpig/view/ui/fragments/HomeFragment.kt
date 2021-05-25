package com.example.growingpig.view.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.growingpig.R

import com.example.growingpig.databinding.FragmentHomeBinding
import com.example.growingpig.model.Task
import com.example.growingpig.view.adapter.TaskAdapter
import com.example.growingpig.view.database.AppDatabase

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.launch

//TODO hacer que me muestre los datos de la base de datos sin tener que apretar el boton
//TODO hacer UI mas amigable. Con constraints y con espacio para el need
//TODO hacer que cuando haga check se elimine la tarea en cuestion





class HomeFragment : Fragment(), Dialog {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var rvList: RecyclerView

    private lateinit var tasks: List<Task>

    private val compositeDisposable = CompositeDisposable()

    //private var need = ""




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return  inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        rvList = binding.rvList
        rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val fab = binding.floatingActionButton


        fab.setOnClickListener {
            addTask()
        }

        /*val preferences = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        need = preferences.getInt(getString(R.string.need_key), 0).toString()*/



    }


    private fun addTask() {
        showDialog()

        childFragmentManager.setFragmentResultListener("dataChanged", this.viewLifecycleOwner){_, bundle ->
            if(bundle.getBoolean("OK")){
                refreshList()
            }
        }
    }

    override fun showDialog() {
        val dialog = AddTaskDialogFragment()
        dialog.show(childFragmentManager, "AddTaskDialogFragment")
    }




    private fun refreshList (){
        Single.create(SingleOnSubscribe<List<Task>> { emitter ->


            lifecycleScope.launch {
                context?.let {

                    val room = Room
                        .databaseBuilder(it, AppDatabase::class.java, "tasks")
                        .build()

                    tasks = room.taskDAO().getAllTasks()

                }
            }

            emitter.onSuccess(tasks)

        })

            .subscribe(object : SingleObserver<List<Task>>{
                override fun onSubscribe(d: Disposable?) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Task>) {
                    rvList.adapter = TaskAdapter(t)
                }

                override fun onError(e: Throwable?) {
                    Log.e("Observer:", "onError", e)
                }

            }

            )
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }




}