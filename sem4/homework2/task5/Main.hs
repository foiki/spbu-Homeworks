sumOfLists :: Num a => [a] -> [a] -> [a] -> [a]
sumOfLists firstList secondList thirdList = sumOfTwoLists (sumOfTwoLists firstList secondList) thirdList where
    sumOfTwoLists [] [] = []
    sumOfTwoLists (x:xs) [] = x : sumOfTwoLists xs []
    sumOfTwoLists [] (y:ys) = y : sumOfTwoLists [] ys
    sumOfTwoLists (x:xs) (y:ys) = (x + y) : sumOfTwoLists xs ys 