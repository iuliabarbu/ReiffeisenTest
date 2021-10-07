package com.android.reiffeisentest.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    var results: MutableList<ResultItem>
) : Parcelable

@Parcelize
data class ResultItem(
    val gender: String?=null,
    val name : NameInfo?=null,
    val location: LocationInfo,
    val email: String?=null,
    val login: LoginInfo,
    val dob: DobInfo,
    val registered: RegisteredInfo,
    val phone: String?=null,
    val cell: String?=null,
    val id: IdInfo,
    val picture: PictureInfo,
    val nat: String?=null
) : Parcelable

@Parcelize
data class NameInfo(
    val title: String?=null,
    val first: String?=null,
    val last: String?=null
) : Parcelable

@Parcelize
data class LocationInfo(
    val street: StreetInfo,
    val city: String?=null,
    val state: String?=null,
    val country: String?=null,
    val postcode: String?=null,
    val coordinates: CoordinatesInfo,
    val timezone: TimezoneInfo
) : Parcelable

@Parcelize
data class StreetInfo(
    val number: String?=null,
    val name: String?=null
) : Parcelable

@Parcelize
data class CoordinatesInfo(
    val latitude: String?=null,
    val longitude: String?=null
) : Parcelable

@Parcelize
data class TimezoneInfo(
    val offset: String?=null,
    val description: String?=null
) : Parcelable

@Parcelize
data class LoginInfo(
    val uuid: String?=null,
    val username: String?=null,
    val password: String?=null,
    val salt: String?=null,
    val md5: String?=null,
    val sha1: String?=null,
    val sha256: String?=null
) : Parcelable

@Parcelize
data class DobInfo(
    val date: String?=null,
    val age: String?=null
) : Parcelable

@Parcelize
data class PictureInfo(
    val large: String?=null,
    val medium: String?=null,
    val thumbnail: String?=null
) : Parcelable

@Parcelize
data class RegisteredInfo(
    val date: String?=null,
    val age: String?=null
) : Parcelable

@Parcelize
data class IdInfo(
    val name: String?=null,
    val value: String?=null
) : Parcelable