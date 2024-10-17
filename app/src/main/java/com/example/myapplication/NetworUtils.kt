package com.example.myapplication

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {

    fun fetchRepositories(username: String): String {
        val url = URL("https://api.github.com/users/$username/repos")
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.setRequestProperty("Accept", "application/vnd.github.v3+json")

        return try {
            val inputStream = BufferedInputStream(urlConnection.inputStream)
            inputStream.bufferedReader().use { it.readText() }
        } finally {
            urlConnection.disconnect()
        }
    }
}
