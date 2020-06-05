module Main
    where

import System.IO

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop:: [(String, String)] -> IO()
doLoop list = do
    putStrLn "Enter a command or '6' to see help menu:"
    command <- getLine
    case command of
        '0':_ -> do
            putStrLn "Bye :)"
            return()
        '1':_ -> do
            putStrLn "Enter name:"
            name <- getLine
            putStrLn "Enter phone number:"
            phone <- getLine
            doLoop $ (name, phone) : list
        '2':_ -> do
            putStrLn "Enter name to find phone number:"
            name <- getLine
            putStrLn $ findPhoneNumber list name
            doLoop list
        '3':_ -> do
            putStrLn "Enter phone number to find contact name:"
            number <- getLine
            putStrLn $ findName list number
            doLoop list
        '4':_ -> do
            putStrLn "Enter file name to save info"
            fileName <- getLine
            saveInfoToFile list fileName
            doLoop list
        '5':_ -> do
            putStrLn "Enter file name to load info"
            fileName <- getLine
            phoneBook <- readFile fileName
            doLoop $ parsePhoneBook (lines phoneBook) []
        '6':_ -> do
            putStrLn "0 - exit\n1 - add new contact (name, phone)\n2 - find a phone number by name\n3 - find a contact name by phone number\n4 - save data to file\n5 - load data from file"
            doLoop list
        _ -> do
            putStrLn "Incorrect command!"
            doLoop list
        
findPhoneNumber:: [(String, String)] -> String -> String
findPhoneNumber [] name = "Number not found!"
findPhoneNumber (x:xs) name | name == fst x = snd x 
                            | otherwise = findPhoneNumber xs name

findName:: [(String, String)] -> String -> String
findName [] number = "Contact not found!"
findName (x:xs) number | number == snd x = fst x 
                       | otherwise = findName xs number

saveInfoToFile:: [(String, String)] -> String -> IO()
saveInfoToFile list fileName = withFile fileName WriteMode
    (\x -> hPutStrLn x $ concatMap (\(name, phoneNumber) -> name ++ " " ++ phoneNumber ++ "\n") list)

parsePhoneBook:: [String] -> [(String, String)] -> [(String, String)]
parsePhoneBook xs phoneBook = foldl (\ phoneBook x -> (head $ words x, last $ words x) : phoneBook) phoneBook xs