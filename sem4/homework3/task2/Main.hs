list :: [Integer]
list = 1 : 7 : 9 : concatMap (\x -> [10 * x + 1, 10 * x + 7, 10 * x + 9]) list