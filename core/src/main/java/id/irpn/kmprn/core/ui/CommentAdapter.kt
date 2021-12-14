package id.irpn.kmprn.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.irpn.kmprn.core.databinding.LayoutItemAlbumBinding
import id.irpn.kmprn.core.databinding.LayoutItemCommentBinding
import id.irpn.kmprn.core.domain.model.PostComment

/**
 * Created by irpanpadillah on 12/12/21
 * Email: padillahirpan8@gmail.com
 */

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var listComment = ArrayList<PostComment>()

    fun updateComments(comments: List<PostComment>) {
        listComment.clear()
        listComment.addAll(comments)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = LayoutItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int {
        return listComment.size
    }

    inner class CommentViewHolder(
        private val binding: LayoutItemCommentBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostComment) {

            with(binding) {
                tvUsername.text = item.name
                tvEmail.text = item.email
                tvCommentBody.text = item.body
            }
        }
    }
}