data class ChatMessage(
    var sender: String = "",
    var text: String = "",
    var timestamp: Long = 0L, // Fix giờ VN
    var role: String = "user"
) {
    constructor() : this("", "", getVietnamTimestamp())

    companion object {
        fun getVietnamTimestamp(): Long {
            val zoneVN = java.time.ZoneId.of("Asia/Ho_Chi_Minh")
            return java.time.ZonedDateTime.now(zoneVN).toInstant().toEpochMilli()
        }
    }
}
