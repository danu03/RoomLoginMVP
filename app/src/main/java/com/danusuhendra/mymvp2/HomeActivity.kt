package com.danusuhendra.mymvp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danusuhendra.mymvp2.view.login.MainActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val preferenceHelper = PreferenceHelper(this)
        tvUsername.text = preferenceHelper.username
        btnLogout.setOnClickListener {
            preferenceHelper.login =  false
            preferenceHelper.username = ""
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
