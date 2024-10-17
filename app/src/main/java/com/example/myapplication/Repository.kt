package com.example.myapplication

import org.json.JSONArray

data class Repository(
    val name: String,
    val description: String?,
    val language: String?,
    val stars: Int
)

fun parseRepositories(jsonResponse: String): List<Repository> {
    val repositories = mutableListOf<Repository>()
    val jsonArray = JSONArray(jsonResponse)

    for (i in 0 until jsonArray.length()) {
        val repoObject = jsonArray.getJSONObject(i)
        val name = repoObject.getString("name")
        val description = repoObject.optString("description", "No description")
        val language = repoObject.optString("language", "Unknown")
        val stars = repoObject.getInt("stargazers_count")

        repositories.add(Repository(name, description, language, stars))
    }
    return repositories
}
