package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // 1. Находим все нужные элементы
        val rgPayment = findViewById<RadioGroup>(R.id.rgPayment)
        val llCardDetails = findViewById<LinearLayout>(R.id.llCardDetails)

        // Находим поля ввода (ВОТ ИХ НЕ ХВАТАЛО!)
        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etPhone = findViewById<EditText>(R.id.etPhone)   // <--- ДОБАВЛЕНО
        val etAddress = findViewById<EditText>(R.id.etAddress) // <--- ДОБАВЛЕНО

        val btnPay = findViewById<AppCompatButton>(R.id.btnPay)

        // По умолчанию (при открытии) выбран "Наличными", поэтому карту скрываем
        llCardDetails.visibility = View.GONE

        // 2. Логика переключения между Наличными и Картой
        rgPayment.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbCash) {
                // Если выбрали наличные — скрываем блок с картой
                llCardDetails.visibility = View.GONE
            } else if (checkedId == R.id.rbCard) {
                // Если выбрали карту — показываем блок с картой
                llCardDetails.visibility = View.VISIBLE
            }
        }

        // 3. Логика кнопки "Оплатить"
        btnPay.setOnClickListener {
            val name = etFirstName.text.toString()
            val phone = etPhone.text.toString()
            val address = etAddress.text.toString()

            // Проверяем, заполнены ли поля
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните имя, телефон и адрес!", Toast.LENGTH_SHORT).show()
            } else {
                // Если всё заполнено:
                val paymentMethod = if (rgPayment.checkedRadioButtonId == R.id.rbCash) "наличными" else "картой"

                // Показываем сообщение об успехе, корзина НЕ очищается
                Toast.makeText(this, "Спасибо, $name! Заказ на сумму ${CartManager.getTotalPrice()} ₽ оплачен $paymentMethod.", Toast.LENGTH_LONG).show()
            }
        }
    }
}