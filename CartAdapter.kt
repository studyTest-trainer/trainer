package com.example.myapplication // <-- Я добавил букву 'p' в начале

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// В скобках мы передаем функцию, которая будет пересчитывать общую сумму
class CartAdapter(private val onPriceChanged: () -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBottle: ImageView = itemView.findViewById(R.id.imgBottle)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val btnPlus: TextView = itemView.findViewById(R.id.btnPlus)
        val btnMinus: TextView = itemView.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val wine = CartManager.items[position]

        holder.imgBottle.setImageResource(wine.imageRes)
        holder.tvName.text = wine.name
        holder.tvQuantity.text = wine.quantity.toString()
        holder.tvPrice.text = "${wine.price * wine.quantity} ₽"

        // Кнопка ПЛЮС
        holder.btnPlus.setOnClickListener {
            wine.quantity++
            notifyItemChanged(position) // Обновляем цифру на экране
            onPriceChanged()            // Обновляем общую сумму внизу
        }

        // Кнопка МИНУС
        holder.btnMinus.setOnClickListener {
            if (wine.quantity > 1) {
                wine.quantity--
                notifyItemChanged(position)
                onPriceChanged()
            } else {
                // Если нажали минус, когда осталась 1 штука - удаляем товар
                CartManager.items.removeAt(position)
                notifyDataSetChanged()
                onPriceChanged()
            }
        }
    }

    override fun getItemCount(): Int = CartManager.items.size
}