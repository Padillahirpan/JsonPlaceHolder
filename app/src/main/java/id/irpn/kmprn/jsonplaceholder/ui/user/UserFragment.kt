package id.irpn.kmprn.jsonplaceholder.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.UserAlbum
import id.irpn.kmprn.core.ui.AlbumAdapter
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentUserBinding
import id.irpn.kmprn.jsonplaceholder.ui.photoalbum.PhotoAlbumFragment
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

private const val ARG_PARAM1 = "param1"

class UserFragment : Fragment(), AlbumAdapter.AlbumInterface {

    private var param1: Int? = null

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter(this)
    }

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        binding.rvAlbum.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter
        }
    }

    private fun observeData() {
        userViewModel.userAlbums.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    dismissLoading()
                    it.data?.let { albums ->
                        albumAdapter.updateListUserAlbum(albums)
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

    override fun onItemAlbumClicked(userAlbum: UserAlbum) {
        activity?.apply {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, PhotoAlbumFragment.newInstance(userAlbum.id))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            param1: Int,
        ) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}