package com.list_sample.minimumrecyclerview.model.section

/**
 * Created by monkey on 2017/09/26.
 */
interface Section {
    val id: Int
    fun count(): Int
    fun itemAt(row: Int): Any
}