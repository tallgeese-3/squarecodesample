package lightcycleconsulting.com.squarecodesample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lightcycleconsulting.com.squarecodesample.R
import lightcycleconsulting.com.squarecodesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}