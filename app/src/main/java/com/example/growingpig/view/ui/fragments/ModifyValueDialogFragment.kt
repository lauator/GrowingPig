package com.example.growingpig.view.ui.fragments


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.growingpig.R
import com.example.growingpig.databinding.DialogModifyValueBinding
import java.lang.IllegalStateException


class ModifyValueDialogFragment : DialogFragment() {

    private lateinit var binding: DialogModifyValueBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.dialog_modify_value, null)

            binding = DialogModifyValueBinding.bind(view)


            builder.setView(view)

                .setPositiveButton(
                    R.string.update
                ) { _, _ ->


                    val etValue = binding.tvValue.text.toString()

                    val bundle = Bundle()
                    bundle.putString("incoming", etValue)
                    bundle.putString("outgoings", etValue)
                    bundle.putString("saving", etValue)
                    bundle.putString("goal", etValue)


                    parentFragmentManager.setFragmentResult("key", bundle)


                    dialog?.dismiss()

                }

                .setNegativeButton(
                    R.string.cancel
                ) { _, _ ->

                    dialog?.cancel()

                }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }


}
