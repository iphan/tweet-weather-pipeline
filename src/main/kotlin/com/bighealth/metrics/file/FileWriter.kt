package com.bighealth.metrics.file

import java.io.Closeable
import java.io.File
import java.io.FileOutputStream

class FileWriter(filepath: String) : Closeable {
    private val fileOutputStream = FileOutputStream(File(filepath), true).bufferedWriter()

    fun writeLine(line: String) {
        fileOutputStream.append(line)
        fileOutputStream.newLine()
    }

    override fun close() {
        fileOutputStream.close()
    }

}