package com.example.voteinformed

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.voteinformed.data.repository.VoteInformed_Repository
import com.example.voteinformed.databinding.ActivityMainBinding

import com.example.voteinformed.data.api.CommitteeApi
import com.example.voteinformed.data.api.CommitteeDto
import com.example.voteinformed.data.api.RetrofitClient
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ------------------ API CALL EXAMPLE ---------------------
        val api = RetrofitClient.getClient().create(CommitteeApi::class.java)
        val filter = "(BodyTypeName eq 'Committee') and BodyActiveFlag eq 1"

        val call = api.getCommittees("Uvxb0j9syjm3aI8h46DhQvnX5skN4aSUL0x_Ee3ty9M.ew0KICAiVmVyc2lvbiI6IDEsDQogICJOYW1lIjogIk5ZQyByZWFkIHRva2VuIDIwMTcxMDI2IiwNCiAgIkRhdGUiOiAiMjAxNy0xMC0yNlQxNjoyNjo1Mi42ODM0MDYtMDU6MDAiLA0KICAiV3JpdGUiOiBmYWxzZQ0KfQ", filter)
        call.enqueue(object : Callback<List<CommitteeDto>> {
            override fun onResponse(
                call: Call<List<CommitteeDto>>,
                response: Response<List<CommitteeDto>>
            ) {
                if (response.isSuccessful) {
                    val committees = response.body()
                    committees?.forEach {
                        Log.d("API_TEST", "Committee: ${it.BodyName}")
                    }
                } else {
                    Log.e("API_TEST", "Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CommitteeDto>>, t: Throwable) {
                Log.e("API_TEST", "Error: ${t.message}")
            }
        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //------------------ DATABASE INITIALIZATION -----------------------------------------------
        val voteInformedRepository = VoteInformed_Repository(applicationContext)

        //------------------------------------------------------------------------------------------

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



}