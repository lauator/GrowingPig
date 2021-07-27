package com.example.growingpig.view.ui.fragments


import android.content.Context
import android.content.SharedPreferences
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


//TODO optimizar codigo y optimizar xml con un constraint

//TODO los metodos de calcular los podria meter en una clase calculadora


class ProfileFragment : Fragment(), Dialog {


    private lateinit var binding: FragmentProfileBinding

    private lateinit var tvIncomeAmount: TextView
    private lateinit var tvOutgoingsAmount: TextView
    private lateinit var tvBalanceAmount: TextView
    private lateinit var tvSavingsAmount: TextView
    private lateinit var tvGoalAmount: TextView
    private lateinit var tvNeed: TextView
    private lateinit var tvTime: TextView

    private lateinit var btnModifyIncome: Button
    private lateinit var btnModifyOutgoings: Button
    private lateinit var btnModifySavings: Button
    private lateinit var btnModifyGoal: Button


    private var income: Int = 0
    private var outgoings: Int = 0
    private var goal: Int = 0
    private var saving: Int = 0
    private var balance: Int = 0
    private var need: Int = 0
    private var time: Int = 0


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
        tvSavingsAmount = binding.tvSavingsAmount
        tvGoalAmount = binding.tvGoalAmount
        tvNeed = binding.tvNeedAmount
        tvTime = binding.tvTime


        btnModifyIncome = binding.btnChangeIncomings
        btnModifyOutgoings = binding.btnChangeOutcomings
        btnModifyGoal = binding.btnGoalModify
        btnModifySavings = binding.btnSavingsModify

        verifyUserData()

        btnModifyIncome.setOnClickListener {
            modifyValue(1)
        }

        btnModifyOutgoings.setOnClickListener {
            modifyValue(2)

        }

        btnModifySavings.setOnClickListener {
            modifyValue(3)
        }

        btnModifyGoal.setOnClickListener {
            modifyValue(4)
        }


    }

    private fun modifyValue(value: Int) {
        showDialog()

        childFragmentManager.setFragmentResultListener(
            "key",
            this.viewLifecycleOwner
        ) { _, bundle ->
            when (value) {
                1 -> {
                    tvIncomeAmount.text = bundle.getString("incoming", "0")
                    tvBalanceAmount.text = calculateBalance().toString()
                    if (calculateTime() > 99) {
                        tvTime.text = "+99"
                    } else {
                        tvTime.text = calculateTime().toString()
                    }
                    saveUserData()
                }
                2 -> {
                    tvOutgoingsAmount.text = bundle.getString("outgoings", "0")
                    tvBalanceAmount.text = calculateBalance().toString()
                    if (calculateTime() > 99) {
                        tvTime.text = "+99"
                    } else {
                        tvTime.text = calculateTime().toString()
                    }
                    saveUserData()
                }
                3 -> {
                    tvSavingsAmount.text = bundle.getString("saving", "0")
                    tvNeed.text = calculateNeed().toString()
                    if (calculateTime() > 99) {
                        tvTime.text = "+99"
                    } else {
                        tvTime.text = calculateTime().toString()
                    }
                    saveUserData()
                }
                4 -> {
                    tvGoalAmount.text = bundle.getString("goal", "0")
                    tvNeed.text = calculateNeed().toString()
                    if (calculateTime() > 99) {
                        tvTime.text = "+99"
                    } else {
                        tvTime.text = calculateTime().toString()
                    }
                    saveUserData()
                }
            }

        }


    }

    private fun saveUserData() {
        val preferences = activity?.getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        ) ?: return
        with(preferences.edit()) {
            putInt(getString(R.string.income_key), income)
            putInt(getString(R.string.out_goings_key), outgoings)
            putInt(getString(R.string.balance_key), balance)
            putInt(getString(R.string.savings_key), saving)
            putInt(getString(R.string.goal_key), goal)
            putInt(getString(R.string.need_key), need)
            putInt(getString(R.string.time_key), time)

            apply()
        }

    }

    private fun verifyUserData() {
        val preferences = activity?.getSharedPreferences(
            getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        ) ?: return

        tvIncomeAmount.text = preferences.getInt(getString(R.string.income_key), 0).toString()
        tvOutgoingsAmount.text =
            preferences.getInt(getString(R.string.out_goings_key), 0).toString()
        tvBalanceAmount.text = preferences.getInt(getString(R.string.balance_key), 0).toString()
        tvSavingsAmount.text = preferences.getInt(getString(R.string.savings_key), 0).toString()
        tvGoalAmount.text = preferences.getInt(getString(R.string.goal_key), 0).toString()
        tvNeed.text = preferences.getInt(getString(R.string.need_key), 0).toString()
        tvTime.text = preferences.getInt(getString(R.string.time_key), 0).toString()

    }


    override fun showDialog() {
        val dialog = ModifyValueDialogFragment()
        dialog.show(childFragmentManager, "ModifyValueDialogFragment")
    }


    private fun calculateBalance(): Int {
        outgoings = tvOutgoingsAmount.text.toString().toInt()
        income = tvIncomeAmount.text.toString().toInt()

        balance = income - outgoings

        return balance

    }

    private fun calculateNeed(): Int {
        goal = tvGoalAmount.text.toString().toInt()
        saving = tvSavingsAmount.text.toString().toInt()

        need = goal - saving

        return need

    }

    private fun calculateTime(): Int {

        time = 0

        if (balance != 0) {
            time = need / balance
        }

        if (time < 1 && balance > 0) time = 1

        if (goal == 0 || balance <= 0) time = 0

        return time
    }


}









