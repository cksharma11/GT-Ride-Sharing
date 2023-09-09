package io

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

object FileOperation {
    fun readFileAsString(fileName: String): String {
        val inputStream = object {}.javaClass.classLoader.getResourceAsStream(fileName)
        if (inputStream != null) {
            val reader = BufferedReader(InputStreamReader(inputStream))
            return reader.use { it.readText() }
        } else {
            throw FileNotFoundException()
        }
    }
}