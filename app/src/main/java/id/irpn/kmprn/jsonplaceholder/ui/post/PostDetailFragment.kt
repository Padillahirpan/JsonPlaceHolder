package id.irpn.kmprn.jsonplaceholder.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.ui.CommentAdapter
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentPostDetailBinding
import id.irpn.kmprn.jsonplaceholder.ui.user.UserFragment
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

private const val EXTRA_POST = "param1"

class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostViewModel by viewModel()

    private var post: Posts? = null

    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.getParcelable<Posts>(EXTRA_POST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupAdapter()
        observeData()

        post?.let {
            setupDataPost(it)
            postViewModel.getPostComment(it.id)
        }
    }

    private fun setupDataPost(post: Posts) {
        binding.apply {
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
            tvPostUsername.text = getString(R.string.text_post_by, post.user?.username ?: "-")
        }
    }

    private fun setupUI() {
        binding.apply {
            tvPostUsername.text = getString(R.string.text_post_username, post?.user?.username ?: "-")
            tvPostUsername.setOnClickListener {
                post?.user?.let { user ->
                    activity?.apply {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, UserFragment.newInstance(user))
                            .addToBackStack(null)
                            .commit()
                    }
                } ?: showUserNotFound()
            }
        }
    }

    private fun showUserNotFound() {
        Snackbar.make(binding.root, getString(R.string.text_user_not_found), Snackbar.LENGTH_LONG).show()
    }

    private fun setupAdapter() {
        binding.rvComments.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = commentAdapter
        }
    }

    private fun observeData() {
        postViewModel.comments.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    dismissLoading()
                    it.data?.let { photos ->
                        binding.tvHeaderComment.text = getString(R.string.text_comment_count, photos.size)
                        commentAdapter.updateComments(photos)
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

    companion object {
        @JvmStatic
        fun newInstance(post: Posts) =
            PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_POST, post)
                }
            }
    }
}