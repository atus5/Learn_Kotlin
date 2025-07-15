package com.example.nhatrobudai

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhatrobudai.DataBase.MyDB
import com.example.nhatrobudai.Fragment.SettingFragment
import com.example.nhatrobudai.databinding.ActivityLoginBinding
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {


    fun getUserNameFromDB(): String? {
        val mydb = MyDB(applicationContext)
        val db = mydb.readableDatabase
        val edtUsername = binding.edtUsername.text.toString()

        val cursor =
            db.rawQuery("SELECT UserName FROM Account WHERE UserName = ?", arrayOf(edtUsername))
        var userName: String? = null
        if (cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndexOrThrow("UserName"))
        }
        cursor.close()
        db.close()
        return userName
    }

    fun getPassFromDB(): String? {
        val mydb = MyDB(applicationContext)
        val db = mydb.readableDatabase
        val edtPass = binding.edtPass.text.toString()
        val cursor = db.rawQuery("SELECT Pass FROM Account WHERE Pass = ?", arrayOf(edtPass))
        var passWord: String? = null
        if (cursor.moveToFirst()) {
            passWord = cursor.getString(cursor.getColumnIndexOrThrow("Pass"))
        }
        cursor.close()
        db.close()
        return passWord
    }

    fun checkLoginFirebase() {
        val edtUsername = binding.edtUsername.text.toString().trim()
        val edtPass = binding.edtPass.text.toString().trim()

        val dbRef = FirebaseDatabase.getInstance().getReference("users")

        dbRef.child(edtUsername).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val passWord = snapshot.child("password").value as? String
                val role = snapshot.child("role").value as? String ?: "user" // Mặc định user

                if (edtPass == passWord) {
                    val prefs = getSharedPreferences("USER", MODE_PRIVATE)
                    prefs.edit()
                        .putString("username", edtUsername)
                        .putString("role", role) // Lưu role luôn
                        .apply()

                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Mật khẩu sai!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        fun showCustomToast(message: String, iconRes: Int, backgroundColor: String) {
            val layout = layoutInflater.inflate(R.layout.custom_toast, null)

            val imgToast = layout.findViewById<ImageView>(R.id.imgToast)
            val txtToast = layout.findViewById<TextView>(R.id.txtToast)

            imgToast.setImageResource(iconRes)
            txtToast.text = message
            layout.setBackgroundColor(android.graphics.Color.parseColor(backgroundColor))

            val toast = Toast(this)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layout
            toast.show()
        }
        binding.regAcc.setOnClickListener {
            val i = Intent(this, RegAccount::class.java)
            startActivity(i)
        }
        var i = Intent(this, MainActivity::class.java)
        binding.btnLogin.setOnClickListener {

            /*val edtUsername = binding.edtUsername.text.toString()
            val edtPass = binding.edtPass.text.toString()
            val userName = getUserNameFromDB()
            val passWord = getPassFromDB()

            if (edtUsername == userName && edtPass == passWord) {
                showCustomToast("Đăng nhập thành công", R.drawable.ic_check, "#4CAF50")
                startActivity(i)
                finish()
            } else {
                showCustomToast(
                    "Tài khoản hoặc mật khẩu không chính xác!",
                    R.drawable.ic_error,
                    "#CC3333"
                )
            }*/





            checkLoginFirebase()
        }
        binding.btnForgotPass.setOnClickListener {
            val i = Intent(this, forgotPass::class.java)
            startActivity(i)

        }


    }
}