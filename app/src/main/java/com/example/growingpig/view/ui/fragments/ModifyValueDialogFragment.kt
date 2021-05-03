package com.example.growingpig.view.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.growingpig.R
import com.example.growingpig.databinding.DialogModifyValueBinding
import java.lang.ClassCastException

import java.lang.IllegalStateException



private lateinit var binding: DialogModifyValueBinding


class ModifyValueDialogFragment: DialogFragment() {




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let{
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            var view = inflater.inflate(R.layout.dialog_modify_value, null)

            binding = DialogModifyValueBinding.bind(view)





            //TODO pasarle cosas al builder para construir mi alertDialog (android devs)
            builder.setView(view)

                    .setPositiveButton(R.string.update,
                            DialogInterface.OnClickListener(){ dialog, id ->


                                val etValue = binding.tvValue.text.toString()

                                parentFragmentManager.setFragmentResult("keyI", bundleOf("incoming" to etValue))
                                parentFragmentManager.setFragmentResult("keyO", bundleOf("outcoming" to etValue))







                                getDialog()?.dismiss()



                    })

                    .setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener(){ dialog, id ->

                        getDialog()?.cancel()



                    })




            builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")

        }










    }
