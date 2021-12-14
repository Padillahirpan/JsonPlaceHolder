package id.irpn.kmprn.jsonplaceholder.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.irpn.kmprn.jsonplaceholder.R
import id.irpn.kmprn.jsonplaceholder.databinding.ActivityMainBinding
import id.irpn.kmprn.jsonplaceholder.ui.post.PostFragment

/**
 * Created by irpanpadillah on 11/12/21
 * Email: padillahirpan8@gmail.com
 */

class MainActivity: AppCompatActivity(){

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
}