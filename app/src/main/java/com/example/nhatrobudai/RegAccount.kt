package com.example.nhatrobudai

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhatrobudai.DataModel.User
import com.example.nhatrobudai.DataBase.MyDB
import com.example.nhatrobudai.databinding.ActivityRegAccountBinding
import com.google.firebase.database.FirebaseDatabase

class RegAccount : AppCompatActivity() {

    fun setUserToDB(username: String, password: String): Boolean {
        val mydb = MyDB(applicationContext)
        val db = mydb.writableDatabase
        return try {
            // Check xem Username đã tồn tại chưa
            val cursor = db.rawQuery(
                "SELECT * FROM Account WHERE UserName = ?",
                arrayOf(username)
            )
            if (cursor.moveToFirst()) {
                // 😅 Username đã tồn tại

                cursor.close()
                false
            } else {
                cursor.close()
                // Insert user mới
                db.execSQL(
                    "INSERT INTO Account(UserName, Pass)  VALUES (LOWER(?), LOWER(?))",
                    arrayOf(username, password)
                )

                true
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            false
        } finally {
            db.close()
        }
    }

    fun setUserToFirebase(username: String, password: String, role :String) {
        val userRef = FirebaseDatabase.getInstance().getReference("users")
        val user = User(username.lowercase(), password.lowercase(), role)

        // Check username có tồn tại chưa
        userRef.child(username).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show()
            } else {
                // Thêm user mới
                userRef.child(username).setValue(user).addOnSuccessListener {
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }



    private lateinit var binding: ActivityRegAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reg)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnDone = binding.btnDone
        btnDone.setOnClickListener {
            val regUserName = binding.regUserName.text.toString()
            val regPassW = binding.regPassW.text.toString()
            val confirmPass = binding.confirmPass.text.toString()

            if(regPassW.equals(confirmPass)){
                setUserToFirebase(regUserName,regPassW, "user")

            }else{
                Toast.makeText(this, "Mật khẩu không giống nhau !", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
}