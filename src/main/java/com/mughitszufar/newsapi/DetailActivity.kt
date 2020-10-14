package com.mughitszufar.newsapi

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import coil.api.load
import coil.size.Scale
import com.mughitszufar.newsapi.databinding.ActivityDetailBinding
import com.mughitszufar.newsapi.model.ArticlesItem

class DetailActivity : AppCompatActivity() {

    //variable untuk menangkap datayang dikirimkna oleh mainactivity melalui cdv_newsheadlineadapter
    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    //untuk menampilkan view, karena kita akan menampilkan  detail activity maka yang akan ada di extend action
    //intinya tinggal tambahin tulisan binding di akhir
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        //untuk menampilkan data yang dikirimkan oleh mainactivity melalui cdvnewsheadline adapter
        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)

                //untuk membuild anctionbar
            setSupportActionBar(toolBar)

            //untuk menampilkan tombol bind
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title
            imgToolbar.apply {
                load(data.urlToImage) {
                    scale(Scale.FILL)
                }

                contentDescription = data.description

            }

            //untuk get context
            txtContent.text = data.content

            //untuk get publishAt
            txtDate.text = data.publishedAt

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}