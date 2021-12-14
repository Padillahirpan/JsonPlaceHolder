package id.irpn.kmprn.jsonplaceholder.ui.photoalbum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.core.ui.PhotoAlbumAdapter
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentPhotoAlbumBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

private const val ARG_PARAM1 = "param1"

class PhotoAlbumFragment: Fragment(), PhotoAlbumAdapter.PhotoAlbumListener {
    private var albumId: Int? = null

    private var _binding: FragmentPhotoAlbumBinding? = null
    private val binding get() = _binding!!

    private val photoAlbumAdapter: PhotoAlbumAdapter by lazy {
        PhotoAlbumAdapter(this)
    }

    private val photoAlbumViewModel: PhotoAlbumViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeData()

        albumId?.let { id ->
            photoAlbumViewModel.getPhotoAlbum(id)
        }
    }

    private fun setupAdapter() {
        binding.rvAlbum.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = photoAlbumAdapter
        }
    }

    private fun observeData() {
        photoAlbumViewModel.photoAlbums.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    dismissLoading()
                    it.data?.let { photos ->
                        photoAlbumAdapter.updateListPhotoAlbum(photos)
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

    override fun onPhotoAlbumClicked(photoAlbum: PhotoAlbum) {
        activity?.apply {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, PhotoPreviewFragment.newInstance(photoAlbum))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(albumId: Int) =
            PhotoAlbumFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, albumId)
                }
            }
    }
}