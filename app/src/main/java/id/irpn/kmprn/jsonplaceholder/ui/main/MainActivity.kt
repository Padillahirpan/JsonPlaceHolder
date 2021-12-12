package id.irpn.kmprn.jsonplaceholder.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.irpn.kmprn.core.data.Resource
import id.irpn.kmprn.core.domain.model.Posts
import id.irpn.kmprn.core.ui.PostAdapter
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.ActivityMainBinding
import id.irpn.kmprn.jsonplaceholder.ui.post.PostFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity(), PostAdapter.PostAdapterListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setupUi()
    }

    private fun setupUi() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, PostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onItemPostClicked(posts: Posts) {

    }
}