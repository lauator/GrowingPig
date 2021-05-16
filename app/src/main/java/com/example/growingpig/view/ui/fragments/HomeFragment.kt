package com.example.growingpig.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growingpig.R

import com.example.growingpig.databinding.FragmentHomeBinding
import com.example.growingpig.view.adapter.TaskAdapter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.CompletableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var rvList: RecyclerView

    private val TAG = "RecyclerView"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        rootView.tag = TAG
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        rvList = binding.rvList
        rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //rvList.adapter = TaskAdapter()

        //Aca iria Rxjava creo, basicamente se encarga de llamar a la base de datos mientras que el observer
        //escucha esos datos y los pone en el adapter y actualiza la vista

        Completable.create(CompletableOnSubscribe{ emitter ->

            emitter.onComplete()

        })

            .subscribe(
                object: CompletableObserver {
                    override fun onSubscribe(d: Disposable?) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        TODO("Not yet implemented")
                    }

                    override fun onError(e: Throwable?) {
                        TODO("Not yet implemented")
                    }

                }

            )



        val fab = binding.floatingActionButton

        //TODO arreglar el menu de navegacion, no navega :(


        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navHomeFragment_to_addTaskFragmentDialog)
        }

    }


}