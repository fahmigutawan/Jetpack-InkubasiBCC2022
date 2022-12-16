package com.bcc.brain.utils

import com.bcc.brain.model.domain.DosenDomain
import com.bcc.brain.model.domain.LombaDetailDomain
import com.bcc.brain.model.domain.UserDomain
import com.bcc.brain.model.presenter.Dosen
import com.bcc.brain.model.presenter.LombaDetail
import com.bcc.brain.model.presenter.User

object MapperDomainToPresenter {
    fun mapDosen(input:DosenDomain) =
        Dosen(
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

    fun mapUser(input: UserDomain) =
        User(
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

    fun mapLombaDetail(input: LombaDetailDomain) =
        LombaDetail(
            input.id,
            input.createdAt,
            input.updatedAt,
            input.deletedAt,
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