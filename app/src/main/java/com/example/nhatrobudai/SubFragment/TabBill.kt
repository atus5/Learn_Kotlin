package com.example.nhatrobudai.SubFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.Adapter.Bill2Adapter
import com.example.nhatrobudai.DataModel.DataBill2
import com.example.nhatrobudai.R
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID


class TabBill : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tabbill, container, false)
        fun getId(): String {
            val randomId = UUID.randomUUID().toString().replace("-", "", true)
            val roomId = randomId.substring(0, 6).uppercase()
            return "#${roomId}"

        }
        fun setBill() {
            val dbRef = FirebaseDatabase.getInstance().getReference("users/room")
            val billReg = dbRef.child("bills")
            billReg.get().addOnSuccessListener { snap ->
                if (!snap.exists()) {
                    val sampleBills = listOf(
                        DataBill2(
                            "P101",
                            "15/07/2025",
                            "Hoàn thành",
                            "100M VND",
                            R.drawable.gau_hai,
                            getId()
                        ),
                        DataBill2(
                            "P102",
                            "15/08/2025",
                            "Hoàn thành",
                            "200M VND",
                            R.drawable.gau_hai,
                            getId()
                        ),
                        DataBill2(
                            "P103",
                            "15/09/2025",
                            "Hoàn thành",
                            "300M VND",
                            R.drawable.gau_hai,
                            getId()
                        ),
                        DataBill2(
                            "P104",
                            "15/10/2025",
                            "Hoàn thành",
                            "400M VND",
                            R.drawable.gau_hai,
                            getId()
                        ),
                        DataBill2(
                            "P105",
                            "15/11/2025",
                            "Hoàn thành",
                            "500M VND",
                            R.drawable.gau_hai,
                            getId()
                        ),
                        DataBill2(
                            "P106",
                            "15/12/2025",
                            "Hoàn thành",
                            "600M VND",
                            R.drawable.gau_hai,
                            getId()
                        )
                    )

                    for (bill in sampleBills) {
                        billReg.push().setValue(bill)
                    }
                    Toast.makeText(requireContext(), "Thêm thành công 6 bills", Toast.LENGTH_SHORT)
                        .show()
                }
            }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Lỗi: ${e.message}", Toast.LENGTH_SHORT)

            }

        }

        fun getBill() {
            val dbRef = FirebaseDatabase.getInstance().getReference("users/room/bills")

            dbRef.get().addOnSuccessListener { snap ->
                if (snap.exists()) {
                    val listBill = ArrayList<DataBill2>()
                    for (billSnap in snap.children) {
                        val bill = billSnap.getValue(DataBill2::class.java)
                        if (bill != null) {
                            listBill.add(bill)
                        }
                    }
                    val adapter = Bill2Adapter(listBill)
                    val recylerView = view.findViewById<RecyclerView>(R.id.recylerView2)
                    recylerView.adapter = adapter
                    recylerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                    val search = view.findViewById<SearchView>(R.id.searchView)

                    search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            adapter.filter(newText ?: "")
                            return true
                        }


                    })
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Không tìm thấy bills trong Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }.addOnFailureListener { e ->
                Log.e("TabBill", "Lỗi: ${e.message}")
            }
        }
        setBill()
        getBill()


        val troll = view.findViewById<TextView>(R.id.txtTroll)
        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ&start_radio=1"
        troll.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)

        }




        return view
    }


}