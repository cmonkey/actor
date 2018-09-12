package org.github.cmonkey.actor

case class Customer(age: Int)

class Cigarettes

case class UnderAgeException(message:String) extends Exception(message)

object CigarettesApp extends App {
  def buyCigareties(customer: Customer): Cigarettes = {
    if (customer.age < 16) {
      throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
    } else {
      new Cigarettes
    }

  }

  val buy = buyCigareties(Customer(12))

  val youngCustomer = Customer(15)

  try{
    buyCigareties(youngCustomer)
    "You age"
  }catch{
    case UnderAgeException(msg) => msg
  }
}
