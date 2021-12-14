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


class PostFragment : Fragment(), PostAdapter.PostAdapterListener {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostViewModel by viewModel()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter(this)
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
                .replace(R.id.frame_layout, PostDetailFragment.newInstance(posts))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }
}