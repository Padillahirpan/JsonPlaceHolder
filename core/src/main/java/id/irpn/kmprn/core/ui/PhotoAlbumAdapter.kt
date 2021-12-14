package id.irpn.kmprn.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import id.irpn.kmprn.core.R
import id.irpn.kmprn.core.databinding.LayoutItemPhotoAlbumBinding
import id.irpn.kmprn.core.domain.model.PhotoAlbum

/**
 * Created by irpanpadillah on 12/12/21
 * Email: padillahirpan8@gmail.com
 */


class PhotoAlbumAdapter(
    private val listener: PhotoAlbumListener
): RecyclerView.Adapter<PhotoAlbumAdapter.PhotoAlbumViewHolder>() {
    private var listPhotoAlbum = ArrayList<PhotoAlbum>()

    fun updateListPhotoAlbum(userAlbums: List<PhotoAlbum>) {
        listPhotoAlbum.clear()
        listPhotoAlbum.addAll(userAlbums)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAlbumViewHolder {
        val binding = LayoutItemPhotoAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoAlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoAlbumViewHolder, position: Int) {
        holder.bind(listPhotoAlbum[position])
    }

    override fun getItemCount(): Int = listPhotoAlbum.size

    inner class PhotoAlbumViewHolder(
        private val binding: LayoutItemPhotoAlbumBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoAlbum) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(item.thumbnailUrl + ".png")
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_photos)
                    .priority(Priority.IMMEDIATE)
                    .into(ivPhotoAlbum)

                tvTitlePhotoAlbum.text = item.title
                root.setOnClickListener {
                    listener.onPhotoAlbumClicked(item)
                }
            }
        }
    }

    interface PhotoAlbumListener {
        fun onPhotoAlbumClicked(photoAlbum: PhotoAlbum)
    }
}