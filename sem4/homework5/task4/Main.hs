data Expression = Const Float | 
                Var | 
                Sum Expression Expression | 
                Subtr Expression Expression |
                Mult Expression Expression |
                Div Expression Expression |
                SignMinus Expression |
                Pow Expression Float deriving (Eq)

instance Show Expression where
    show (Const a) = if floor a == ceiling a then show $ round a else show a
    show Var = "x"
    show (Sum a b) = "(" ++ show a ++ " + " ++ show b ++ ")"
    show (Subtr a b) = "(" ++ show a ++ " - " ++ show b ++ ")"
    show (Mult a b) = "(" ++ show a ++ " * " ++ show b ++ ")"
    show (Div a b) = "(" ++ show a ++ " / " ++ show b ++ ")"
    show (SignMinus a) = "(-" ++ show a ++ ")"
    show (Pow a b) = "(" ++ show a ++ "^" ++ show b ++ ")"

derivative:: Expression -> Expression
derivative = simplify . simplify . derivative' . simplify

derivative':: Expression -> Expression
derivative' expr = case expr of
    Const a -> Const 0
    Var -> Const 1
    Sum a b -> Sum (derivative' a) (derivative' b)
    Subtr a b -> Subtr (derivative' a) (derivative' b)
    Mult a b -> Sum (Mult (derivative' a) b) (Mult a (derivative' b))
    Div a b -> Div (Subtr (Mult (derivative' a) b) (Mult a (derivative' b))) (Pow b 2)
    SignMinus a -> SignMinus (derivative' a)
    Pow a b -> Mult (Const b) (Mult (Pow a (b - 1)) (derivative a))

    
simplify:: Expression -> Expression
simplify expr = case expr of
    (Sum a (Const 0)) -> simplify a
    (Sum (Const 0) a) -> simplify a
    (Subtr a (Const 0)) -> simplify a
    (Subtr (Const 0) a) -> simplify a
    (Mult a (Const 1)) -> simplify a
    (Mult (Const 1) a) -> simplify a
    (Mult a (Const 0)) -> Const 0
    (Mult (Const 0) a) -> Const 0
    (Div (Const 0) _) -> Const 0
    (Div a (Const 1)) -> simplify a
    (Pow a 0) -> Const 1
    (Pow a 1) -> simplify a

    (Sum (Const x) (Const y))  -> Const (x + y)
    (Subtr (Const x) (Const y)) -> Const (x - y)
    (Mult (Const x) (Const y)) -> Const (x * y)
    (Div (Const x) (Const y))  -> Const (x / y)

    (Sum Var Var) -> Mult (Const 2) Var
    (Subtr Var Var) -> Const 0
    (Mult Var Var) -> Pow Var 2
    (Div Var Var)  -> Const 1

    (Const a) -> Const a
    Var -> Var
    (Sum a b) -> Sum (simplify a) (simplify b)
    (Subtr a b) -> Subtr (simplify a) (simplify b)
    (Mult a b) -> Mult (simplify a) (simplify b)
    (Div a b) -> Div (simplify a) (simplify b)
    (SignMinus (SignMinus a)) -> simplify a

    expr -> expr


