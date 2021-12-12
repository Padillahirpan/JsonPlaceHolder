package id.irpn.kmprn.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.irpn.kmprn.core.databinding.LayoutItemAlbumBinding
import id.irpn.kmprn.core.domain.model.UserAlbum

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class AlbumAdapter(
    private val listener: AlbumInterface
): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var listUserAlbum = ArrayList<UserAlbum>()

    fun updateListUserAlbum(userAlbums: List<UserAlbum>) {
        listUserAlbum.clear()
        listUserAlbum.addAll(userAlbums)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = LayoutItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(listUserAlbum[position])
    }

    override fun getItemCount(): Int {
        return listUserAlbum.size
    }

    inner class AlbumViewHolder(
        private val binding: LayoutItemAlbumBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserAlbum) {
            with(binding) {
                tvTitle.text = item.title

                this.root.setOnClickListener {
                    listener.onItemAlbumClicked(item)
                }
            }
        }
    }

    interface AlbumInterface {
        fun onItemAlbumClicked(userAlbum: UserAlbum)
    }
}