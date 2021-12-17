package sk.itsovy.android.calllog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    val model: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ak ide o portrait rezim tak potrebujem nastavit prislusny fragment
        val isPortrait = findViewById<View>(R.id.activity_portrait) != null
        if (isPortrait) {
            supportFragmentManager.commit {
                replace(R.id.activity_portrait, MasterFragment())
            }

            model.selectedCall.observe(this) {
               supportFragmentManager.commit {
                   replace(R.id.activity_portrait, DetailFragment())
                   addToBackStack(null)
               }
            }
        }


    }
}