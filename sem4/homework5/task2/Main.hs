multiplyList:: Integer -> [Integer]
multiplyList number = [1..number] >>= \x -> map (x*) [1..number]