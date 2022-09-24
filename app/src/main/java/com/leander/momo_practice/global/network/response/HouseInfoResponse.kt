package com.leander.momo_practice.global.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


/* Created on 2022/9/23 */

@Keep
data class HouseInfoResponse(
    @SerializedName("result")
    val result: Result
) :java.io.Serializable{
    @Keep
    data class Result(
        @SerializedName("count")
        val count: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("sort")
        val sort: String
    ) :java.io.Serializable{
        @Keep
        data class Result(
            @SerializedName("e_category")
            val eCategory: String,
            @SerializedName("e_geo")
            val eGeo: String,
            @SerializedName("e_info")
            val eInfo: String,
            @SerializedName("e_memo")
            val eMemo: String,
            @SerializedName("e_name")
            val eName: String,
            @SerializedName("e_no")
            val eNo: String,
            @SerializedName("e_pic_url")
            val ePicUrl: String,
            @SerializedName("e_url")
            val eUrl: String,
            @SerializedName("_id")
            val id: Int,
            @SerializedName("_importdate")
            val importdate: Importdate
        ) :java.io.Serializable{
            @Keep
            data class Importdate(
                @SerializedName("date")
                val date: String,
                @SerializedName("timezone")
                val timezone: String,
                @SerializedName("timezone_type")
                val timezoneType: Int
            )
        }
    }
}