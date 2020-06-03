brackets :: String -> Bool
brackets string | string == [] = True
                | otherwise = helper string [] where
                    helper [] [] = True
                    helper [] brackets = False
                    helper (x:string) [] | isOpeningBracket x = helper string [x]
                                         | isClosingBracket x = False
                                         | otherwise = helper string [] 
                    helper (x:string) (y:brackets) | isOpeningBracket x = helper string (x:y:brackets)
                                                   | isClosingBracket x = isPairCorrect x y && helper string brackets
                                                   | otherwise = helper string (y:brackets)
                                             
                    isOpeningBracket :: Char -> Bool
                    isOpeningBracket s = s == '(' || s == '{' || s == '['

                    isClosingBracket :: Char -> Bool
                    isClosingBracket s = s == ')' || s == '}' || s == ']'

                    isPairCorrect :: Char -> Char -> Bool
                    isPairCorrect x y = case x of ')' -> y == '('
                                                  '}' -> y == '{'
                                                  ']' -> y == '['