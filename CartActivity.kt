package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val rvCartItems = findViewById<RecyclerView>(R.id.rvCartItems)
        val tvTotal = findViewById<TextView>(R.id.tvTotalPrice)
        val btnCheckout = findViewById<AppCompatButton>(R.id.btnCheckout)

        // Настраиваем список, чтобы он шел сверху вниз
        rvCartItems.layoutManager = LinearLayoutManager(this)

        // Функция пересчета общей суммы
        @SuppressLint("SetTextI18n")
        fun updateTotal() {
            tvTotal.text = "${CartManager.getTotalPrice()} ₽"
        }

        // Подключаем наш адаптер к списку
        val adapter = CartAdapter { updateTotal() }
        rvCartItems.adapter = adapter

        // Считаем сумму при открытии
        updateTotal()

        // Кнопка "Оформить"
        btnCheckout.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }
}