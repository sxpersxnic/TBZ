# Test Strategy

## Exercise 1

### Test Cases

#### Abstract

|ID|Input (Price)|Expected Output|
|--|-------------|---------------|
|T1|Price < 15'000|No Discount|
|T2|15'000 ≤ Price < 20'000|5% Discount|
|T3|20'000 < Price < 25'000|7% Discount|
|T4|Price > 25'000|8.5% Discount|

#### Concrete

|ID |Input (Price)|Expected Discount|Expected Price after Discount|
|---|-------------|-----------------|----------------------------|
|T1|14'000|0%|14'000|
|T2|18'000|5%|17'100|
|T3|22'000|7%|20'460|
|T4|30'000|8.5%|27'450|
|T5|15'000|5%|14'250|
|T6|20'000|5%|19'000|
|T7|25'000|8.5%|22'875|

## Exercise 2

**Website:** autovermietung.ch

|ID|Description|Expected Result|Actual Result|Status|Possible Cause|
|--|-----------|---------------|-------------|------|--------------|
|1|User can search for vehicles|Search results are displayed correctly|Search results missing|Fail|Database connection issue|
|2|Book a vehicle|Booking is confirmed, invoice generated|Booking failed|Fail|API timeout|
|3|User registration|Account created, confirmation email sent|Email not sent|Fail|SMTP server offline|
|4|Filter by vehicle type (e.g., SUV)|Filtered list contains only SUVs|Wrong vehicles shown|Fail|Filter logic bug|
|5|Cancel a booking|Booking canceled, refund issued|Booking remains active|Fail|API logic error|

## Exercise 3

### Black-Box Testing

|ID|Description|Expected Result|
|--|-----------|---------------|
|BB1|Create a new account|Account is created, unique account number assigned, initial balance = 0 (or input value)|
|BB2|Deposit money|Balance increases correctly; supports multiple deposits|
|BB3|Withdraw money|Balance decreases correctly; fails if withdrawal > balance|
|BB4|Transfer money to another account (same currency)|Source account decreases, target account increases by the same amount|
|BB5|Transfer money to another account (different currency)|Source account decreases, target increases by converted amount using `convertCurrency`|
|BB6|Delete account|Account removed from bank's internal list; subsequent access returns "account does not exist"|
|BB7|Check balance|Prints the correct balance and currency|
|BB8|Attempt invalid input (non-numeric or invalid currency code)|Program rejects input and asks again|

### White-Box Testing

|ID|Method|Test Idea|
|--|------|---------|
|WB1|`deposit(double amount)`|Test adding money; check balance updates correctly; test negative and zero input|
|WB2|`withdraw(double amount)`|Test withdrawing valid amounts, overdrafts, and boundary conditions (balance = 0)|
|WB3|`transferAmount(Account from, Account to)`|Test valid transfers; check currency conversion|
|WB4|`createAccount(String name, Currency c, double startBalance)`|Ensure account IDs increment correctly; unique IDs for multiple accounts|
|WB5|`deleteAccount(Account a)`|Confirm account is removed from accouns list; ensure printing deleted account triggers correct message|
|WB6|`convertCurrency(double amount, Currency from, Currency to)`|Test conversion rates; edge cases where no conversion occurs|
|WB7|`getAccount(int nr)`|Returns correct account or null if not found; test retrieval for deleted account|

### Improvements / Best Practices

1. `pseudoDelteAccount()` + `deleteAccount()` bug:
	- In Java, assigning `a = null` or calling `pseudoDeleteAccount()` on a local copy does not delete the object outside the method.
	- Current deletion only removes the account from the list bud does not "nullify" the original object if references exist elsewhere.
2. Static counter in Account class:
	- Works for unique IDs, but resets if the app restarts. Consider using a persistent ID generator.
3. Logging:
	- Use proper logging instead of `System.out.println()` for better traceability and log management.
