package  com.example.techcrunchpostapi.model

import com.google.gson.annotations.SerializedName

data class Content(

    @SerializedName("rendered") val rendered: String,
    @SerializedName("protected") val protected: Boolean
)