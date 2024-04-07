package com.example.mybudget
import java.time.LocalDateTime
import java.util.Date

class Expense {
    var id: Int=0
    var amount: Float=0F
    var created: Date? = null
    var description: String? = ""
    var category: String? = ""
    constructor(id:Int, amount: Float, created:Date, description: String, category: String){
        this.id=id
        this.amount=amount
        this.created=created
        this.description=description
        this.category=category
    }
    constructor(amount: Float,  description: String, category: String){
        this.amount=amount
        this.created=Date()
        this.description=description
        this.category=category
    }
}