findMaxNeighbour:: Ord a => [a] -> Maybe a
findMaxNeighbour [] = Nothing
findMaxNeighbour [x] = Nothing
findMaxNeighbour (x:[y]) = Nothing
findMaxNeighbour (x:y:z:list) = mplus (if x < y && y > z then Just y else Nothing) (findMaxNeighbour (y:z:list))