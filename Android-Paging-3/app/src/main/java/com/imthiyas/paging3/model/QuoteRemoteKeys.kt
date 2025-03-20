package com.imthiyas.paging3.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("QuoteRemoteKeysTable")
data class QuoteRemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)