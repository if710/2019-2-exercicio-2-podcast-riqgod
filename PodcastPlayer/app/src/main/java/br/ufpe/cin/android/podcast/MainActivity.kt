package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = ItemFeedDB.getDatabase(this@MainActivity)
        var itemsFeed = listOf<ItemFeed>()
        doAsync {
            try{
                val xmlLink = URL("https://www.genkidama.com.br/blog/category/anikencast/feed/podcast/").readText()
                itemsFeed = Parser.parse(xmlLink)

                for (item in itemsFeed) {
                    database.itemFeedDAO().addItem(item)
                    println(item)
                }

            }catch (e: Exception){
                Toast.makeText(ctx,"Internet error! loading stored data...",Toast.LENGTH_SHORT).show()
                println("Internet error! loading stored data..."+e.toString())
                itemsFeed = database.itemFeedDAO().allItems()
            } finally{
                uiThread{
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = CustomAdapter(itemsFeed, this@MainActivity )
                    recyclerView.addItemDecoration(
                        DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL)
                    )
                }
            }

        }
    }
}
