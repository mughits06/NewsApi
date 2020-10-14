package com.mughitszufar.newsapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.mughitszufar.newsapi.databinding.CdvNewsHeadlineBinding
import com.mughitszufar.newsapi.model.ArticlesItem

class CdvNewsHeadlinesAdapter : RecyclerView.Adapter<CdvNewsHeadlinesVH>(){

    //  untuk mengambil data di dalam model article item
    private val listData = ArrayList<ArticlesItem>()

    //  berfungsi untuk Add data ke dalam RecyclerView
    fun addData(items:List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    //    berfungsi untuk menginflate atau menerapkan operasi yang dibuat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlinesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater,parent,false)
        return CdvNewsHeadlinesVH(binding)
    }

    //    digunakan untuk mengetahui panjang/banyak data(size) guna kebutuhan looping
    override fun getItemCount(): Int {
        return listData.size
    }

    //    digunakan untuk memposisikan widget pada layout model
    override fun onBindViewHolder(holder: CdvNewsHeadlinesVH, position: Int) {
        holder.bind(listData[position])
    }
}

//    sebagai Adapter Recyclerview
class CdvNewsHeadlinesVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article : ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "Tidak ada Judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }
//            untuk melakukan aksi klik pada gambar
            root.setOnClickListener{
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS,article) // DETAIL_NEWS BERFUNGSI SEBAGAI VARIABLE YANG BERISI DATA YANG AKAN DIKIRIMKAN KE DETAIL ACTIVITY
                }
                it.context.startActivity(intent)
            }
        }
    }
    private fun cropText(text: String) : String {
        return text.take(50) + if (text.length > 50)"..." else ""
    }
}

