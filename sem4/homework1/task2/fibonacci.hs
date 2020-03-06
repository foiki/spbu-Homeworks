module Fibonacci where

fibonacci :: Integer -> Integer
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = if n >= 2 then fibonacci (n - 1) + fibonacci (n - 2) else error "Incorrect index"