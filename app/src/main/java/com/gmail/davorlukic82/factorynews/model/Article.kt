package com.gmail.davorlukic82.factorynews.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article (
    @PrimaryKey (autoGenerate = true)
    var articleDbId: Long? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    var receivedTime: Long? = null

) : Parcelable
