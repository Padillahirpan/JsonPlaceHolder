package id.irpn.kmprn.jsonplaceholder.ui.photoalbum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.irpn.kmprn.core.domain.model.PhotoAlbum
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.FragmentPhotoPreviewBinding

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

private const val EXTRA_DATA = "param1"

class PhotoPreviewFragment : Fragment() {

    private var photoAlbum: PhotoAlbum? = null

    private var _binding: FragmentPhotoPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoAlbum = it.getParcelable<PhotoAlbum>(EXTRA_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAlbum?.let {
            binding.tvTitle.text = it.title
            Glide.with(this)
                .load(it.url+".png")
                .placeholder(R.drawable.placeholder_photos)
                .into(binding.photoView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(photo: PhotoAlbum) =
            PhotoPreviewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_DATA, photo)
                }
            }
    }
}