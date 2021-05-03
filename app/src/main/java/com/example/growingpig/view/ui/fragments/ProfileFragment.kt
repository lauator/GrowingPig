package com.example.growingpig.view.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

import com.example.growingpig.R

import com.example.growingpig.databinding.FragmentProfileBinding

import com.google.android.material.textfield.TextInputEditText

private lateinit var binding: FragmentProfileBinding


private lateinit var tvIncomingsAmount: TextView
private lateinit var tvOutcomingsAmount: TextView
private lateinit var tvBalanceAmount: TextView
private lateinit var tvSavings: TextView
private lateinit var tvGoalAmount: TextView
private lateinit var tvNeed: TextView
private lateinit var btnModifyIncomings: Button
private lateinit var btnModifyOutcomings: Button
private lateinit var btnModifySavings: Button
private lateinit var btnModifyGoal: Button



private lateinit var whyParagraph: TextInputEditText
class ProfileFragment : Fragment(){

    var extraValue: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)


        tvIncomingsAmount = binding.tvIncomingsAmounts
        tvOutcomingsAmount = binding.tvOutcomingsAmount
        tvBalanceAmount = binding.tvBalanceAmount
        tvSavings = binding.tvSavings
        tvGoalAmount = binding.tvGoalAmount
        tvNeed = binding.tvNeed
        whyParagraph = binding.whyParagraph

        btnModifyIncomings = binding.btnChangeIncomings
        btnModifyOutcomings = binding.btnChangeOutcomings
        btnModifyGoal = binding.btnGoalModify
        btnModifySavings = binding.btnSavingsModify




        btnModifyIncomings.setOnClickListener(){
            modifyIncomings()

        }

        btnModifyOutcomings.setOnClickListener(){

        }

        btnModifySavings.setOnClickListener(){
            
        }

        btnModifyGoal.setOnClickListener(){


        }


        //tvBalanceAmount.text = calculateBalance().toString()


    }

    private fun modifyIncomings() {
        showModifyDialog()

        childFragmentManager.setFragmentResultListener("keyI", this.viewLifecycleOwner){key, bundle ->
            val resultI = bundle.getString("incoming", "fallo")
            tvIncomingsAmount.text = resultI
        }

    }



    //TODO Poder modificar todos los edittext
    //TODO guardarlos en la base de datos


    fun showModifyDialog(){
        val dialog = ModifyValueDialogFragment()
        dialog.show(childFragmentManager, "ModifyValueDialogFragment")
    }







}





    /*
    private fun calculateBalance() : Int {
       var outcoming =  tvOutcomingsAmount.text.toString().toInt()
        var incoming =  tvOutcomingsAmount.text.toString().toInt()

        return outcoming - incoming

    }
*/

