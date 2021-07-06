package com.iris.core.model

import java.math.BigDecimal

data class Book(
    val id: Long? = null,
    val name: String = "",
    val price: BigDecimal = BigDecimal.ONE,
    val description: String = "",
    val writer: Writer = Writer(null,"","")
)
