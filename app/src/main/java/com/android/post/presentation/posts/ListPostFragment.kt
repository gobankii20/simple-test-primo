package com.android.post.presentation.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.android.post.R
import com.android.post.databinding.FragmentListPostBinding
import com.android.post.presentation.PostsViewModel
import com.android.post.utils.isNetworkAvailable
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ListPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListPostFragment : Fragment() {

    private lateinit var binding: FragmentListPostBinding
    private var mAdapter: PostsAdapter? = PostsAdapter()
    private val postViewModel: PostsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_post, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.postsRecyclerView.adapter = mAdapter

        if (requireActivity().isNetworkAvailable()) {
            postViewModel.getPosts()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(postViewModel) {

            postsData.observe(viewLifecycleOwner, Observer {
                binding.postsProgressBar.visibility = GONE
                mAdapter?.mPostList = it
            })

            messageData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, LENGTH_LONG).show()
            })

            showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
                binding.postsProgressBar.visibility = if (isVisible) VISIBLE else GONE
            })
        }
        onClickListener()
    }

    private fun onClickListener() {
        mAdapter?.onClickListener = {
            val action = ListPostFragmentDirections.actionListPostFragmentToPostDetailFragment(it)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListPostFragment()
    }
}