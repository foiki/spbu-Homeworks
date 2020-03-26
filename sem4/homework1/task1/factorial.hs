module Factorial where

factorial :: Integer -> Integer
factorial x | x >=0 = helper 1 x
            | otherwise = error "argument must be >= 0"
helper result 0 = result
helper result x = helper (result * x) (x - 1)