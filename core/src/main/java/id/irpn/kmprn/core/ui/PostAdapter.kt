package id.irpn.kmprn.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.irpn.kmprn.core.databinding.LayoutItemPostBinding
import id.irpn.kmprn.core.domain.model.Posts

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class PostAdapter(
    private val listener: PostAdapterListener
): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var listPost = ArrayList<Posts>()

    fun updateListPost(posts: List<Posts>) {
        listPost.clear()
        listPost.addAll(posts)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = LayoutItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(listPost[position])
    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    inner class PostViewHolder(private val binding: LayoutItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Posts) {
            with(binding) {
                tvPostTitle.text = item.title
                tvPostBody.text = item.body

                tvUsername.text = item.user?.username ?: "-"
                tvUserCompany.text = item.user?.companyName?: "-"

                this.root.setOnClickListener {
                    listener.onItemPostClicked(item)
                }
            }
        }
    }

    interface PostAdapterListener {
        fun onItemPostClicked(posts: Posts)
    }
}