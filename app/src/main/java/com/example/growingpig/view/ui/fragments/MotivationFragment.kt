package com.example.growingpig.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentMotivationBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.concurrent.timerTask




class MotivationFragment : Fragment() {

    private lateinit var binding: FragmentMotivationBinding

    private  lateinit  var imageSwitcher: ImageSwitcher

    private lateinit var btnModifyText : Button

    private lateinit var tvTextMotivation: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motivation, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMotivationBinding.bind(view)

        imageSwitcher  = binding.imageSwitcher
        btnModifyText = binding.btnTextModify
        tvTextMotivation = binding.tvMotivation

        btnModifyText.setOnClickListener {
            modifytext()
        }






        val images = intArrayOf(R.drawable.lion, R.drawable.dados)


         imageSwitcher?.setFactory {
             val imgView = ImageView(activity?.applicationContext)
             imgView.scaleType = ImageView.ScaleType.CENTER_CROP
             imgView
         }


        val fadeIn = AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.fade_in )
        val fadeOut = AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.fade_out)
        imageSwitcher.setInAnimation(fadeIn)
        imageSwitcher.setOutAnimation(fadeOut)

        startSlider(images)

    }


    private fun startSlider(images: IntArray) {
        var position = 0
        val DURATION: Long = 9000

        Timer().scheduleAtFixedRate(timerTask {
            activity?.runOnUiThread(Runnable {

                imageSwitcher?.setImageResource(images[position])
                position++
                if(position == images.size) position = 0

            })


        },0,DURATION)

        if(position == images.size){position = 0}

    }



    private fun modifytext() {
        showDialog()
        childFragmentManager.setFragmentResultListener("textKey", this.viewLifecycleOwner){_, bundle ->
            tvTextMotivation.text = bundle.getString("text", "")
        }
    }

    private fun showDialog() {
        val dialog = ModifyTextDialogFragment()
        dialog.show(childFragmentManager, "ModifyTextDialogFragment")
    }


}