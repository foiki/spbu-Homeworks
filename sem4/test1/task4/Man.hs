import System.IO

data PriorityQueueElement = PriorityQueueElement {
    value:: String,
    priority:: Integer
} deriving (Show)

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop queue = do
    putStrLn "Enter a command or '4' to see help menu:"
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
            doLoop (addElement queue (read value) (read priority))
        '2':_ -> do
            putStrLn "Enter priority to find:"
            priority <- getLine
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

--print:: [PriorityQueueElement] -> IO ()
--print 