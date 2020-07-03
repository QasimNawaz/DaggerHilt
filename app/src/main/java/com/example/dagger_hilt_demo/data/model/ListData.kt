package com.example.dagger_hilt_demo.data.model

import com.google.gson.annotations.SerializedName

data class ListData(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("ad")
	val ad: Ad? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null
)

data class Ad(

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class DataItem(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("pantone_value")
	val pantoneValue: String? = null
)
