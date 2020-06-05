module Fibonacci where

fibonacci :: Integer -> Integer
fibonacci n | n == 0 = 0
            | n == 1 = 1
            | n >= 2 = helper 1 1 (n - 2)
            | otherwise = error "Wrong index"
helper first second 0 = second
helper first second n = helper second (first + second) (n - 1)