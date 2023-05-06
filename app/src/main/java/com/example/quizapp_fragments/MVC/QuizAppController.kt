package com.example.quizapp_fragments.MVC

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.quizapp_fragments.Fragments.*
import com.example.quizapp_fragments.R
import com.google.android.material.navigation.NavigationView



class QuizAppController : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var quizappModel: QuizAppModel
    lateinit var TFquestions: List<QuizAppModel.TFQuizQuestion>
    lateinit var MCQquestionsData: List<QuizAppModel.MCQQuizQuestion>
    var selectedOption = 0
    private lateinit var drawerLayout: DrawerLayout

    // This is the main Activity where the Controlling is happening ,like the fragment swtiching
    // And the navigation drawer is used

//    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        quizappModel = QuizAppModel(this)
        TFquestions = quizappModel.getQuestionsForTF(this)
        MCQquestionsData = quizappModel.getQuestionsForMCQ(this)




        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flcontainer1, loginFragment)
            .commit()
        navigationView.setCheckedItem(R.id.nav_home)


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.flcontainer1, SubjectListFragment())
                .commit()
            R.id.nav_history -> supportFragmentManager.beginTransaction()
                .replace(R.id.flcontainer1, HistoryFragment())
                .commit()
            R.id.nav_settings-> supportFragmentManager.beginTransaction()
                .replace(R.id.flcontainer1, DefaultQuizFragment())
                .commit()
            R.id.nav_logout ->supportFragmentManager.beginTransaction()
                .replace(R.id.flcontainer1, LoginFragment())
                .commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            onBackPressedDispatcher.onBackPressed()
        }
    }




}