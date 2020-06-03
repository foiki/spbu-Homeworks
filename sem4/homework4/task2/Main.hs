module Main
    where

import System.IO

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop list = do
    putStrLn "Enter a command or '4' to see help menu:"
    command <- getLine
    case command of
        '0':_ -> do
            putStrLn "Bye :)"
            return()
        '1':_ -> do
            putStrLn "Enter value to add:"
            value <- getLine
            doLoop (addElement list (read value))
        '2':_ -> do
            putStrLn "Enter value to remove:"
            value <- getLine
            doLoop (removeElement list (read value))
        '3':_ -> do
            print list
            doLoop list
        '4':_ -> do
            putStrLn "0 - exit\n1 - add value to sorted list\n2 - remove value from list\n3 - print list "
            doLoop list
        _ -> do
            putStrLn "Incorrect command!"
            doLoop list
        
        
addElement:: [Integer] -> Integer -> [Integer]
addElement [] element = [element]
addElement (x:xs) element | x >= element = element : x : xs
                          | otherwise = x : addElement xs element

removeElement:: [Integer] -> Integer -> [Integer]
removeElement [] element = []
removeElement (x:xs) element | x == element = xs
                             | otherwise = x : removeElement xs element
