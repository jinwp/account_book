package com.example.accountbook

import Expense
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val onItemClick: (Expense) -> Unit,
    private val onDeleteClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var expenses = emptyList<Expense>()

    class ExpenseViewHolder(private val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense, onItemClick: (Expense) -> Unit, onDeleteClick: (Expense) -> Unit) {
            binding.apply {
                textProductName.text = expense.productName
                textAmount.text = "₩${NumberFormat.getNumberInstance().format(expense.amount)}"
                textCategory.text = expense.category
                textDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(expense.date))

                // 사진이 있는 경우만 이미지뷰 표시
                if (expense.photoUri != null) {
                    imagePhoto.visibility = View.VISIBLE
                    Glide.with(itemView.context)
                        .load(expense.photoUri)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_error)
                        .into(imagePhoto)
                } else {
                    imagePhoto.visibility = View.GONE
                }

                // 클릭 이벤트
                root.setOnClickListener { onItemClick(expense) }
                buttonDelete.setOnClickListener { onDeleteClick(expense) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = expenses[position]
        holder.bind(currentExpense, onItemClick, onDeleteClick)
    }

    override fun getItemCount(): Int = expenses.size

    fun submitList(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }
}