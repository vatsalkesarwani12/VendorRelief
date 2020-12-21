package com.CodeNaroNa.vendor.relief.modelKotlin

import com.google.firebase.firestore.PropertyName

data class PrecautionData(@get:PropertyName("Q") @set:PropertyName("Q") var question: String = "",
                          @get:PropertyName("Facts") @set:PropertyName("Facts") var answer: String = "")