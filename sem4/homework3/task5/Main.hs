fibonacci :: Integer -> Integer
fibonacci n = helper 0 1 (abs n) where
    operation = if n > 0 then (+) else (-)
    helper first _ 0 = first
    helper first second n = helper second (operation first second) (n - 1)