import System.IO

data PriorityQueueElement = PriorityQueueElement {
    value:: String,
    priority:: Integer
} deriving (Show)

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop queue = do
    putStrLn "Enter a command or '5' to see help menu:"
    command <- getLine
    case command of
        '0':_ -> do
            putStrLn "Bye :)"
            return()
        '1':_ -> do
            putStrLn "Enter value to add:"
            value <- getLine
            putStrLn "Enter it's priority:"
            priority <- getLine
            doLoop (addElement queue value (read priority))
        '2':_ -> do
            putStrLn "Enter priority to find:"
            priority <- getLine
            putStrLn $ show $ getValue queue $ read priority
            doLoop queue
        '3':_ -> do
            putStrLn $ show $ getMax queue
            doLoop queue
        '4':_ -> do 
            putStrLn "Current queue: "
            printQueue queue
            doLoop queue
        '5':_ -> do
            putStrLn "0 - exit"
            putStrLn "1 - add value to priorityQueue"
            putStrLn "2 - find value with some priority"
            putStrLn "3 - find value with max priority"
            putStrLn "4 - printQueue"
            putStrLn "5 - see help menu"
            doLoop queue
        _ -> do
            putStrLn "Incorrect command!"
            doLoop queue

addElement:: [PriorityQueueElement] -> String -> Integer -> [PriorityQueueElement]
addElement [] a priority = [PriorityQueueElement a priority]
addElement (current@(PriorityQueueElement a priority):xs) value newPriority = 
    if newPriority > priority 
        then PriorityQueueElement value newPriority :current:xs
        else current : addElement xs value newPriority

getValue:: [PriorityQueueElement] -> Integer -> Maybe String
getValue [] _ = Nothing
getValue ((PriorityQueueElement a priority):xs) toFind 
    | toFind > priority = Nothing
    | toFind == priority = Just a
    | otherwise = getValue xs toFind

getMax:: [PriorityQueueElement] -> Maybe String
getMax [] = Nothing
getMax ((PriorityQueueElement a priority):xs) = Just a

printQueue:: [PriorityQueueElement] -> IO ()
printQueue [] = do
    putStrLn " "
printQueue ((PriorityQueueElement a priority):xs) = do
    putStr $ show $ a
    putStr " "
    printQueue xs