package DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import models.Customer
import models.Employee
import models.Orders
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, Constanta.DB_NAME, null, Constanta.DB_VERSIONS), Interfase_DB {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table ${Constanta.CUSTOMER_TABLE}(${Constanta.CUSTOMER_ID} integer not null primary key autoincrement unique, ${Constanta.CUSTOMER_NAME} text not null)")
        p0?.execSQL("create table ${Constanta.EMPLOYEE_TABLE} ( ${Constanta.EMPLOYEE_ID} integer not null primary key autoincrement unique, ${Constanta.EMPLOYEE_NAME} text not null)")
        p0?.execSQL("create table ${Constanta.ORDERS_TABLE} ( ${Constanta.ORDERS_ID} integer not null primary key autoincrement unique,${Constanta.ORDERS_CUSTOMER_ID} integer not null , ${Constanta.ORDERS_EMPLOYEE_ID} integer not null ,${Constanta.ORDERS_DATA} text not null, foreign key (${Constanta.ORDERS_CUSTOMER_ID}) references ${Constanta.CUSTOMER_TABLE}(${Constanta.CUSTOMER_ID}),foreign key (${Constanta.ORDERS_EMPLOYEE_ID}) references ${Constanta.EMPLOYEE_TABLE}(${Constanta.EMPLOYEE_ID}))")


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    override fun addCustomer(customer: Customer) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constanta.CUSTOMER_NAME, customer.name)
        database.insert(Constanta.CUSTOMER_TABLE, null, contentValue)
        database.close()
    }

    override fun editCustomer(customer: Customer): Int {
        TODO("Not yet implemented")
    }

    override fun deleteCustomer(customer: Customer) {
        TODO("Not yet implemented")
    }

    @SuppressLint("Recycle")
    override fun getAllCustomer(): List<Customer> {
        val list = ArrayList<Customer>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constanta.CUSTOMER_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val customer = Customer()
                customer.id = cursor.getInt(0)
                customer.name = cursor.getString(1)
                list.add(customer)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getCustomerById(id: Int): Customer {
        val database = this.readableDatabase
        val cursor = database.query(
            Constanta.CUSTOMER_TABLE,
            arrayOf(
                Constanta.CUSTOMER_ID,
                Constanta.CUSTOMER_NAME
            ),
            "${Constanta.CUSTOMER_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        return Customer(cursor.getInt(0), cursor.getString(1))
    }

    override fun addEmploy(employee:Employee) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constanta.EMPLOYEE_NAME, employee.name)
        database.insert(Constanta.EMPLOYEE_TABLE, null, contentValue)
        database.close()
    }

    override fun editEmploy(employee: Employee): Int {
        TODO("Not yet implemented")
    }

    override fun deleteEmploy(employee: Employee) {
        TODO("Not yet implemented")
    }

    override fun getAllEmploy(): List<Employee> {
        val list = ArrayList<Employee>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constanta.EMPLOYEE_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val customer = Employee()
                customer.id = cursor.getInt(0)
                customer.name = cursor.getString(1)
                list.add(customer)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getEmployById(id: Int): Employee {
        val database = this.readableDatabase
        val cursor = database.query(
            Constanta.EMPLOYEE_TABLE,
            arrayOf(
                Constanta.EMPLOYEE_ID,
                Constanta.EMPLOYEE_NAME
            ),
            "${Constanta.EMPLOYEE_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        return Employee(cursor.getInt(0), cursor.getString(1))
    }

    @SuppressLint("SimpleDateFormat")
    override fun addOrder(orders: Orders) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(Constanta.ORDERS_CUSTOMER_ID, orders.custumor?.id)
        contentValue.put(Constanta.ORDERS_EMPLOYEE_ID, orders.employee?.id)
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy").format(Date())
        contentValue.put(Constanta.ORDERS_DATA, simpleDateFormat)
        database.insert(Constanta.ORDERS_TABLE, null, contentValue)
        database.close()
    }

    override fun editOrder(orders: Orders): Int {
        TODO("Not yet implemented")
    }

    override fun deleteOrder(orders: Orders) {
        TODO("Not yet implemented")
    }

    override fun getAllOrder(): List<Orders> {
        val list = ArrayList<Orders>()
        val database = this.readableDatabase
        val cursor = database.rawQuery("select * from ${Constanta.ORDERS_TABLE}", null)
        if (cursor.moveToFirst()) {
            do {
                val orders = Orders()
                orders.id = cursor.getInt(0)
                orders.custumor = getCustomerById(cursor.getInt(1))
                orders.employee = getEmployById(cursor.getInt(2))
                orders.date = cursor.getString(3)
                list.add(orders)

            } while (cursor.moveToNext())
        }
        return list
    }
}