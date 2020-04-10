printRhomb :: Integer -> IO()
printRhomb = putStr . rhomb where
    rhomb size = rhombHelper size 1 where
        rhombHelper size level = if size == level 
                                then rhombRow size size
                                else rhombRow level size 
                                    ++ rhombHelper size (level + 1) 
                                    ++ rhombRow level size

rhombRow:: Integer -> Integer -> String
rhombRow level size = rhombRowHelper (size - level) level ++ "\n" where
    rhombRowHelper 0 1 = "*"
    rhombRowHelper 0 n = "*" ++ rhombRowHelper 0 (n - 1) ++ "*"
    rhombRowHelper t n = " " ++ rhombRowHelper (t - 1) n ++ " "

