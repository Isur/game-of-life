package com.adt.game_of_life.model.file

import android.app.Application
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class FileManager(context: Application) : IFileManager {

    private val root = context.getExternalFilesDir(null)!!

    override fun getFilenames(): List<String> {
        return getFiles().map { it.nameWithoutExtension }
    }

    override fun getFiles(): List<File> {
        return root
            .listFiles()!!
            .filter { it.extension == GAME_OF_LIFE_EXTENSION }
            .sortedBy { it.name }
    }

    override fun addFile(filename: String, content: Array<Array<Int?>>) {
        val isUnique = !getFiles().any { it.name == filename }
        if (isUnique) {
            val filenameWithExtension = "$filename.$GAME_OF_LIFE_EXTENSION"
            val fos = FileOutputStream(File(root.path, filenameWithExtension))
            val oos = ObjectOutputStream(fos)
            oos.writeObject(content)
        }
    }

    override fun getContent(filename: String): Array<Array<Int?>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private companion object {
        const val GAME_OF_LIFE_EXTENSION = "gole"
    }
}