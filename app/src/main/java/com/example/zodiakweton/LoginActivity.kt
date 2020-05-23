package com.example.zodiakweton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private var namaInput : String = ""
    private var tanggalInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        btnHasil.setOnClickListener { validationInput() }
    }
    private fun validationInput(){
        namaInput = naLog.text.toString()
        tanggalInput = daLog.text.toString()
        when {
            namaInput.isEmpty() -> naLog.error = "Nama tidak boleh kosong"
            tanggalInput.isEmpty() -> daLog.error = "Tanggal tidak boleh kosong"
            else -> {
                tampilToast("Berhasil")
                goToHomeActivity()
            }
        }
    }
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("tanggal", tanggalInput)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
