import System.IO
import Control.Monad
import Data.List
import Data.Maybe

data Graph = Graph [Int] [((Int,Int),Double)] deriving (Show)
data Path = Path {
    from :: Int,
    to :: Int,
    dist :: Double
} deriving (Eq)

instance Ord Path where
    compare p1 p2 = dist p1 `compare` dist p2

instance Show Path where
    show path = show (to path, dist path)

graph1 = Graph [1..5] [((1, 2), 10), ((1, 3), 7), ((3, 4), 4), ((2, 3), 8), ((3, 5), 6), ((1, 5), 23)]
graph2 = Graph [1..3] [((1, 2), 1), ((1, 3), 3), ((2, 3), 2)]

main = do
    hSetBuffering stdin LineBuffering
    putStrLn "graph1, start point = 1:"
    print $ dijkstra graph1 1
    putStrLn "graph2, start point = 1:"
    print $ dijkstra graph2 1
    
    
dijkstra:: Graph -> Int -> [Path]
dijkstra (Graph vertexes paths) src =  dijkstra' (map (\vertex -> Path vertex vertex (if vertex == src then 0.0 else 1 / 0)) vertexes) [] where
    dijkstra' [] visited = visited
    dijkstra' notVisited visited = dijkstra' closestNotVis updatedVisited where
        closest = minimum notVisited
        updatedVisited = closest : visited
        closestNotVis = (map (\p -> min p (getNewPath closest p)) . delete closest) notVisited
        getNewPath (Path closest _ nearestDist) (Path currNode _ _) = Path currNode closest (nearestDist + distBetweenNodes closest currNode) 
        distBetweenNodes x y = fromJust $ Prelude.lookup (x, y) paths `mplus` Just (1 / 0)

    