package com.example.growingpig.view.ui.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentMotivationBinding
import com.example.growingpig.view.adapter.ViewPagerAdapter





class MotivationFragment : Fragment(), Dialog {

    private lateinit var binding: FragmentMotivationBinding

    private lateinit var btnModifyText : Button

    private lateinit var tvTextMotivation: TextView

    private val imageUrls =
        arrayListOf("https://images.pexels.com/photos/3077882/pexels-photo-3077882.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
            , "https://images.pexels.com/photos/584179/pexels-photo-584179.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")



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


        btnModifyText = binding.btnTextModify
        tvTextMotivation = binding.tvMotivation

        val viewPager = binding.viewPager
        val viewPagerAdapter = ViewPagerAdapter(requireActivity().applicationContext, imageUrls)
        viewPager.adapter = viewPagerAdapter



        btnModifyText.setOnClickListener {
            modifyText()
        }

    }


    private fun modifyText() {
        showDialog()
        childFragmentManager.setFragmentResultListener("textKey", this.viewLifecycleOwner){_, bundle ->
            tvTextMotivation.text = bundle.getString("text", "")
        }
    }

    override fun showDialog() {
        val dialog = ModifyTextDialogFragment()
        dialog.show(childFragmentManager, "ModifyTextDialogFragment")
    }


}