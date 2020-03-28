degreesOfTwo :: Integer -> [Integer]
degreesOfTwo degree = helper degree [] where 
    helper 0 result = 1 : result
    helper degree result = helper (degree - 1) (2 ^ degree : result)