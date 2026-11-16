package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Сначала загружаем экран
        setContentView(R.layout.activity_registration)

        // 2. Теперь ищем элементы (ОНИ НАЙДУТСЯ, так как экран уже загружен)
        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val etLogin = findViewById<EditText>(R.id.etLogin)      // Поле логина
        val etPassword = findViewById<EditText>(R.id.etPassword) // Поле пароля

        // 3. Вешаем ОДИН обработчик клика на кнопку
        btnLogin.setOnClickListener {
            // Берем текст из полей ввода
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()

            // Проверяем, пустые ли они
            if (login.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите ваш логин!", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите ваш пароль!", Toast.LENGTH_SHORT).show()
            } else {
                // Если всё введено — показываем успех и переходим
                Toast.makeText(this, "Успешный вход!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, CatalogActivity::class.java)
                startActivity(intent)
            }
        }
    }
}