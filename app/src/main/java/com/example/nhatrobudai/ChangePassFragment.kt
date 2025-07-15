package com.example.nhatrobudai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class ChangePassFragment : Fragment() {
    fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()

    }

    fun changePass(userName: String, password: String, oldPassword: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("users")

        // Truy vấn user
        dbRef.child(userName).get()
            .addOnSuccessListener { snap ->
                if (snap.exists()) {
                    // Lấy mật khẩu từ Firebase
                    val passW = snap.child("password").value as? String

                    if (passW == oldPassword) {
                        // Update mật khẩu mới
                        dbRef.child(userName).child("password").setValue(password)
                        Toast.makeText(
                            requireContext(),
                            "✅ Đổi mật khẩu thành công",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(requireContext(), "❌ Sai mật khẩu cũ", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "❌ Tài khoản không tồn tại",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "💥 Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_pass, container, false)
        val fUserName: EditText = view.findViewById(R.id.fUserName)


        val oldPass: EditText = view.findViewById(R.id.oldPass)

        val newPass: EditText = view.findViewById(R.id.newPass)

        val btnDone: Button = view.findViewById(R.id.btnffDone)
        btnDone.setOnClickListener {
            val username = fUserName.text.toString().trim()
            val oldPassword = oldPass.text.toString().trim()
            val newPassword = newPass.text.toString().trim()
            changePass(username, newPassword, oldPassword)
        }
        return view
    }

}