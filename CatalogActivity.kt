package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CatalogActivity : AppCompatActivity() {

    private lateinit var tvCartBadge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        // 1. Находим корзину и кружочек
        val navCart = findViewById<FrameLayout>(R.id.nav_cart_layout)
        tvCartBadge = findViewById(R.id.tvCartBadge)

        updateBadge()

        // Переход в корзину
        navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        // 2. Находим плюсики (только те, что есть в твоем XML)
        val btnAdd1 = findViewById<TextView>(R.id.btnAdd1)
        val btnAdd2 = findViewById<TextView>(R.id.btnAdd2)

        // 3. Добавляем вина в память
        btnAdd1.setOnClickListener {
            // Первое вино (Розовое/Красное по твоему XML) - 890 руб
            CartManager.addWine(Wine(1, "Вино Adagum (Розовое)", 890, R.drawable.img_bottle_rose))
            updateBadge()
            Toast.makeText(this, "Добавлено в корзину!", Toast.LENGTH_SHORT).show()
        }

        btnAdd2.setOnClickListener {
            // Второе вино (Белое) - 1250 руб
            CartManager.addWine(Wine(2, "Вино Adagum (Белое)", 1250, R.drawable.img_bottle_white))
            updateBadge()
            Toast.makeText(this, "Добавлено в корзину!", Toast.LENGTH_SHORT).show()
        }
    }

    // Обновление кружочка с цифрой
    private fun updateBadge() {
        val count = CartManager.getTotalCount()
        if (count > 0) {
            tvCartBadge.visibility = View.VISIBLE
            tvCartBadge.text = count.toString()
        } else {
            tvCartBadge.visibility = View.GONE
        }
    }
}