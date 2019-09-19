package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.UiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync{
            val xmlLink= URL("https://www.genkidama.com.br/blog/category/anikencast/feed/podcast/").readText()
            val itensFeed = Parser.parse(xmlLink)
            uiThread{
                toast(itensFeed.toString())
            }
        }


    }
}
