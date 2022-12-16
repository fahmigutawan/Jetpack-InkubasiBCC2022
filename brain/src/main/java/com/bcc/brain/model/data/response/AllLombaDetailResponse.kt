package com.bcc.brain.model.data.response

import kotlinx.serialization.Serializable

@Serializable
data class AllLombaDetailResponse(
    val success:Boolean,
    val message:String,
    val data: List<LombaDetailData>
)

@Serializable
data class LombaDetailData(
    val id:String,
//    val createdAt:String,
//    val updatedAt:String,
//    val deletedAt:String,
    val nama_lomba:String,
    val gambar:String,
    val deadline_daftar:String,
    val tgl_pengumuman:String,
    val biaya_daftar:String,
    val universitas:String,
    val deskripsi:String,
    val nama_kategori:String,
    val sub_kategori:String
)
