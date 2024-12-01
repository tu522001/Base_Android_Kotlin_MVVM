package com.example.callapiinandroidkotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.DialogFragment
import com.example.callapiinandroidkotlin.R
import com.example.callapiinandroidkotlin.databinding.FragmentCustomDialogBinding

class BasePopup : DialogFragment() {

    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!
    private var messages : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPopupClose.setOnClickListener {dismiss()}
        binding.tvPopupMessage.text = messages
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showPopup(fragmentManager: FragmentManager, errorMessage: String) {
        this.show(fragmentManager, "CustomPopup")
        this.messages = errorMessage
    }

    override fun onStart() {
        super.onStart()
        handlePopup()
        handleClosePopup()
    }

    private fun handlePopup() {
        dialog?.window?.let {
            handleSizeLayout(it)
            handleCornersPopup(it)
        }
    }

    /**
     *  Ngăn không cho đóng khi nhấn ngoài popup
     * */
    private fun handleClosePopup() {
        dialog?.let {
            it?.setCanceledOnTouchOutside(false) // Ngăn việc đóng popup khi nhấn ngoài
            it?.setCancelable(false)// Đảm bảo popup không đóng khi nhấn nút back
        }
    }

    /**
     *  Thiết lập kích thước cho popup
     * */
    private fun handleSizeLayout(window: Window) {
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val popupWidth = (screenWidth * 0.9).toInt()  // 90% chiều rộng màn hình
        val params = window.attributes  // Thay đổi chiều rộng của popup
        params.width = popupWidth  // Chiều rộng 90% màn hình
        params.height = WindowManager.LayoutParams.WRAP_CONTENT  // Chiều cao tự động
        window.attributes = params
    }

    /**
     * Bo góc, padding popup
     * */
    private fun handleCornersPopup(window: Window) {
        window.setBackgroundDrawableResource(R.drawable.popup_background)  // Áp dụng background với bo góc và viền
        val padding = resources.getDimensionPixelSize(R.dimen.padding_16dp) // Thêm padding 16dp cho popup
        window.decorView.setPadding(padding, padding, padding, padding)
    }

}
