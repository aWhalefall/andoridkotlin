package www.ppisland.top.myapplication.fragmentview.gif

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.lzyzsd.circleprogress.DonutProgress
import com.yhao.commen.download.ProgressDownload
import com.yhao.commen.download.ProgressListener
import com.yhao.commen.util.ScreenUtil
import com.yhao.model.bean.Gif
import org.jetbrains.anko.find
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView
import www.ppisland.top.myapplication.R

/**
 * Created by weichyang on 2017/11/1.
 */
class GifAdapter(var items: List<Gif>, var recyclerView: RecyclerView) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    var mHeights: MutableMap<Int, Int> = HashMap()
    val options: RequestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    var gifDrawable: GifDrawable? = null


    init {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                pauseGif()
            }
        })
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        Glide.with(holder.gifImageView).
                asBitmap().
                load(items?.get(position)?.img).
                transition(BitmapTransitionOptions().crossFade(800)).
                listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Bitmap>?, p3: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(bitmap: Bitmap, p1: Any?, p2: Target<Bitmap>?, p3: DataSource?, p4: Boolean): Boolean {
                        val imageViewWidth: Int = ScreenUtil.w(holder.gifImageView.context)
                        val imageViewHeight: Int = ((imageViewWidth.toDouble() / bitmap.width) * bitmap.height).toInt()
                        mHeights.put(position, imageViewHeight)
                        holder.textView.visibility = View.VISIBLE
                        holder.gifImageView.layoutParams.height = imageViewHeight
                        holder.gifImageView.layoutParams.width = imageViewWidth
                        holder.textView.text = items?.get(position)?.title
                        return false
                    }
                }).into(holder.gifImageView)
        if (mHeights.containsKey(position)) {
            holder.gifImageView.layoutParams.height = mHeights[position]!!
            holder.textView.text = items?.get(position)?.title
        }
        holder.gifImageView.setOnClickListener {
            pauseGif()
            ProgressDownload.downloadPhoto(items?.get(position)?.img!!, object : ProgressListener {
                override fun onProgress(readByte: Long, totalByte: Long, done: Boolean) {
                    holder.progressBar.post {
                        holder.progressBar.visibility = View.VISIBLE
                        holder.progressBar.progress = (((readByte.toFloat() / totalByte) * 100).toInt()) //不考虑不长是否是100，进度和下载进度同步
                    }
                }

                override fun onSave(filePath: String) {
                    gifDrawable = GifDrawable(filePath)
                    holder.gifImageView.post {
                        holder.progressBar.visibility = View.INVISIBLE
                        holder.gifImageView.setImageDrawable(gifDrawable)
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GifViewHolder {

        return GifViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_gif, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gifImageView: GifImageView = itemView.find(R.id.gifImageView)
        val progressBar: DonutProgress = itemView.find(R.id.progressBar)
        val textView: TextView = itemView.find(R.id.textView)
    }


    public fun pauseGif() {
        if (gifDrawable != null) {
            gifDrawable!!.pause()
        }
    }
}