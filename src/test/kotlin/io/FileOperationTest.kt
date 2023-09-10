package io

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream

class FileOperationTest {
    class FileOperationTest {
        @Test
        fun `readFileAsString should return file content`() {
            val fileName = "test-file.txt"
            val expectedContent = "This is a test file content.\nIt has multiple lines."
            val fileContent = FileOperation.readFileAsString(fileName)
            assertEquals(expectedContent, fileContent)
        }

        @Test
        fun `readFileAsString should throw FileNotFoundException for non-existent file`() {
            val nonExistentFileName = "non-existent-file.txt"
            assertThrows<FileNotFoundException> { FileOperation.readFileAsString(nonExistentFileName) }
        }
    }
}