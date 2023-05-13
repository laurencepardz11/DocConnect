package com.nethealth.drconnect.data.remote


sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Error<out T>(val data: T?, val msg: String? = null, val error: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Initial : Resource<Nothing>() // add this line

    companion object{
        fun <T> initial(): Resource<T>  = Initial
        fun <T> loading(): Resource<T>  = Loading
    }

}

fun <T> Resource<T>?.isInitial(): Boolean = this is Resource.Initial // add this line

fun <T> initial(): Resource<T> = Resource.Initial // add this line

//sealed class Resource<out T> {
//    data class Loading<out T>(val data: T?) : Resource<T>()
//    data class Initial<out T>(val data: T?) : Resource<T>()
//    data class Success<out T>(val data: T?, val msg: String? = null, val error: String? = null) : Resource<T>()
//    data class Error<out T>(val data: T?, val msg: String? = null, val error: String? = null) : Resource<T>()
//
//    companion object {
//        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
//        fun <T> initial(data: T? = null): Resource<T> = Loading(data)
//        fun <T> success(data: T?, msg: String? = null, error: String? = null): Resource<T> = Success(data, msg, error)
//        fun <T> error(data: T?, msg: String? = null, error: String? = null): Resource<T> = Error(data, msg, error)
//    }
//}
//
//sealed class NetworkStatus(val status: Status, val msg: String? = null) {
//    object Loading : NetworkStatus(Status.LOADING)
//    object Success : NetworkStatus(Status.SUCCESS)
//    data class Error(val error: String) : NetworkStatus(Status.ERROR)
//}
//
//enum class Status { LOADING, SUCCESS, ERROR }
