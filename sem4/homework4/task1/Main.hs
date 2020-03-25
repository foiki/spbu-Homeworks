mapVariant:: [Int] -> Int
mapVariant = sum . map (\x -> 1 - mod x 2)

filterVariant:: [Int] -> Int
filterVariant = length . filter (\x -> mod x 2 == 0)

foldrVariant:: [Int] -> Int
foldrVariant = foldr (\x result -> result + 1 - mod x 2) 0