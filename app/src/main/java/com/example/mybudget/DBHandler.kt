package com.example.mybudget
import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import java.text.SimpleDateFormat

class DBHandler(context: Context, name: String?,
       factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CUSTOMERS_TABLE = (
                "CREATE TABLE " + TABLE_EXPENSES + "("
                        + COLUMN_ID + " integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_AMOUNT + " REAL NOT NULL,"
                        + COLUMN_CREATED_AT + " date NULL,"
                        + COLUMN_DESCRIPTION + "  varchar(300) NOT NULL, "
                        + COLUMN_CATEGORY + "  varchar(100) NOT NULL" +
                        ")")

        db.execSQL(CREATE_CUSTOMERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES)
        onCreate(db)
    }
    fun addExpense(expense: Expense) {
        val values = ContentValues()
        values.put(COLUMN_AMOUNT, expense.amount)
        values.put(COLUMN_DESCRIPTION, expense.description)
        values.put(COLUMN_CATEGORY, expense.category)
        val db = this.writableDatabase
        db.insert(TABLE_EXPENSES, null, values)
        db.close()
    }
    @SuppressLint("Recycle")
    fun getTotalExpenses(): Float{
        var total=0F
        val query = "SELECT sum(amount) FROM $TABLE_EXPENSES"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            total += cursor.getFloat(0)
        }
        db.close()
        return total
    }
    @SuppressLint("SimpleDateFormat")
    fun getAllExpenses(): List<Expense> {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val query =
            "SELECT * FROM $TABLE_EXPENSES"

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        val list = mutableListOf<Expense>();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val amount = cursor.getFloat(1)
            val created = formatter.parse(cursor.getString(2))
            val description = cursor.getString(3)
            val category = cursor.getString(4)
            list.add(Expense(id, amount, created,description, category))
            cursor.close()
        }
        db.close()
        return list
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "budget.db"
        val TABLE_EXPENSES = "expenses"
        val COLUMN_ID = "_id"
        val COLUMN_AMOUNT = "amount"
        val COLUMN_CREATED_AT = "created_at"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_CATEGORY = "category"
    }
}