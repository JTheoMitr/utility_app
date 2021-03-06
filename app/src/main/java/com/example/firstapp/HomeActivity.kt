package com.example.firstapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import java.lang.NullPointerException

class HomeActivity : AppCompatActivity() {
    lateinit var homeTv:TextView
    var TAG = HomeActivity::class.java.simpleName


    //from storage to ram
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Employee.COMPANY_NAME

        homeTv = findViewById(R.id.tvHome)
        Log.i(TAG, "creating")
        //       String name = getIntent().getExtras().getString("namekey");
        /*var name = intent.extras?.getString("namekey")
         homeTv.text = name*/
    }

    //ui is visible - user can click button
    override fun onStart() {
        super.onStart()
        Log.v(TAG, "starting")
        add(10,20);

    }


    //waking up
    override fun onResume() {
        super.onResume()
        Log.w(TAG, "resuming -- restore the app state")

    }

    //partially visible --- sleep
    override fun onPause() {
        super.onPause()
        Log.i(TAG, "pausing-- store app state")

    }

    //hibernate in windows - pushed out of ram into storage , why not purge
    override fun onStop() {
        super.onStop()
        Log.e(TAG, "stopping")

    }

    //activity is purged -- no longer exists in memory or storage
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "destroying -- clean up(release resource)")

    }

    fun handleClick(view: android.view.View) {
        when(view.id){
            R.id.btnDial -> {startDialer()}
            R.id.btnTimer -> {startTimer("Tea is Ready", 10)}
            R.id.btnRecycler -> {startRecycler()}
        }

    }

    private fun startRecycler() {
        val hIntent = Intent(this, RecyclerActivity::class.java) //explicit intent

        startActivityForResult(hIntent, 123)
        Log.i(TAG, "starting recycler activity")
    }

    fun startTimer(message: String, seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun startDialer() {
        val dIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456")) //implicit intent

        startActivity(dIntent)
    }

    fun closeActivity(view: android.view.View) { //step 2
        Log.i(TAG,"closeActivity");
        //send the contact back to main activity
        var etContact = findViewById<EditText>(R.id.etContact)
        var phno = etContact.text.toString()
        var dataIntent : Intent = Intent()
        dataIntent.putExtra("phonenum", phno)
        setResult(RESULT_OK,dataIntent)
        finish()
    }

    /**
     * this method is for adding two nos
     */
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}