decomposition:: Int -> [[Int]]
decomposition n | n <= 0 = error "Number must be positive"
                | otherwise = helper 1 n where
                    helper _ 0     = [[]]
                    helper startValue endValue = [x : prevValDecompos | x <- [startValue..endValue], prevValDecompos <- helper x $ endValue - x]