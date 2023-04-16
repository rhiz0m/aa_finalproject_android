package com.taokyone.aa_finalproject_android.model

data class NasaImage(
    val hdurl: String = ""
        ) {
    override fun toString(): String {
        return "NasaImage(hdurl='$hdurl')"
    }
}