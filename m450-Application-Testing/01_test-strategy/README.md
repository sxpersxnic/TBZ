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

## Exercise 3
