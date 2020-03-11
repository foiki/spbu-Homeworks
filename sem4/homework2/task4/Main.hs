findFirstPosition :: Integer -> [Integer] -> Integer
findFirstPosition number list = helper number list 0 where
      helper number [] position = error "Number not found"
      helper number list position = if (head list == number) 
            then position
            else helper number (tail list) (position + 1)