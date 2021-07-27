package com.example.growingpig.view.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.growingpig.R
import com.example.growingpig.databinding.DialogModifyTextBinding
import java.lang.IllegalStateException

class ModifyTextDialogFragment : DialogFragment() {

    private lateinit var binding: DialogModifyTextBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_modify_text, null)
            binding = DialogModifyTextBinding.bind(view)

            builder.setView(view)

                .setPositiveButton(R.string.update) { _, _ ->
                    val etText = binding.tvValue.text.toString()

                    parentFragmentManager.setFragmentResult("textKey", bundleOf("text" to etText))



                    dialog?.dismiss()

                }

                .setNegativeButton(R.string.cancel) { _, _ ->

                    dialog?.cancel()

                }

            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")

    }

}