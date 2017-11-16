package www.ppisland.top.myapplication.fragmentview.text.joke

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yhao.model.bean.Joke
import org.jetbrains.anko.find
import www.ppisland.top.myapplication.R
import java.util.regex.Pattern
import java.util.regex.Pattern.compile

/**
 * Created by weichyang on 2017/11/1.
 */
class JokeAdapter(var items: List<Joke>) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {


    override fun getItemCount(): Int {
 return items.size;
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.txtTile.text = items?.get(position)?.title
        //原数据有html标签，过滤掉
        val p_html = compile("<[^>]+>", Pattern.CASE_INSENSITIVE)
        val m_html = p_html.matcher(items?.get(position)?.text)
        holder.txtContent.text = m_html.replaceAll("")


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JokeViewHolder {
        return JokeViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_joke, parent, false))
    }


    class JokeViewHolder(jokeItem: View) : RecyclerView.ViewHolder(jokeItem) {

        val txtTile: TextView = itemView.find(R.id.title)
        val txtContent: TextView = itemView.find(R.id.text)

    }
}