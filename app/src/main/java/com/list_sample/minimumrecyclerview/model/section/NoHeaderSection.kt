package com.list_sample.minimumrecyclerview.model.section

/**
 * Created by monkey on 2017/09/27.
 */
class NoHeaderSection(id: Int, items: List<Any>): Section {
    override val id = id
    val items: List<Any> = items

    override fun count(): Int {
        return items.size
    }

    override fun itemAt(row: Int): Any {
        return items.get(row)
    }
}