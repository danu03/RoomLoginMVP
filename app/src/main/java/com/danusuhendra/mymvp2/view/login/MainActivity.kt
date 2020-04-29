package com.danusuhendra.mymvp2.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danusuhendra.mymvp2.HomeActivity
import com.danusuhendra.mymvp2.PreferenceHelper
import com.danusuhendra.mymvp2.R
import com.danusuhendra.mymvp2.database.AppDatabase
import com.danusuhendra.mymvp2.model.Users
import com.danusuhendra.mymvp2.presenter.LoginPresenter
import com.danusuhendra.mymvp2.repository.UsersRepository
import com.danusuhendra.mymvp2.view.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var preferenceHelper : PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appDatabase = AppDatabase.getDatabase(this)
        val repository = UsersRepository(appDatabase.usersDao())
        val presenter = LoginPresenter(repository, this)
        preferenceHelper = PreferenceHelper(this)
        if (preferenceHelper.login){
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
        btnToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            val users = Users(null, username, password)
            presenter.loginUsers(users)
        }
    }

    override fun showUsers(users: Users) {
        preferenceHelper.username = users.username
    }

    override fun loginResult(status: Boolean) {
        if (status){
            preferenceHelper.login = status
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun showMessage(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}
