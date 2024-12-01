package com.example.callapiinandroidkotlin.data

import android.view.View

/**
 * ( sealed class có tên là Resource, được thiết kế để đóng gói thông tin về trạng thái của việc tải dữ liệu )
 * Class này chứa các phương thức và Class con để biểu diễn các trạng thái khác nhau
 * của quá trình "tải dữ liệu", chẳng hạn như "thành công", "đang tải", "lỗi", và "xử lý".
 * */

sealed class Resource<out T>(val data: T? = null, val errorCode : Int? = null) {
    /**
     * Trong đoạn mã của bạn, Out<T> được sử dụng để chỉ ra rằng tham số kiểu T là covariant (ngược với thứ bậc), và nó chỉ có thể được sử dụng ở vị trí của giá trị trả về (output) từ lớp đó.
     *
     * Trong ngữ cảnh của Resource, Out<T> sẽ là lớp con của Out<Nothing>, vì Out<Nothing> là một trường hợp đặc biệt của Out<T> (với T là bất kỳ kiểu dữ liệu nào).*/
    class Success<out T>(data: T?) : Resource<T>(data)
    object Loading : Resource<Nothing>()
    class Error(errorCode: Int? = null, var message : String? = null) : Resource<Nothing>(null, errorCode)
    class Processing<T>(val process: Float) : Resource<T>()

    override fun toString(): String {
        return when(this){
            // is Success<*> sử dụng * để chỉ đến một đối tượng Success với bất kỳ kiểu dữ liệu cụ thể nào
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorCode]"
            is Loading -> "Loading"
            is Processing -> "Processing[process = $$process]"
        }
    }

    /**
     * Phương thức này nhận một tham số là một hàm (callback) có kiểu là suspend (Success<T>) -> Unit.
     *
     * suspend: Điều này cho biết rằng hàm callback có thể chứa các triển khai coroutine.
     *
     * (Success<T>) -> Unit: Đây là kiểu hàm callback. Nó chỉ định rằng hàm callback nhận một tham số kiểu Success<T> (tức là một đối tượng Success với kiểu dữ liệu là T) và không trả về giá trị (Unit).*/
    suspend fun whenSuccess(callback : suspend (Success<T>) -> Unit) = apply {
        if (this is Success){
            callback(this)
        }
    }

    suspend fun whenError(callback: suspend (Error) -> Unit) = apply {
        if (this is Error) {
            callback(this)
        }
    }

    suspend fun whenLoading(callback: suspend (Loading) -> Unit) = apply {
        if (this is Loading) {
            callback(this)
        }
    }
}