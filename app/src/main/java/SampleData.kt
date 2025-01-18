data class Message(val author: String, val body: String)

object SampleData {
    val conversationSample = listOf(
        Message("Alice", "Hey, how are you?"),
        Message("Bob", "I'm good! How about you?"),
        Message("Alice", "I'm doing great, thanks!"),
        Message("Bob", "Are you coming to the party?"),
        Message("Alice", "Yes, I'll be there at 7 PM."),
        Message("Bob", "Awesome, see you there!")
    )
}