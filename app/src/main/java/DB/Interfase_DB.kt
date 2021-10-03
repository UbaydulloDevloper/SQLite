package DB

import models.Customer
import models.Employee
import models.Orders

interface Interfase_DB {
    fun addCustomer(customer: Customer)
    fun editCustomer(customer: Customer):Int
    fun deleteCustomer(customer: Customer)
    fun getAllCustomer(): List<Customer>
    fun getCustomerById(id: Int): Customer

    fun addEmploy(employee: Employee)
    fun editEmploy(employee: Employee):Int
    fun deleteEmploy(employee: Employee)
    fun getAllEmploy(): List<Employee>
    fun getEmployById(id: Int): Employee

    fun addOrder(orders: Orders)
    fun editOrder(orders: Orders):Int
    fun deleteOrder(orders: Orders)
    fun getAllOrder(): List<Orders>

}