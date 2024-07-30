package com.nasza.quizpraktikapipublik.model

import android.os.Parcel
import android.os.Parcelable

data class RecipeResponse(
    val hits: List<Hit>
)

data class Hit(
    val recipe: Recipe
)

data class Recipe(
    val label: String,
    val image: String,
    val url: String,
    val ingredientLines: List<String>,
    val nutrients: Nutrients
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readParcelable(Nutrients::class.java.classLoader) ?: Nutrients(Nutrient(0.0, ""))
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeString(image)
        parcel.writeString(url)
        parcel.writeStringArray(ingredientLines.toTypedArray())
        parcel.writeParcelable(nutrients, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}

data class Nutrients(
    val ENERC_KCAL: Nutrient
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Nutrient::class.java.classLoader) ?: Nutrient(0.0, "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(ENERC_KCAL, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Nutrients> {
        override fun createFromParcel(parcel: Parcel): Nutrients {
            return Nutrients(parcel)
        }

        override fun newArray(size: Int): Array<Nutrients?> {
            return arrayOfNulls(size)
        }
    }
}

data class Nutrient(
    val quantity: Double,
    val unit: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(quantity)
        parcel.writeString(unit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Nutrient> {
        override fun createFromParcel(parcel: Parcel): Nutrient {
            return Nutrient(parcel)
        }

        override fun newArray(size: Int): Array<Nutrient?> {
            return arrayOfNulls(size)
        }
    }
}

