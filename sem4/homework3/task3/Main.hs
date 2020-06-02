maxFinder :: [Integer] -> Integer
maxFinder [] = error "List is empty"
maxFinder [x] = 1 
maxFinder (x:y:xs) = helper (x:y:xs) 2 1 y where
    helper :: [Integer] -> Integer -> Integer -> Integer -> Integer
    helper [x,y] currentPos posOfMax maxValue = if x > maxValue then currentPos else posOfMax
    helper (x:y:z:xs) currentPos posOfMax maxValue = if x + z > maxValue 
        then helper (y:z:xs) (currentPos + 1) currentPos (x + z)
        else helper (y:z:xs) (currentPos + 1) posOfMax maxValue