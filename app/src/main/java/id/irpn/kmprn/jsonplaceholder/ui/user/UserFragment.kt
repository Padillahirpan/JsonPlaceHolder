package id.irpn.kmprn.jsonplaceholder.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.UserAlbum
import id.irpn.kmprn.core.ui.AlbumAdapter
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentUserBinding
import org.koin.android.viewmodel.ext.android.viewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UserFragment : Fragment(), AlbumAdapter.AlbumInterface {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var albumAdapter: AlbumAdapter

    private val userViewModel: UserViewModel by viewModel()

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
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeData()
    }

    private fun setupAdapter() {
        albumAdapter = AlbumAdapter(this)
        binding.rvAlbum.apply {
            layoutManager = LinearLayoutManager(context)
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
                    binding.tvSizeList.text = it.data?.size.toString()
                    it.data?.let { albums ->
                        albumAdapter.updateListUserAlbum(albums)
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

    override fun onItemAlbumClicked(userAlbum: UserAlbum) {
        Log.d("xzy","this is itemAlbum: $userAlbum")
    }

    companion object {
        @JvmStatic
        fun newInstance(
//            param1: String,
//            param2: String
        ) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}