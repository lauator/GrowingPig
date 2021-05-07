package com.example.growingpig.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentProfileBinding
import com.google.android.material.textfield.TextInputEditText





private lateinit var whyParagraph: TextInputEditText
class ProfileFragment : Fragment(){

    private lateinit var binding: FragmentProfileBinding

    private lateinit var tvIncomeAmount: TextView
    private lateinit var tvOutgoingsAmount: TextView
    private lateinit var tvBalanceAmount: TextView
    private lateinit var tvSavings: TextView
    private lateinit var tvGoalAmount: TextView
    private lateinit var tvNeed: TextView
    private lateinit var tvTime: TextView

    private lateinit var btnModifyIncome: Button
    private lateinit var btnModifyOutgoings: Button
    private lateinit var btnModifySavings: Button
    private lateinit var btnModifyGoal: Button


    private var resultI = ""
    private var resultO = ""
    private var resultS = ""
    private var resultG = ""

    private var income: Int = 0
    private var outgoings: Int = 0
    private var goal: Int = 0
    private var saving: Int = 0
    private var balance: Int = 0
    private var need: Int = 0




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


        tvIncomeAmount = binding.tvIncomingsAmounts
        tvOutgoingsAmount = binding.tvOutcomingsAmount
        tvBalanceAmount = binding.tvBalanceAmount
        tvSavings = binding.tvSavingsAmount
        tvGoalAmount = binding.tvGoalAmount
        tvNeed = binding.tvNeedAmount
        tvTime = binding.tvTime
        whyParagraph = binding.whyParagraph

        btnModifyIncome = binding.btnChangeIncomings
        btnModifyOutgoings = binding.btnChangeOutcomings
        btnModifyGoal = binding.btnGoalModify
        btnModifySavings = binding.btnSavingsModify




        btnModifyIncome.setOnClickListener{
            modifyValue(1)
        }

        btnModifyOutgoings.setOnClickListener{
            modifyValue(2)

        }

        btnModifySavings.setOnClickListener{
            modifyValue(3)
        }

        btnModifyGoal.setOnClickListener{
            modifyValue(4)
        }





    }

    private fun modifyValue(value: Int) {
        showModifyDialog()

            childFragmentManager.setFragmentResultListener("key", this.viewLifecycleOwner) { _, bundle ->
                when (value) {
                    1 -> {
                        resultI = bundle.getString("incoming", "0")
                        tvIncomeAmount.text = resultI
                        tvBalanceAmount.text = calculateBalance().toString()
                        if(calculateTime() > 99){
                            tvTime.text = "+99"
                        }
                        else{
                        tvTime.text = calculateTime().toString()
                        }
                    }
                    2 -> {
                        resultO = bundle.getString("outgoings", "0")
                        tvOutgoingsAmount.text = resultO
                        tvBalanceAmount.text = calculateBalance().toString()
                        if(calculateTime() > 99){
                            tvTime.text = "+99"
                        }
                        else{
                            tvTime.text = calculateTime().toString()
                        }
                    }
                    3 -> {
                        resultS = bundle.getString("saving", "0")
                        tvSavings.text = resultS
                        tvNeed.text = calculateNeed().toString()
                        if(calculateTime() > 99){
                            tvTime.text = "+99"
                        }
                        else{
                            tvTime.text = calculateTime().toString()
                        }
                    }
                    4 -> {
                        resultG = bundle.getString("goal", "0")
                        tvGoalAmount.text = resultG
                        tvNeed.text = calculateNeed().toString()
                        if(calculateTime() > 99){
                            tvTime.text = "+99"
                        }
                        else{
                            tvTime.text = calculateTime().toString()
                        }
                    }
                }

            }


    }


    //TODO guardarlos en el Shared Preferences


    private fun showModifyDialog(){
        val dialog = ModifyValueDialogFragment()
        dialog.show(childFragmentManager, "ModifyValueDialogFragment")
    }



    private fun calculateBalance() : Int {
         outgoings =  tvOutgoingsAmount.text.toString().toInt()
         income =  tvIncomeAmount.text.toString().toInt()

        balance = income - outgoings

        return balance

    }

    private fun calculateNeed() : Int {
         goal =  tvGoalAmount.text.toString().toInt()
         saving =  tvSavings.text.toString().toInt()

        need =  goal - saving

        return need

    }

    private fun calculateTime(): Int {

        var time = 0

            if(balance != 0){
        time = need / balance
        }

        if(time < 1 && balance > 0) time = 1

        if(goal == 0 || balance <= 0 ) time = 0

        return time
    }



}









