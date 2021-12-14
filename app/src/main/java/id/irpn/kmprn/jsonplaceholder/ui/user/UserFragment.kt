package id.irpn.kmprn.jsonplaceholder.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.User
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

private const val EXTRA_USER = "param1"

class UserFragment : Fragment(), AlbumAdapter.AlbumInterface {

    private var user: User? = null

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter(this)
    }

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable<User>(EXTRA_USER)
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

        user?.let {
            setupDataUser(it)
            userViewModel.getUserAlbum(it.id)
        } ?: setupEmptyUser()
    }

    private fun setupDataUser(user: User) {
        binding.ctDetailUser.apply {
            tvName.text = user.name
            tvUsername.text = user.username
            tvPhone.text = user.phone
            tvEmail.text = user.email
            tvUrlWebsite.text = user.website
            tvAddress.text = getString(R.string.text_user_address, user.street?:"", user.suite?:"", user.city?:"")
            tvZipcode.text = user.zipCode
            tvCompanyName.text = user.companyName
            tvCompanyRole.text = user.companyCatchPhrase
        }
    }
    private fun setupEmptyUser() {

    }

    private fun setupAdapter() {
        binding.rvAlbum.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = albumAdapter
        }
    }

    private fun observeData() {
        userViewModel.usersAlbums.observe(viewLifecycleOwner) {
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
        fun newInstance(user: User) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_USER, user)
                }
            }
    }
}