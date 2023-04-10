package com.app.conectar.api.getProfileResponse


import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("created_ts")
    val createdTs: String, // 2022-10-06 21:58:37
    @SerializedName("document_file")
    val documentFile: String, // https://developer.shyamfuture.in/itsc_calapp/uploads/sos_documents/1MmqhJBx.jpg
    @SerializedName("document_id")
    val documentId: String, // 400
    @SerializedName("document_status")
    val documentStatus: String, // A
    @SerializedName("document_type")
    val documentType: String, // DF
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("updated_ts")
    val updatedTs: String, // 2022-10-06 22:01:12
    @SerializedName("user_id")
    val userId: String // 1838
)