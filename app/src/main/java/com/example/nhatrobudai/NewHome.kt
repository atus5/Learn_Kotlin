package com.example.nhatrobudai

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.nhatrobudai.Fragment.AllRoomFragment
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase


class NewHome : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_home, container, false)


        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val locker: ImageView = view.findViewById(R.id.imgLocker)
        val liLocker: LinearLayout = view.findViewById(R.id.liLocker)
        val txtUserName: TextView = view.findViewById(R.id.txtUserName)



        fun showNoti() {
            val dialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog_home, null)
            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val btnOK = dialogView.findViewById<Button>(R.id.btnOk)
            alertDialog.show()
            btnOK.setOnClickListener {

                alertDialog.dismiss()
            }
        }

        progressBar.setOnClickListener {
            showNoti()
        }

        locker.setOnClickListener {
            showNoti()
        }

        liLocker.setOnClickListener {
            showNoti()
        }
        val btnBell: ImageButton = view.findViewById(R.id.btnBell)
        fun openNoti() {


            val popupView = layoutInflater.inflate(R.layout.popup_notification, null)
            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
            // Cho phép click ngoài thì đóng popup
            popupWindow.isOutsideTouchable = true
            popupWindow.elevation = 10f

            // Hiển thị ngay dưới icon
            popupWindow.showAsDropDown(btnBell, -30, 50)


            val troll1: TextView = popupView.findViewById(R.id.txtNotify1)
            val troll2: TextView = popupView.findViewById(R.id.txtNotify2)
            val troll3: TextView = popupView.findViewById(R.id.txtNotify3)
            val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ&start_radio=1"


            troll1.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            troll2.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            troll3.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }


        }

        btnBell.setOnClickListener {

            openNoti()
        }


        val openAllroom: TextView = view.findViewById(R.id.txtOpenAllRoom)
        openAllroom.setOnClickListener {
            // Mở Fragment mới nè ku
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AllRoomFragment()) // container của mày
                .addToBackStack(null) // Để bấm back quay lại được
                .commit()

        }

        val suCo: ImageView = view.findViewById(R.id.txtSuCo)
        val sos: ImageView = view.findViewById(R.id.txtSos)
        val traPhong: ImageView = view.findViewById(R.id.txtTraPhong)
        val capNhat: ImageView = view.findViewById(R.id.txtCapNhat)

        fun showBankAnhDiKu() {

            val dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.qr_bank_dialog, null)

            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()



            alertDialog.show()

        }

        suCo.setOnClickListener {
            val url = "https://zalo.me/g/hrnhuw201"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)

        }

        sos.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:113")
            startActivity(intent)
        }

        traPhong.setOnClickListener {
            showBankAnhDiKu()

        }

        capNhat.setOnClickListener {
            showNoti()

        }


        fun showUsername() {
            val prefs = requireContext().getSharedPreferences("USER", MODE_PRIVATE)
            val userName = prefs.getString("username", "") ?: ""
            val dbRef = FirebaseDatabase.getInstance().getReference("users")
            dbRef.child(userName).get().addOnSuccessListener { snap ->
                if (snap.exists()) {
                    val usernameValue =
                        snap.child("username").value as? String
                    txtUserName.text = usernameValue
                } else {
                    txtUserName.text = "???"
                }

            }.addOnFailureListener { e ->
                txtUserName.text = e.message

            }
        }
        showUsername()


        return view
    }


}