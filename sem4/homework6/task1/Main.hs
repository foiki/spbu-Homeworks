data Polynom a = Null | a :+: Polynom a deriving Eq

instance (Ord a, Num a, Show a) => Show (Polynom a) where
    show Null = ""
    show (x:+:xs) = show x ++ helper xs 1 where
        helper Null _ = ""
        helper (x:+:xs) 1 = numberWithSign x ++ "x" ++ helper xs 2
        helper (x:+:xs) power = numberWithSign x ++ "x^" ++ show power ++ helper xs (power + 1)

numberWithSign:: (Ord a, Num a, Show a) => a -> String
numberWithSign x = if x < 0 then show x else "+" ++ show x

instance (Num a) => Num (Polynom a) where
    x + Null = x
    Null + x = x
    (x:+:xs) + (y:+:ys) = (x + y) :+: (xs + ys)

    x * Null = Null
    Null * x = Null
    (x:+:xs) * (y:+:ys) = (x * y) :+: (xs * (y:+:Null) + (x:+:xs) * ys)

fromList:: Num a => [a] -> Polynom a
fromList = foldr (:+:) Null

test1 = (2 :+: (3 :+: Null)) * (2 :+: (3 :+: Null)) == (4 :+: (12 :+: (9 :+: Null)))