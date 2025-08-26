package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.databinding.MainActivityBinding
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        val controlador = navHostFragment.navController

        controlador.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
            viewModel.componente.observe(this, Observer { temComponentes ->
                if (temComponentes.appBar) {
                    supportActionBar?.show()
                } else {
                    supportActionBar?.hide()
                }
                if (temComponentes.bottomNavigation) {
                    binding.mainActivityBottomNavigation.visibility = VISIBLE
                } else {
                    binding.mainActivityBottomNavigation.visibility = GONE
                }
            })
        }
        binding.mainActivityBottomNavigation.setupWithNavController(controlador)
    }
}
