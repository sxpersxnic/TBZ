case class Employee(name: String, department: String, salary: Int)

def filterEmployeesByDepartmentAndSalary(department: String, salaryThreshold: Int, employees: List[Employee]): List[Employee] = {
  employees.filter(e => e.department == department && e.salary > salaryThreshold)
}

def getUppercaseFirstNamesOfEmployees(employees: List[Employee]): List[String] = {
  employees.map(_.name.toUpperCase)
}

@main def run(): Unit = {
  val employees = List(
    Employee("Alice", "IT", 70000),
    Employee("Bob", "Marketing", 50000),
    Employee("Charlie", "IT", 40000),
    Employee("David", "HR", 45000)
  )
  val itHighEarners = filterEmployeesByDepartmentAndSalary("IT", 50000, employees)
  val itHighEarnerUppercaseFirstNames = getUppercaseFirstNamesOfEmployees(itHighEarners)
  println(itHighEarnerUppercaseFirstNames)
}