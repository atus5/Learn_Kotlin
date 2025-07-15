package com.example.nhatrobudai.Fragment

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nhatrobudai.ChangePassFragment
import com.example.nhatrobudai.LoginActivity
import com.example.nhatrobudai.R
import com.example.nhatrobudai.SecureFragment
import com.example.nhatrobudai.SubFragment.GioiThieuFragment
import com.google.firebase.database.FirebaseDatabase


class SettingFragment : Fragment() {
    fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun showLanguageDialog(context: Context) {
        val languages = arrayOf("Tiếng Việt")

        AlertDialog.Builder(context)
            .setTitle("Chọn ngôn ngữ")
            .setItems(languages) { i, i1 ->
                val selectedLanguage = languages[i1]

            }
            .show()
    }

    fun deleleUserFirebase() {
        val prefs = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "") ?: ""

        val dbRef = FirebaseDatabase.getInstance().getReference("users")
        if (username.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "❌ Không tìm thấy tên người dùng để xóa",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        dbRef.child(username).get().addOnSuccessListener { snap ->
            if (snap.exists()) {
                dbRef.child(username).removeValue().addOnSuccessListener {
                    Toast.makeText(
                        requireContext(),
                        "✅ Xóa tài khoản thành công",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Xóa username trong SharedPreferences
                    prefs.edit().remove("username").apply()

                    val i = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(i)
                    requireActivity().finish()
                }.addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "💥 Lỗi xóa: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "❌ Không thể xóa tài khoản!", Toast.LENGTH_SHORT)
                    .show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(requireContext(), "💥 Lỗi truy vấn: ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val btnGt: LinearLayout = view.findViewById(R.id.layoutShow)
        val btnlanguage: LinearLayout = view.findViewById(R.id.layoutLanguage)
        val btnSecure: LinearLayout = view.findViewById(R.id.layoutSecure)
        val btnDieuKhoan: LinearLayout = view.findViewById(R.id.layoutDieuKhoan)
        val btnShare: LinearLayout = view.findViewById(R.id.layoutShare)
        val btnSupport: LinearLayout = view.findViewById(R.id.layoutSupport)
        val btnPassChange: LinearLayout = view.findViewById(R.id.layoutPassChange)
        val btnRemove: LinearLayout = view.findViewById(R.id.layoutRemove)
        val layoutProfile: LinearLayout = view.findViewById(R.id.layoutProfile)


        fun showNoti() {

            val dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.custom_dialog_profile, null)
            val layoutUandP = dialogView.findViewById<ConstraintLayout>(R.id.layoutUandP)
            val layoutLoading = dialogView.findViewById<LinearLayout>(R.id.layoutLoading)
            val userName = dialogView.findViewById<TextView>(R.id.txtUsernamev)
            val passWord = dialogView.findViewById<TextView>(R.id.txtPasswordv)
            layoutUandP.visibility = View.GONE
            layoutLoading.visibility = View.VISIBLE
            // Lấy username từ SharedPreferences
            val prefs = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE)
            val username = prefs.getString("username", "") ?: "???"

            // Lấy dữ liệu từ Firebase
            val dbRef = FirebaseDatabase.getInstance().getReference("users")
            dbRef.child(username).get().addOnSuccessListener { snap ->
                layoutUandP.visibility = View.VISIBLE
                layoutLoading.visibility = View.GONE
                if (snap.exists()) {
                    val fetchedUsername = snap.child("username").value as? String
                    val fetchedPassword = snap.child("password").value as? String

                    userName.text = fetchedUsername
                    passWord.text = fetchedPassword
                } else {
                    userName.text = "???"
                    passWord.text = "???"
                }

            }

            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val btnOK = dialogView.findViewById<Button>(R.id.btnOKK)
            btnOK.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        layoutProfile.setOnClickListener {

            showNoti()

        }

        btnRemove.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Xác nhận")
                .setMessage("Bạn chắc chắn muốn xóa tài khoản chứ ?")
                .setPositiveButton("OK") { _, _ ->
                    deleleUserFirebase()
                }
                .setNegativeButton("Không") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        btnPassChange.setOnClickListener {
            openFragment(ChangePassFragment())
        }
        val btnLogout: LinearLayout = view.findViewById(R.id.layoutLogout)
        btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn đăng xuất không ?")
                .setPositiveButton("Có") { dialog, _ ->
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                    Toast.makeText(
                        requireContext(),
                        "Đăng xuất thành công",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }
                .setNeutralButton("Không") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        btnGt.setOnClickListener {
            openFragment(GioiThieuFragment())
        }
        btnlanguage.setOnClickListener {
            showLanguageDialog(requireContext())
        }

        btnSecure.setOnClickListener {
            openFragment(SecureFragment())
        }
        btnDieuKhoan.setOnClickListener {
            openFragment(SecureFragment())
        }
        btnShare.setOnClickListener {

            val linkToCopy = "https://pornhub.com" // Link mày cấp nè ku

            // Lấy ClipboardManager
            val clipboard =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            // Tạo ClipData chứa link
            val clip = ClipData.newPlainText("Link chia sẻ", linkToCopy)

            // Copy vào clipboard
            clipboard.setPrimaryClip(clip)

            // Thông báo
            AlertDialog.Builder(requireContext())
                .setTitle("Xác nhận")
                .setMessage("Cảm ơn bạn đã chia sẻ ~~")
                .setPositiveButton("OK") { dialog, _ ->
                    Toast.makeText(
                        requireContext(),
                        "Đã sao chép vào clipboard",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }.show()
        }
        btnSupport.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Xác nhận")
                .setMessage("Bạn có muốn liên hệ hỗ trợ với Admin không ?")
                .setPositiveButton("Có") { dialog, _ ->
                    val url = "https://www.facebook.com/atus.005"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                .setNeutralButton("Không") { dialog, _ ->
                    dialog.dismiss()

                }
                .show()


        }


        return view
    }


}