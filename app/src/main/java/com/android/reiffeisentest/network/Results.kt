package com.android.reiffeisentest.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    val results: List<PersonInfo>
) : Parcelable

@Parcelize
data class PersonInfo(
    val gender: String,
    val location: LocationInfo,
    val email: String,
    val login: LoginInfo,
    val dob: DobInfo,
    val registered: RegisteredInfo,
    val phone: String,
    val cell: String,
    val id: IdInfo,
    val picture: PictureInfo,
    val nat: String
) : Parcelable

@Parcelize
data class LocationInfo(
    val street: StreetInfo,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesInfo,
    val timezone: TimezoneInfo
) : Parcelable

@Parcelize
data class StreetInfo(
    val number: String,
    val name: String
) : Parcelable

@Parcelize
data class CoordinatesInfo(
    val latitude: String,
    val longitude: String
) : Parcelable

@Parcelize
data class TimezoneInfo(
    val offset: String,
    val description: String
) : Parcelable

@Parcelize
data class LoginInfo(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
) : Parcelable

@Parcelize
data class DobInfo(
    val date: String,
    val age: String
) : Parcelable

@Parcelize
data class PictureInfo(
    val large: String,
    val medium: String,
    val thumbnail: String
) : Parcelable

@Parcelize
data class RegisteredInfo(
    val date: String,
    val age: String
) : Parcelable

@Parcelize
data class IdInfo(
    val name: String?=null,
    val value: String?=null
) : Parcelable