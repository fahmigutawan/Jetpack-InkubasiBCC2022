package com.bcc.brain.utils

import com.bcc.brain.model.data.response.LombaDetailData
import com.bcc.brain.model.data.response.DosenResponse
import com.bcc.brain.model.data.response.UserResponse
import com.bcc.brain.model.domain.DosenDomain
import com.bcc.brain.model.domain.LombaDetailDomain
import com.bcc.brain.model.domain.UserDomain

object MapperResponseToDomain {
    fun mapDosen(input:DosenResponse) =
        DosenDomain(
            input.ID,
            input.Nama,
            input.Email,
            input.IDLine,
            input.NomorWA,
            input.Prodi,
            input.Bidang,
            input.StatusKesediaan,
            input.Notifikasi
        )

    fun mapUser(input:UserResponse) =
        UserDomain(
            input.ID,
            input.Nama,
            input.Email,
            input.IDLine,
            input.NomorWA,
            input.Prodi,
            input.BidangDikuasai,
            input.StatusKesediaan,
            input.Notifikasi
        )

    fun mapLombaDetail(input: LombaDetailData) =
        LombaDetailDomain(
            input.id,
            "",
            "",
            "",
            input.nama_lomba,
            input.gambar,
            input.deadline_daftar,
            input.tgl_pengumuman,
            input.biaya_daftar,
            input.universitas,
            input.deskripsi,
            input.nama_kategori,
            input.sub_kategori
        )
}