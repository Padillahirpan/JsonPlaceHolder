package id.irpn.kmprn.jsonplaceholder.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.ui.PostAdapter
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentPostBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PostFragment : Fragment(), PostAdapter.PostAdapterListener {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostViewModel by viewModel()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter(this)
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
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = postAdapter
        }
    }

    private fun observeData() {
        postViewModel.posts.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    dismissLoading()
                    it.data?.let { posts ->
                        postAdapter.updateListPost(posts)
                    }
                }
                is Resource.Error -> {
                    dismissLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun dismissLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemPostClicked(posts: Posts) {
        activity?.apply {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, PostDetailFragment.newInstance(posts.id))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }
}