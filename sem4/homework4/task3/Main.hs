data Tree a = Empty | Leaf a | Branch (Tree a) a (Tree a)

instance Foldable Tree where
    foldr f acc Empty = acc
    foldr f acc (Leaf a) = f a acc
    foldr f acc (Branch left value right) = foldr f (f value (foldr f acc right)) left

treeAsList:: Tree a -> [a]
treeAsList = foldr (:) []

tree1 = Leaf 5
tree2 = Branch (Branch (Branch (Leaf 0) 1 (Leaf 2)) 3 (Branch (Leaf 4) 5 (Leaf 6)))
    7 (Branch (Branch (Leaf 8) 9 (Leaf 10)) 11 (Branch (Leaf 12) 13 (Leaf 14)))