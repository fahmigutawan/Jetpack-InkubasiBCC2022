package com.bcc.brain.utils

object MonthMapper {
    private val data = listOf(
        MonthMapperModel("1", "Januari"),
        MonthMapperModel("2", "Februari"),
        MonthMapperModel("3", "Maret"),
        MonthMapperModel("4", "April"),
        MonthMapperModel("5", "Mei"),
        MonthMapperModel("6", "Juni"),
        MonthMapperModel("7", "Juli"),
        MonthMapperModel("8", "Agustus"),
        MonthMapperModel("9", "September"),
        MonthMapperModel("10", "Oktober"),
        MonthMapperModel("11", "November"),
        MonthMapperModel("12", "Desember"),
    )

    private val dataMapped = data.map { it.monthCount to it.montWord }.toMap()

    fun getMount(count:String) = dataMapped[count.trim()] ?: "Tidak diketahui"
}

data class MonthMapperModel(
    val monthCount:String,
    val montWord:String
)