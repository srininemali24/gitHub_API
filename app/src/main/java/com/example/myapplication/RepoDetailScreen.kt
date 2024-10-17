import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.Repository

@Composable
fun RepoDetailScreen(repo: Repository) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Repository: ${repo.name}", style = MaterialTheme.typography.h4)
        Text(text = "Description: ${repo.description ?: "No description"}")
        Text(text = "Language: ${repo.language ?: "Unknown"}")
        Text(text = "Stars: ${repo.stars}")
    }
}
