package com.bccowo.naiz.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResult(
    val type: String,
    val name: String,
    val description: String
) : Parcelable

val results = listOf(
    DetectionResult(
        "a",
        "Relief Ari Darma",
        "Menggambarkan terbebas-nya  naga  betina  dari  rudapaksa  naga  jantan dan pergi atau pulang menemui ayahnya yang  merupakan  raja  para  naga.  Naga  betina menceritakan  pada  ayahnya  budi  baik Ari Darma  yang  telah membebaskan dirinya dari perbuatan dosa dan aib yang memalukan. Gambaran relief untuk cerita ini adalah masih nampak suasana hutan. Selanjutnya nampak naga betina sedang bergerak dan menemui  seekor  naga  dalam  posisi  duduk dengan  ekor  menjulur  ke  atas.  Sosok  kedua naga ini menggambarkan sebagai bangsawan nampak pada mahkotanya, dan mahkota ayahnya  nampak  lebih  menandakan  sebagai raja"
    ),
    DetectionResult(
        "b",
        "Relief Mahabrata",
        "Menggambarkan Pandawa dan Kurawa berjudi dadu untuk bertaruh kekayaan. Saat pertaruhan itu terjadi, Dursasana, salah satu anggota Kurawa mempermalukan Drupadi dengan cara menarik pakaian Drupadi. Sehingga, Drupadi telanjang dan akhirnya ia berusaha menutupi tubuhnya kembali dengan membuka gelungan rambutnya. Gelungan rambut Drupadi ini menginformasikan bahwa tradisi menggelung rambut telah ada sejak dulu.\n" + "Akibat kejadian tersebut, akhirnya Pandawa memutuskan untuk pergi dari kerajaan dan menuju hutan. Tidak hanya anggota Pandawa saja yang pergi, tetapi mereka juga membawa serta Kunti (ibu Pandawa), Drupadi, dan punakawan (pembantu dan pengasuh Pandawa)."
    ),
    DetectionResult(
        "c",
        "Releief Arjunawihaha",
        "Menggambarkan sang Arjuna sedang laku tapabrata di gunung Indrakila. Dalam laku taparatanya, Arjuna mendapat ujian dari para dewa untuk melihat keteguhan sang Arjuna dalam lakunya. Para Dewa mengutus tujuh Bidadari untuk menggoda Arjuna dalam laku tapa. Diantara tujuh bidadari itu ada yang sangat terkenal cantiknya, yaitu yang bernama Dewi Supraba dan Dewi Tilottama. Ternyata Arjuna teguh dalam laku tapanya tak tergoda oleh tujuh bidadari itu. Dengan rasa kecewa para Bidadari kembali ke kahyangan dan melaporkan atas kegagalannya kepada Bathara Indra."
    )
)

object ML {
    fun getData(type: String): DetectionResult {
        return results.find { it.type == type } as DetectionResult
    }
}