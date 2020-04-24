checkAll :: (a -> Bool) -> [a] -> Bool
checkAll f = foldr ((&&) . f) True