package com.list_sample.minimumrecyclerview.model.section

import android.support.v4.util.SparseArrayCompat
import android.util.Log

/**
 * Created by monkey on 2017/09/26.
 */
class SectionGroup() {

    private var sections = SparseArrayCompat<Section>()

    fun append(section: Section): SectionGroup {
        sections.append(section.id, section)

        return this
    }

    fun totalCount(): Int {
        var count = 0
        for (i in 0 .. sections.size() -1) {
            val key = sections.keyAt(i)
            count += sections.get(key).count()
        }
        return count
    }

    fun itemAt(position: Int): Any {
        var index = position

        for (i in 0 .. sections.size()) {
            val sectionKey = sections.keyAt(i)
            val section = sections.get(sectionKey)
            val sectionCount = section.count()

            if (index < sectionCount) {
                return section.itemAt(index)
            } else {
                index -= sectionCount
            }
        }
        throw IllegalArgumentException("not found item at position" + position)
    }
}