package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = ItemFeedDB.getDatabase(this)
        doAsync{
            val xmlLink= URL("https://www.genkidama.com.br/blog/category/anikencast/feed/podcast/").readText()
            val itemsFeed = Parser.parse(xmlLink)
            uiThread{
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = CustomAdapter(itemsFeed, this@MainActivity )
                recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL)
                )

            }
            for (item in itemsFeed) {
                database.itemFeedDAO().addItem(item)
                println(item)
            }
        }

    }
}
