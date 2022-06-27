package io.github.andrethlckr.tvmaze.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureAppBar()
    }

    private fun configureAppBar() {
        configureHomeButton()
    }

    private fun configureHomeButton() {
        val navHostFragment = getNavHostFragment()
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(binding.appBar)

        binding.appBar.setNavigationOnClickListener {
            if (onBackPressedDispatcher.hasEnabledCallbacks()) onBackPressed()
            else navController.navigateUp(appBarConfiguration)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.shows_fragment) binding.appBar.navigationIcon = null
            else binding.appBar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        }
    }

    private fun getNavHostFragment() = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
}
