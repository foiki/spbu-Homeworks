module Fibonachi where

fibonachi :: Integer -> Integer
fibonachi n = if (n > 1) then fibonachi (n - 1) + fibonachi (n - 2) else 1