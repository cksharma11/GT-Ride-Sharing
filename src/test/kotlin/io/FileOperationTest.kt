package io

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

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