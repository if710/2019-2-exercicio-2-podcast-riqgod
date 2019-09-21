package br.ufpe.cin.android.podcast

import android.content.ClipData
import androidx.room.*

@Dao
interface ItemFeedDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(vararg items:ItemFeed)
    @Update
    fun updateItem(vararg items:ItemFeed)
    @Delete
    fun removeItem(vararg items:ItemFeed)
    @Query("SELECT * FROM items")
    fun allItems(): List<ItemFeed>
    @Query ("SELECT * FROM items WHERE title LIKE :name")
    fun searchItemForTitle(name:String):List<ItemFeed>
    @Query ("SELECT * FROM items WHERE pubDate LIKE :date")
    fun searchItemForDate(date:String) : List<ItemFeed>
}