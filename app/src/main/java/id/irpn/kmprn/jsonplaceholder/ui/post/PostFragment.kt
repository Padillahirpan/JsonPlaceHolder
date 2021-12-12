package id.irpn.kmprn.jsonplaceholder.ui.post

import android.os.Bundle
import android.util.Log
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
import id.irpn.kmprn.jsonplaceholder.ui.user.UserFragment
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment(), PostAdapter.PostAdapterListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostViewModel by viewModel()

    private lateinit var postAdapter: PostAdapter

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
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        Log.d("xyz","onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("xyz","onViewCreated")

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        postAdapter = PostAdapter(this)
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context)
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
                    binding.tvSizeList.text = it.data?.size.toString()
                    it.data?.let { posts ->
                        postAdapter.updateListPost(posts)
                    }
                }
                is Resource.Error -> {
                    dismissLoading()
                    binding.tvSizeList.text = it.message
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
        Log.d("xzy","this is itemPost: $posts")
        activity?.apply {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, UserFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }
}