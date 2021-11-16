package hofy.notino.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import hofy.notino.presentation.databinding.ActivityMainBinding
import hofy.notino.presentation.ui.BaseFragment
import hofy.notino.presentation.ui.custom.LockingAppBarLayoutBehavior

class MainActivity : AppCompatActivity(), BaseFragment.IMainImageInteractionListener,
    NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_products
            )
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onMainImageChange(res: Int) {
        binding.appBarLayout.setExpanded(true, true)
        binding.toolbarImage = res
    }

    override fun onMainImageChange(url: String?) {
        binding.appBarLayout.setExpanded(true, true)
        binding.toolbarImage = url
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }

    override fun setTitle(title: CharSequence?) {
        binding.collapsingToolbarLayout.title = title
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        title = destination.label
        when (destination.id) {
            R.id.navigation_product_detail -> {
                binding.appBarLayout.setExpanded(true)
                ((binding.appBarLayout.layoutParams as CoordinatorLayout.LayoutParams).behavior as LockingAppBarLayoutBehavior).shouldScroll =
                    true
            }
            else -> {
                binding.appBarLayout.setExpanded(false)
                ((binding.appBarLayout.layoutParams as CoordinatorLayout.LayoutParams).behavior as LockingAppBarLayoutBehavior).shouldScroll =
                    false
            }
        }
    }
}