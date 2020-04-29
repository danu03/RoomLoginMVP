package com.danusuhendra.mymvp2.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danusuhendra.mymvp2.R
import com.danusuhendra.mymvp2.database.AppDatabase
import com.danusuhendra.mymvp2.model.Users
import com.danusuhendra.mymvp2.presenter.RegisterPresenter
import com.danusuhendra.mymvp2.repository.UsersRepository
import com.danusuhendra.mymvp2.view.login.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val appDatabase = AppDatabase.getDatabase(this)
        val repository = UsersRepository(appDatabase.usersDao())
        val presenter = RegisterPresenter(repository, this)
        btnRegister.setOnClickListener {
            val username = edtUsernameRegister.text.toString()
            val password = edtPasswordRegister.text.toString()
            val users = Users(null, username, password)
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill the Form", Toast.LENGTH_SHORT).show()
            }else{
                presenter.registerUsers(users)
            }
        }
    }

    override fun showRegister(status: Boolean) {
        if (status){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun showMessage(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}
