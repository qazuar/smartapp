package com.smartai.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smartai.smartAI.R
import com.smartai.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /*
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */

        val fragment = SearchFragment()
        showFragment(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                println("menu item settings")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Show a fragment with custom backstack addition policy
     */
    fun showFragment(
        fragment: Fragment,
        tag: String = fragment.javaClass.simpleName,
        addToBackStack: Boolean = true,
        allowStateLoss: Boolean = false,
        animate: Boolean = false
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        /*
        if (animate) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }
        */

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)

        if (!supportFragmentManager.isStateSaved) {
            fragmentTransaction.commit()
        } else if (allowStateLoss) {
            fragmentTransaction.commitAllowingStateLoss()
        }

        // Remember selected fragment.
        //selectedTabFragment = WeakReference(fragment)

        // Perform any pending navigation actions from deep link.
        //pendingNavigationAction?.let { consumePendingNavigationAction(it, fragment) }
    }

}