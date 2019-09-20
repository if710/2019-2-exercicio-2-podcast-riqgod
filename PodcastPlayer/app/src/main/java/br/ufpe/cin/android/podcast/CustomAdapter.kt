package br.ufpe.cin.android.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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

class ItemFeedHolder(feed: View) : RecyclerView.ViewHolder(feed), View.OnClickListener{

    var title = feed.item_title
    var button = feed.item_action
    var date = feed.item_date

    fun Bind(item:ItemFeed, context:Context){
        title.text = item.title
        date.text = item.pubDate
        button.setOnClickListener {
            Toast.makeText(context,"Downloading... ${item.downloadLink} <- url", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {
        val position = adapterPosition
        Toast.makeText(v.context, "Clicou no item da posição: $position", Toast.LENGTH_SHORT).show()
        //not working this toast... why??
    }
}