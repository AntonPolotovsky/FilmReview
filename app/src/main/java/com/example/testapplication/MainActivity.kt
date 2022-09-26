package com.example.testapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.presentation.filmactivity.view.FilmActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToMain()
    }
    private fun goToMain() {
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, FilmActivity::class.java)
            startActivity(intent)
            finish()
        }, 3300)
        startActivity(intent)
    }
//        binding.filmImage.alpha = 0f
//        binding.animate().setDuration(1500).alpha(1f).withEndAction {
//            val intent = Intent(this, FilmActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//            finish()
//        }


//        binding.filmImage.setOnClickListener {
//            val intent = Intent(this, FilmActivity::class.java)
//            startActivity(intent)
//        }
    }
//}
