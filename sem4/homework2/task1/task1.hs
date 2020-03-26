reverseList :: [a] -> [a]
reverseList list = helper list [] where 
      helper list result = case (list, result) of
            ([], _) -> result
            (x:xs, _) -> (helper xs) (x : result)