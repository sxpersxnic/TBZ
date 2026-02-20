case class Address(street: String, houseNumber: Int, zipCode: String, city: String)

def formatAddress(address: Address): String = {
  s"${address.street} ${address.houseNumber}, ${address.zipCode} ${address.city}"
}

def formatAddresses(addresses: List[Address]): List[String] = addresses.map(formatAddress)

@main def run(): Unit = {
  val addresses = List(
    Address("Main Street", 123, "12345", "Springfield"),
    Address("Elm Street", 456, "67890", "Shelbyville"),
    Address("Oak Avenue", 789, "54321", "Capital City")
  )
  val formattedAddresses = formatAddresses(addresses)
  println(formattedAddresses)
}