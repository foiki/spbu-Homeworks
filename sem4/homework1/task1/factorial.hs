module Factorial where

factorial :: Integer -> Integer
factorial 0 = 1
factorial x = if x >= 0 then factorial (x - 1) * x else error "argument must be >= 0"