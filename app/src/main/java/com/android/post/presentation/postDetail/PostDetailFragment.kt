package com.android.post.presentation.postDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.post.R
import com.android.post.databinding.FragmentPostDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PostDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding

    private val args : PostDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        bindPostDetail()
    }

    private fun bindPostDetail() {
        binding.post = args.postData

        binding.webview.loadDataWithBaseURL(null, args.postData.body, "text/html", "UTF-8", null);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostDetailFragment()
    }
}