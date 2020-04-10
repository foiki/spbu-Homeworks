angleMatrix:: Integer -> [[Integer]]
angleMatrix n = map (\x -> (sumLists [1..n] . reverse) [0..x - 1]) [1..n]

sumLists :: [Integer] -> [Integer] -> [Integer]
sumLists [] list = list
sumLists list [] = list
sumLists (x:xs) (y:ys) = x + y : sumLists xs ys