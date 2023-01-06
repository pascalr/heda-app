package com.heda.models

data class Mark (
    val type: String
)

data class Attrs (
    val raw: String?,
    val first: Boolean?
)

data class Node (
    val type: String,
    val text: String?,
    val content: List<Node>?,
    val marks: List<Mark>?,
    val attrs: Attrs?
) {
    fun printView() {

    }
}