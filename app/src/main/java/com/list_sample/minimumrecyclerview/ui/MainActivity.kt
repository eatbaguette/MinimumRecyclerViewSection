package com.list_sample.minimumrecyclerview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.list_sample.minimumrecyclerview.R
import com.list_sample.minimumrecyclerview.adapter.RecyclerViewAdapter
import com.list_sample.minimumrecyclerview.model.EvenNumberModel
import com.list_sample.minimumrecyclerview.model.OddNumberModel
import com.list_sample.minimumrecyclerview.model.SectionHeaderModel
import com.list_sample.minimumrecyclerview.model.section.HeaderSection
import com.list_sample.minimumrecyclerview.model.section.NoHeaderSection
import com.list_sample.minimumrecyclerview.model.section.SectionGroup
import java.util.*

class MainActivity : AppCompatActivity() {
    private var itemList = ArrayList<Any> ()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewAdapter

    val sectionGroup = SectionGroup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView

        adapter = RecyclerViewAdapter(this, sectionGroup)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        // データをリストに入れて渡す。
        prepareData()
    }

    private fun prepareData() {
        // データを作成
        for (i in 1 .. 100) {
            if (i % 2 == 0) {
                val item = EvenNumberModel(i.toString())
                itemList.add(item)
            } else {
                val item = OddNumberModel(i.toString())
                itemList.add(item)
            }
        }

        // SectionGroupにSectionを入れて渡す
        sectionGroup
                .append(HeaderSection(0, SectionHeaderModel("ヘッダータイトル1"), itemList))
                .append(HeaderSection(1, SectionHeaderModel("ヘッダータイトル2"), itemList))
                .append(NoHeaderSection(2, itemList))

        // 更新
        adapter.notifyDataSetChanged()
    }
}
