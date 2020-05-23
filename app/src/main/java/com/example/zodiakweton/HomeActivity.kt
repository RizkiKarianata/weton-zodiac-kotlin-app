package com.example.zodiakweton

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_home)
        btnBack.setOnClickListener { navigationToLoginActivity() }
        initView()
    }
    private fun navigationToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun initView() {
        callZodiak()
    }
    @SuppressLint("ShowToast")
    fun callZodiak() {
        val bundle = intent.extras
        val datanama = bundle?.getString("nama")
        val datatanggal = bundle?.getString("tanggal")
        val url =
            "https://script.google.com/macros/exec?service=AKfycbw7gKzP-WYV2F5mc9RaR7yE3Ve1yN91Tjs91hp_jHSE02dSv9w&nama=$datanama&tanggal=$datatanggal"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {
            val jsonObject = JSONObject(it)
            val cek = jsonObject.getJSONObject("data")
            tv_nama.text = cek.getString(("nama"))
            tv_zodiak.text = cek.getString(("zodiak"))
            tv_ultah.text = cek.getString(("ultah"))
            tv_usia.text = cek.getString(("usia"))
            tv_weton.text = cek.getString(("lahir"))
        },
            Response.ErrorListener {
                Toast.makeText(this, "Kesalahan", Toast.LENGTH_SHORT)
                tv_nama.text = "-"
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
