package br.ufpe.cin.android.podcast

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemFeed(@PrimaryKey @NonNull val title: String, val link: String, val pubDate: String, val description: String, val downloadLink: String) {

    override fun toString(): String {
        return title
    }
}
