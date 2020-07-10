package com.dorukkangal.geoalert.ui.main

import androidx.navigation.NavController
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.ui.base.BaseActivity
import com.dorukkangal.geoalert.ui.list.GeoAlertListFragment
import com.dorukkangal.geoalert.util.obtainNavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val navController: NavController by lazy {
        obtainNavHostFragment(
            fragmentManager = supportFragmentManager,
            fragmentTag = GeoAlertListFragment::class.java.name,
            navGraphId = R.navigation.nav_graph_geo_alert_list,
            containerId = R.id.fragmentContainerView
        ).navController
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        navController.navigateUp()
    }
}
