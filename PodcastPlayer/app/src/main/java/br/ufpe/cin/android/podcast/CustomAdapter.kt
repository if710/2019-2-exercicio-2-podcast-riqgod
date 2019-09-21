package br.ufpe.cin.android.podcast

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_episode_detail.view.*
import kotlinx.android.synthetic.main.itemlista.view.*

public class CustomAdapter( val listItemFeed:List<ItemFeed>, val context: Context) : RecyclerView.Adapter<ItemFeedHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFeedHolder{
        var inflater = LayoutInflater.from(context)
        var v = inflater.inflate(R.layout.itemlista,parent,false)
        return ItemFeedHolder(v)
    }

    override fun getItemCount(): Int {
        return listItemFeed.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ItemFeedHolder, position: Int) {
        val item = listItemFeed[position]
        holder.Bind(item,context)
         //To change body of created functions use File | Settings | File Templates.
    }

}

class ItemFeedHolder(private val feed: View) : RecyclerView.ViewHolder(feed), View.OnClickListener{

    var title = feed.item_title
    var button = feed.item_action
    var date = feed.item_date
    var downloadLink:String = ""
    var link:String = ""
    var description:String = ""

    fun Bind(item:ItemFeed, context:Context){
        title.text = item.title
        date.text = item.pubDate
        downloadLink = item.downloadLink
        link = item.link
        description = item.description

        button.setOnClickListener {
            val intent = Intent()
            intent.action = ACTION_VIEW
            intent.data = Uri.parse(downloadLink)
            it.context.startActivity(intent)
            Toast.makeText(context,"Downloading... ${item.downloadLink} <- url", Toast.LENGTH_SHORT).show()
        }
        feed.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent(v.context, EpisodeDetailActivity::class.java)
        intent.putExtra("title",title.text)
        intent.putExtra("date",date.text)
        intent.putExtra("downloadLink",downloadLink)
        intent.putExtra("link",link)
        intent.putExtra("description",description)
        startActivity(v.context, intent, null)
    }
}