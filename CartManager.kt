package com.example.myapplication

object CartManager {
    val items = mutableListOf<Wine>()

    fun addWine(newWine: Wine) {
        val existing = items.find { it.id == newWine.id }
        if (existing != null) {
            existing.quantity++ // Если вино уже есть, добавляем +1
        } else {
            items.add(newWine)  // Если нет, создаем новую карточку
        }
    }

    fun getTotalPrice(): Int {
        return items.sumOf { it.price * it.quantity }
    }

    fun getTotalCount(): Int {
        return items.sumOf { it.quantity }
    }
}