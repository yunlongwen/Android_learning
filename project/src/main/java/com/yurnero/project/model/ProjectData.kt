package com.yurnero.project.model

import com.google.gson.annotations.SerializedName


data class ProjectData(
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("isDraft") val isDraft: Boolean,
    @SerializedName("createdTime") val createdTime: Long
)

data class PageInfoResponse(
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalItemCount") val totalItemCount: Int,
    @SerializedName("totalPageCount") val totalPageCount: Int,
    @SerializedName("currentPageItemCount") val currentPageItemCount: Int
)

data class PageInfoRequest(
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("pageSize") val pageSize: Int,
)

data class ProjectResponse(@SerializedName("result") val result: ProjectBean)

data class ProjectBean(
    @SerializedName("data") val data: ArrayList<ProjectData>,
    @SerializedName("pageInfo") val pageInfo: PageInfoResponse
)

data class ProjectListParam(@SerializedName("pageInfo") val pageInfo: PageInfoRequest)
