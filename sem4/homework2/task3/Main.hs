sumOfDigits :: Integer -> Integer
sumOfDigits 0 = 0
sumOfDigits number = mod number 10 + sumOfDigits (div number 10)