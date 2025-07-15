import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nhatrobudai.Adapter.ChatAdapter

import com.example.nhatrobudai.databinding.FragmentMailBinding
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MailFragment : Fragment() {

    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ChatAdapter
    private val messages = ArrayList<ChatMessage>()

    private lateinit var dbRef: DatabaseReference
    private lateinit var userId: String
    private lateinit var userRole: String
    private var messageListener: ValueEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailBinding.inflate(inflater, container, false)

        val prefs = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE)
        userId = prefs.getString("username", "Anonymous") ?: "Anonymous"
        userRole = prefs.getString("role", "user") ?: "user" // default là user

        initView()
        return binding.root
    }

    private fun initView() {
        // Firebase path
        dbRef = if (userRole == "admin") {
            FirebaseDatabase.getInstance().getReference("chats")
        } else {
            FirebaseDatabase.getInstance().getReference("chats/${userId}")
        }

        // Setup RecyclerView
        adapter = ChatAdapter(messages, userId)
        binding.recyclerViewChat.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
            adapter = this@MailFragment.adapter
        }

        // Load chat messages
        loadMessages()

        // Gửi tin nhắn
        binding.btnSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
                binding.editTextMessage.text.clear()
            }
        }
    }

    private fun loadMessages() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()

                if (userRole == "admin") {
                    for (userSnapshot in snapshot.children) {
                        for (msgSnapshot in userSnapshot.children) {
                            if (msgSnapshot.hasChild("sender") && msgSnapshot.hasChild("text") && msgSnapshot.hasChild("timestamp")) {
                                val message = msgSnapshot.getValue(ChatMessage::class.java)
                                message?.let { messages.add(it) }
                            }
                        }
                    }
                } else {
                    for (msgSnapshot in snapshot.children) {
                        if (msgSnapshot.hasChild("sender") && msgSnapshot.hasChild("text") && msgSnapshot.hasChild("timestamp")) {
                            val message = msgSnapshot.getValue(ChatMessage::class.java)
                            message?.let { messages.add(it) }
                        }
                    }
                }

                // 👉 Sort theo timestamp để đúng thứ tự
                messages.sortBy { it.timestamp }

                adapter.notifyDataSetChanged()
                if (messages.isNotEmpty()) {
                    binding.recyclerViewChat.scrollToPosition(messages.size - 1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Lỗi: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun sendMessage(text: String) {
        val sender = if (userRole == "admin") "admin" else userId

        // Tìm user đang nhắn gần nhất (lấy sender của tin nhắn cuối cùng)
        val replyUserId = if (userRole == "admin" && messages.isNotEmpty()) {
            messages.last().sender
        } else {
            userId
        }

        val targetRef = FirebaseDatabase.getInstance()
            .getReference("chats/$replyUserId")

        val message = ChatMessage(
            sender = sender,
            text = text,
            timestamp = ChatMessage.getVietnamTimestamp()
        )

        targetRef.push().setValue(message)
            .addOnSuccessListener {
                Log.d("FirebasePath", "Admin gửi vào: chats/$replyUserId")
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Lỗi gửi: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        messageListener?.let { dbRef.removeEventListener(it) }
        _binding = null
    }
}
