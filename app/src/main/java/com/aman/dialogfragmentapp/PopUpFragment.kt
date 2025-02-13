package com.aman.dialogfragmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aman.dialogfragmentapp.databinding.FragmentFirstBinding
import com.aman.dialogfragmentapp.databinding.FragmentPopUpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopUpFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding: FragmentPopUpBinding by lazy {
        FragmentPopUpBinding.inflate(layoutInflater)
    }
    private val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPassToPrevious.setOnClickListener {
            if(binding.tvText.text.toString().trim().isNullOrEmpty()){
                binding.tvText.error = mainActivity.resources.getString(R.string.enter_the_value_to_pass_to_previous_fragment)
            } else{
                val result = Bundle().apply {
                    putString(ApiConstants.bundleValue, binding.tvText.text.toString().trim())
                }
                parentFragmentManager.setFragmentResult(ApiConstants.requestKey, result)
                dismiss()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PopUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}