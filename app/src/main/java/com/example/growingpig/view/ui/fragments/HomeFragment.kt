package com.example.growingpig.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentAddTaskDialogBinding
import com.example.growingpig.databinding.FragmentHomeBinding




private lateinit var binding: FragmentHomeBinding

private lateinit var rvList: RecyclerView

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        rvList = binding.rvList

        val fab = binding.floatingActionButton

        //TODO arreglar el menu de navegacion, no navega :(


        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navHomeFragment_to_addTaskFragmentDialog)
        }

    }


}