package com.heda.models

data class Recipe (
    val name: String?,
    val id: Int,
    val user_id: Int?,
    val recipe_kind_id: Int?,
    val preparation_time: Int?,
    val cooking_time: Int?,
    val total_time: Int?,
    val json: String?,
    val ingredients: String?,
    val image_slug: String?,
    val original_id: Int?,
    val is_public: Int?,
    val raw_servings: String?,
    val heda_instructions: String?
)