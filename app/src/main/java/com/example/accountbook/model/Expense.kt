@Entity(tableName = "expenses")
data class Expense(
    @PrimarayKey(auotoGenerate = true)
    val id: Long = 0L,

    val productName: String,     // 상품명
    val amount: Double,          // 금액
    val category: String,        // 카테고리
    val date: Long,              // 날짜
    val photoUri: String? = null // 이미지 URI //null로 할건지 고민
)