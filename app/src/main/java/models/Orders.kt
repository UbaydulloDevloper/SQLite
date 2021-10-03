package models

class Orders {

    var id: Int? = null
    var custumor: Customer? = null
    var employee: Employee? = null
    var date: String? = null

    constructor(id: Int?, custumor: Customer?, Employee: Employee?) {
        this.id = id
        this.custumor = custumor
        this.employee = Employee
    }

    constructor(custumor: Customer?, Employee: Employee?, date: String?) {
        this.custumor = custumor
        this.employee = Employee
        this.date = date
    }


    constructor()
    constructor(custumor: Customer?, employee: Employee?) {
        this.custumor = custumor
        this.employee = employee
    }


}