package  com.example.techcrunchpostapi.model

import com.google.gson.annotations.SerializedName

data class Title(

    @SerializedName("rendered") val rendered: String
)