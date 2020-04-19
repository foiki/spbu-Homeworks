data BST a = Empty | Leaf a | Branch (BST a) a (BST a) deriving (Eq)

instance Show a => Show (BST a) where
    show Empty = "Empty"
    show (Leaf a) = "(Leaf " ++ show a ++ ")"
    show (Branch l a r) = "(Branch " ++ show l ++ " " ++ show a ++ " " ++ show r ++ ")"

contains:: Ord a => a -> BST a -> Bool
contains element Empty = False
contains element (Leaf a) = element == a
contains element (Branch l a r) | element == a = True 
                                | element > a = contains element r
                                | otherwise = contains element l

add:: Ord a => a -> BST a -> BST a
add element Empty = Leaf element
add element (Leaf a) | element == a = Leaf a
                     | element > a = Branch Empty a (Leaf element) 
                     | otherwise = Branch (Leaf element) a Empty 
add element (Branch l a r) | element > a = Branch l a (add element r)
                           | otherwise = Branch (add element l) a r

remove:: Ord a => a -> BST a -> BST a
remove element Empty = Empty
remove element (Leaf a) = if element == a then Empty else Leaf a
remove element (Branch l a r) | element > a = Branch l a (remove element r)
                              | element < a = Branch (remove element l) a r
                              | otherwise = helper (Branch l a r) where
                                  helper (Branch Empty _ r) = r
                                  helper (Branch l a r) = Branch l (findMin r) (remove (findMin r) r)

findMin:: Ord a => BST a -> a
findMin (Leaf a) = a
findMin (Branch Empty a _) = a
findMin (Branch l _ _) = findMin l

size:: BST a -> Integer
size Empty = 0
size (Leaf _) = 1
size (Branch l _ r) = 1 + size l + size r

height:: BST a -> Integer
height Empty = 0
height (Leaf _) = 1
height (Branch l _ r) = 1 + max (height l) (height r)

--randomize:: BST a -> BST a

removeTest1 = remove 1 (Branch (Leaf 1) 4 (Leaf 5)) == (Branch Empty 4 (Leaf 5))

removeTest2 = remove 4 (Branch (Leaf 1) 4 (Branch (Branch (Leaf 7) 8 (Leaf 9)) 10 (Branch (Leaf 11) 12 (Leaf 13))))
    == (Branch (Leaf 1) 7 (Branch (Branch Empty 8 (Leaf 9)) 10 (Branch (Leaf 11) 12 (Leaf 13))))

