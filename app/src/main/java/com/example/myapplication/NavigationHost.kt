import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MainViewModel

@Composable
fun NavigationHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                repositoryList = viewModel.repositoryList.value,
                onFetchClick = { viewModel.fetchRepositories(it) },
                onRepoClick = { repo -> navController.navigate("detail/${repo.name}") },
                errorMessage = viewModel.errorMessage.value,
                isLoading = viewModel.isLoading.value
            )
        }
        composable("detail/{repoName}") { backStackEntry ->
            val repoName = backStackEntry.arguments?.getString("repoName") ?: ""
            val repo = viewModel.repositoryList.value.find { it.name == repoName }
            if (repo != null) {
                RepoDetailScreen(repo = repo)
            }
        }
    }
}
