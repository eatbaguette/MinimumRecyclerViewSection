package com.list_sample.minimumrecyclerview.model.section

import com.list_sample.minimumrecyclerview.model.SectionHeaderModel

/**
 * Created by monkey on 2017/09/26.
 */
class HeaderSection(id: Int, sectionHeaderModel: SectionHeaderModel, items: List<Any>): Section {
    override val id = id
    val sectionHeader = sectionHeaderModel
    val items: List<Any> = items

    override fun count(): Int {
        return if (items.isEmpty()) 0 else items.size + 1
    }

    override fun itemAt(row: Int): Any {
        if (row == 0) {
            return sectionHeader
        } else {
            return items.get(row - 1)
        }
    }
}