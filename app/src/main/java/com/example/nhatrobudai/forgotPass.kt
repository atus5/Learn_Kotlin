package com.example.nhatrobudai

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nhatrobudai.DataBase.MyDB
import com.example.nhatrobudai.databinding.ActivityForgotPassBinding
import com.example.nhatrobudai.databinding.ActivityLoginBinding
import com.google.firebase.database.FirebaseDatabase

class forgotPass : AppCompatActivity() {

    fun updatePass() {
        val fUserName = binding.fUserName.text.toString()
        val fPass = binding.fPass.text.toString()
        val secureCode = binding.secureCode.text.toString()

        val myDB = MyDB(applicationContext)
        val db = myDB.writableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM Account WHERE UserName = ?",
            arrayOf(fUserName)
        )

        if (cursor.moveToFirst()) {
            if (secureCode == "123") {
                db.execSQL(
                    "UPDATE Account SET Pass = ? WHERE UserName = ?",
                    arrayOf(fPass, fUserName)
                )
                Toast.makeText(this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Mã bảo mật không đúng", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
        }

        cursor.close()
        db.close()
    }

    fun updatePassToFirebase() {
        val fUserName = binding.fUserName.text.toString().trim()
        val fPass = binding.fPass.text.toString().trim()
        val secureCode = binding.secureCode.text.toString().trim()

        val myDb = FirebaseDatabase.getInstance().getReference("users")
        if (secureCode == "123") {
            myDb.child(fUserName).get().addOnSuccessListener { it ->
                if (it.exists()) {
                    myDb.child(fUserName).child("password").setValue(fPass).addOnSuccessListener {
                        Toast.makeText(this, " Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT)
                            .show()
                    }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, " Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()

                        }
                }else{
                    Toast.makeText(this, " Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener{ e->
                Toast.makeText(this, " Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Mã bảo mật không đúng", Toast.LENGTH_SHORT).show()
        }
    }


    private lateinit var binding: ActivityForgotPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.forgot)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnfDone.setOnClickListener {
            updatePassToFirebase()
        }
    }
}