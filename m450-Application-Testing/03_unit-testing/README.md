# Unit Testing

## Exercise 1

[calculator](exercise-1)

## Exercise 2

### JUnit 5 Summary

#### Overview

-   JUnit Platform - Test engine launcher
-   JUnit Jupiter - New test API (what developers use)
-   JUnit Vintage - For legacy JUnit 3/4 tests

#### Common Features

1. `@Test` - Defines a standard test method.
2. Assertions - Used to check expected results
3. `@BeforeEach` and `@AfterEach` - Setup and teardown methods for each test.
4. `@BeforeAll` and `@AfterAll` - Setup and teardown methods for the entire test class.
5. Parameterized Tests - Run the same test with different inputs.
6. `@DisplayName` - Custom names for test methods.
7. Test Suite - Group test classes together.

#### Useful Annotations Overview

| Annotation                                    | Purpose                   |
| --------------------------------------------- | ------------------------- |
| `@Test`                                       | Marks a test method       |
| `@BeforeEach`                                 | Run before each test      |
| `@AfterEach`                                  | Run after each test       |
| `@BeforeAll`                                  | Run once before all tests |
| `@AfterAll`                                   | Run once after all tests  |
| `@Disabled`                                   | Skip a test               |
| `@ParameterizedTest`                          | Multiple inputs, one test |
| `@ValueSource`, `@CsvSource`, `@MethodSource` | Data providers            |

## Exercise 3

### Bank Application Overview

The Java application is a simple banking system that simulates basic bank operations.

#### Classes

| Class                             | Description                                                                                                                                        |
| --------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Bank`                            | Central management class that holds all accounts in a `TreeMap`. Creates and manages different account types (Savings, Salary, PromoYouthSavings). |
| `Account`                         | Abstract base class for all account types. Contains common properties (`id`, `balance`) and methods (`deposit`, `withdraw`, `print`).              |
| `Booking`                         | Represents a single transaction with a `date` (bank days since 1.1.1970) and `amount` (in Millirappen).                                            |
| `SavingsAccount`                  | Standard savings account - cannot withdraw more than the current balance.                                                                          |
| `SalaryAccount`                   | Salary account with a `creditLimit` - allows negative balance up to the limit.                                                                     |
| `PromoYouthSavingsAccount`        | Youth savings account that adds a 1% bonus on every deposit.                                                                                       |
| `BankUtils`                       | Utility class for formatting dates and amounts.                                                                                                    |
| `AccountBalanceComparator`        | Comparator for sorting accounts by balance (descending).                                                                                           |
| `AccountInverseBalanceComparator` | Comparator for sorting accounts by balance (ascending).                                                                                            |

#### Key Features

1. **Account Creation**: Bank generates unique account IDs with prefixes:

    - `S-xxxx` for Savings Accounts
    - `Y-xxxx` for PromoYouth Savings Accounts
    - `P-xxxx` for Salary (Payroll) Accounts

2. **Transaction Validation**:

    - `canTransact(date)` ensures transactions are chronologically ordered
    - Negative amounts are rejected
    - Withdrawal limits are enforced per account type

3. **Monetary Values**: All amounts are stored in **Millirappen** (1/100,000 of a Swiss Franc) for precision

4. **Date System**: Uses a simplified bank date system (days since 1.1.1970, assuming 30-day months and 360-day years)

#### Test Classes

Located in `src/test/java/ch/schule/bank/junit5/`:

| Test Class                      | Tests For                                            |
| ------------------------------- | ---------------------------------------------------- |
| `AccountTests`                  | Base account functionality                           |
| `BankTests`                     | Bank operations (create, deposit, withdraw, balance) |
| `BookingTests`                  | Booking/transaction handling                         |
| `SavingsAccountTests`           | Savings account specific behavior                    |
| `SalaryAccountTests`            | Salary account with credit limit                     |
| `PromoYouthSavingsAccountTests` | Youth account bonus calculation                      |

## Exercise 4

Tests are [here](./exercise-3/02_bank-vorgabe/src/test/java/ch/schule/bank/junit5)
