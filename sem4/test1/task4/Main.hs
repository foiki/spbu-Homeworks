superMap :: [a] -> (a -> [b]) -> [b]
superMap list f = foldr ((++) . f) [] list