package com.list_sample.minimumrecyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.list_sample.minimumrecyclerview.R
import com.list_sample.minimumrecyclerview.model.EvenNumberModel
import com.list_sample.minimumrecyclerview.model.OddNumberModel
import com.list_sample.minimumrecyclerview.model.section.HeaderSection
import com.list_sample.minimumrecyclerview.model.section.SectionGroup
import com.list_sample.minimumrecyclerview.model.SectionHeaderModel

/**
 * Created by monkey on 2017/09/26.
 */
class RecyclerViewAdapter(context: Context, private val sectionGroup:SectionGroup): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)


    enum class ViewType(val id: Int) {

        EvenNumber(0) {
            override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder {
                return EvenNumberHolder(inflater.inflate(R.layout.even_number_cell, viewGroup, false))
            }

            override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Any) {
                holder as EvenNumberHolder
                item as EvenNumberModel

                holder.evenNumberCellText.text = item.cellText
            }

        },
        OddNumber(1) {
            override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder {
                return OddNumberHolder(inflater.inflate(R.layout.odd_number_cell, viewGroup, false))
            }

            override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Any) {
                holder as OddNumberHolder
                item as OddNumberModel

                holder.oddNumberCellText.text = item.cellText
            }
        },
        SectionHeader(2) {
            override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup): RecyclerView.ViewHolder {
                return SectionHeaderHolder(inflater.inflate(R.layout.section_header_cell, viewGroup, false))
            }

            override fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Any) {
                holder as SectionHeaderHolder
                item as SectionHeaderModel

                holder.sectionHeaderText.text = item.titleText
            }
        }
        ;

        // 抽象クラス
        abstract fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) : RecyclerView.ViewHolder
        abstract fun bindViewHolder(holder: RecyclerView.ViewHolder, item: Any)

        // ViewTypeを探す
        companion object {
            fun forId(id: Int): ViewType {
                for(viewType: ViewType in values()) {
                    if (viewType.id == id) {
                        return viewType
                    }
                }
                throw AssertionError("no enum")
            }
        }
    }

    // ViewHolder
    companion object {
        private class EvenNumberHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val evenNumberCellText = itemView.findViewById<TextView>(R.id.even_number_cell_text)
        }

        private class OddNumberHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val oddNumberCellText = itemView.findViewById<TextView>(R.id.odd_number_cell_text)
        }

        private class SectionHeaderHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val sectionHeaderText = itemView.findViewById<TextView>(R.id.section_header_cell_header_text)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.even_number_cell, parent, false)

        return ViewType.forId(viewType).createViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = sectionGroup.itemAt(position)
        ViewType.forId(holder.itemViewType).bindViewHolder(holder, item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = sectionGroup.itemAt(position)

        return when(item) {
            is EvenNumberModel -> {
                ViewType.EvenNumber.id
            }
            is OddNumberModel -> {
                ViewType.OddNumber.id
            }
            is SectionHeaderModel -> {
                ViewType.SectionHeader.id
            }
            else -> {
                throw AssertionError("no enum")
            }
        }
    }

    override fun getItemCount(): Int {
        return sectionGroup.totalCount()
    }
}