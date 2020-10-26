package com.example.epic_image.ui.epics

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.epic_image.R
import kotlinx.android.synthetic.main.activity_epics.*


class EpicsActivity : AppCompatActivity() {
    companion object {
        const val TAG = "EpicsActivity"
        const val OBJECT_DATE = "OBJECT_DATE"
        const val REQUEST_CODE = 200
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()


    }

    private fun init() {
        setContentView(R.layout.activity_epics)

        intent?.let {
            if (it.getStringExtra("EXTRA_DATE") != null) {
                val date = it.getStringExtra("EXTRA_DATE")
                toolbarEpics.title = date
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contentEpics, EpicsFragment.newInstance(date.toString()))
                    .commitNow()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            (supportFragmentManager.findFragmentById(R.id.contentEpics) as EpicsFragment).updateList()
        }

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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}