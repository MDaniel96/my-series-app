package hu.dmorvai.myseries.ui.series

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.dmorvai.myseries.R
import hu.dmorvai.myseries.model.Serie
import kotlinx.android.synthetic.main.list_item_serie.view.*
import java.time.LocalDate

class SeriesAdapter(
    private val context: Context,
    private var series: List<Serie>
) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_serie, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        series[position].let {
            holder.tvTitle.text = it.name
            holder.tvYear.text = if (it.premiered != null) LocalDate.parse(it.premiered).year.toString() else "TBD"
            holder.tvRating.text = String.format(context.getString(R.string.rating_out_of_10), it.rating?.average)
            Glide.with(context).load(it.image?.medium).into(holder.ivImage)
        }
    }

    override fun getItemCount() = series.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivImage: ImageView = view.ivImage
        var tvTitle: TextView = view.tvTitle
        var tvYear: TextView = view.tvYear
        var tvRating: TextView = view.tvRating
    }
}
