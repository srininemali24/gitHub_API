import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.Repository

@Composable
fun MainScreen(
    repositoryList: List<Repository>,
    onFetchClick: (String) -> Unit,
    onRepoClick: (Repository) -> Unit,
    errorMessage: String?,
    isLoading: Boolean
) {
    var username by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("GitHub Username or Organization") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onFetchClick(username) }) {
            Text("Fetch Repositories")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        } else {
            LazyColumn {
                items(repositoryList) { repo ->
                    RepositoryItem(repo = repo, onClick = { onRepoClick(repo) })
                }
            }
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = repo.name, fontWeight = FontWeight.Bold)
        Text(text = repo.language ?: "Unknown Language")
        Text(text = "⭐ ${repo.stars}")
    }
}
