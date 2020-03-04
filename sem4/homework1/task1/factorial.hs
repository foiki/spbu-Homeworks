module Factorial where

factorial :: Integer -> Integer
factorial x = if x > 1 then factorial (x - 1) * x else 1