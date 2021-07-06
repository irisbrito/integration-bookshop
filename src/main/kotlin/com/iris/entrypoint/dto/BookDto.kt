package com.iris.entrypoint.dto

import java.math.BigDecimal

data class BookDto(
    val id: Long? = null,
    val name: String = "",
    val price: BigDecimal = BigDecimal.ONE,
    val description: String = "",
    val writer: WriterDto = WriterDto(null,"","")
)
