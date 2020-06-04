import System.IO
import Control.Monad
import Data.Map
import Data.Function(on)
import Data.List(sortBy)

data Graph v = Graph [(Int,v)] [(Int,Int,Int)] deriving (Show)

graph1 = Graph [(1, 1), (2, 2), (3, 3), (4, 4), (5, 5)] [(1, 2, 10), (1, 3, 7), (3, 4, 4), (2, 3, 8), (3, 5, 6), (1, 5, 23)]
graph2 = Graph [(1, 1), (2, 2), (3, 3)] [(1, 2, 1), (1, 3, 3), (2, 3, 2)]

main = do
    hSetBuffering stdin LineBuffering
    putStrLn "graph1, start point = 1:"
    putStrLn "vertex, minLenght: path"
    printResults $ dijkstra graph1 1
    putStrLn "graph2, start point = 1:"
    putStrLn "vertex, minLenght: path"
    printResults $ dijkstra graph2 1
    

dijkstra:: Graph v -> Int -> [(Int, (Int, [Int]))]
dijkstra (Graph vertexes paths) src = toList $ updateVisited (Prelude.map fst vertexes) paths $ insert src (0, [src]) empty

printResults:: [(Int, (Int, [Int]))] -> IO ()
printResults [] = return ()
printResults ((x,(length,path)):xs) = do 
    putStr $ show x ++ ", " ++ show length ++ ": "
    putStrLn $ Prelude.foldr (\x -> (++) (show x ++ " ")) "" path
    printResults xs

updateVisited:: [Int] -> [(Int, Int, Int)] -> Map Int (Int, [Int]) -> Map Int (Int, [Int])
updateVisited [] _ map = map
updateVisited notVisited@(x:_) paths map = updateVisited updatedNotVisited paths (updateWeights updatedNotVisited vertex paths map) where
    orderedVertexes = sortBy (compare `on` fst . snd) $ toList map
    filteredVertexes = Prelude.filter (`elem` notVisited) $ Prelude.map fst orderedVertexes
    vertex = if Prelude.null filteredVertexes then x else head filteredVertexes
    updatedNotVisited = Prelude.filter (/=vertex) notVisited

updateWeights:: [Int] -> Int -> [(Int, Int, Int)] -> Map Int (Int, [Int]) -> Map Int (Int, [Int])
updateWeights _ _ [] vertexMap = vertexMap
updateWeights notVisited src ((x,y,pathLength):xs) vertexMap
    | src == x && elem y notVisited = updateWeight notVisited src ((x,y,pathLength):xs) vertexMap
    | otherwise = updateWeights notVisited src xs vertexMap

updateWeight:: [Int] -> Int -> [(Int, Int, Int)] -> Map Int (Int, [Int]) -> Map Int (Int, [Int])
updateWeight notVisited src ((x,y,pathLength):xs) vertexMap = case (Data.Map.lookup x vertexMap, Data.Map.lookup y vertexMap) of
    (Just (len, path), Just (minLength, _)) | len + pathLength < minLength -> updateWeights notVisited src xs $ insert y (len + pathLength, path ++[y]) vertexMap
    (Just (len,path), Nothing) -> updateWeights notVisited src xs $ Data.Map.insert y (len + pathLength, path ++[y]) vertexMap
    _ -> updateWeights notVisited src xs vertexMap