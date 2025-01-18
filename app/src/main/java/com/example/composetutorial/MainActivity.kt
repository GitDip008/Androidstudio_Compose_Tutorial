package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

// Message Data Class
data class Message(val author: String, val body: String)

// SampleData Object
object SampleData {
    val conversationSample = listOf(
        Message("Dip", "Have you checked out the latest Android updates?"),
        Message(
            "Android 16",
            "Yes! Android 14 is amazing. The predictive back gesture is now smoother, which makes navigation more intuitive. "
                    + "They’ve also improved per-app language settings, which is super helpful for multilingual users. "
                    + "It feels like they're paying a lot of attention to accessibility."
        ),
        Message("Dip", "That’s great. Any updates for developers?"),
        Message("Android 16", "Jetpack Compose has some cool new features."),
        Message(
            "Dip",
            "I’ve heard about Compose! It seems revolutionary for UI building. "
                    + "Everyone says it simplifies layout design significantly. "
                    + "I need to try the tutorial soon."
        ),
        Message("Android 16", "You should! Previews save a lot of time."),
        Message("Dip", "What about Material Design 3?"),
        Message(
            "Android 16",
            "Material Design 3 is a game-changer. Dynamic color theming adapts beautifully to user preferences, making apps more personal. "
                    + "It also aligns perfectly with modern design principles. "
                    + "Implementing it is straightforward, especially with Jetpack Compose."
        ),
        Message("Dip", "Does it support older Android versions?"),
        Message("Android 16", "Mostly Android 12 and newer."),
        Message("Dip", "Have emulator tools improved in Android Studio?"),
        Message("Android 16", "Yes! They’re faster and handle foldables better."),
        Message(
            "Dip",
            "That’s awesome. Testing apps on emulators used to be slow. "
                    + "Optimizing performance and adding foldable support is a big win. "
                    + "It should make developing for larger screens much easier."
        ),
        Message("Android 16", "Agreed! Try the new Layout Validation tools."),
        Message("Dip", "I’ll do that. What about Firebase testing?"),
        Message("Android 16", "Crashlytics is great for real-time issue monitoring."),
        Message("Dip", "Any tips for getting started with Firebase?"),
        Message("Android 16", "Start with Firebase Auth and Realtime Database."),
        Message("Dip", "What’s your experience with Cloud Functions?"),
        Message("Android 16", "They’re perfect for handling backend logic."),
        Message("Dip", "Thanks for the info. I’ve learned so much!")
    )
}


// MessageCard Composable
@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 14.dp)) {
        Image(
            painter = painterResource(R.drawable.dip1),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// Conversation Composable
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

// Previews
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}
