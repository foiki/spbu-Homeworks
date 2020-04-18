data BST a = Empty | Leaf a | Branch (BST a) a (BST a)

instance Show a => Show (BST a) where
    show Empty = "Empty"
    show (Leaf a) = "Leaf " ++ show a
    show (Branch l a r) = "(Branch (" ++ show l ++ ") " ++ show a ++ " (" ++ show r ++ "))"

contains:: Ord a => a -> BST a -> Bool
contains element Empty = False
contains element (Leaf a) = element == a
contains element (Branch l a r) | element == a = True 
                                | element > a = contains element r
                                | otherwise = contains element l

add:: Ord a => a -> BST a -> BST a
add element Empty = Leaf element
add element (Leaf a) = if element > a then Branch Empty a (Leaf element) 
    else Branch (Leaf element) a Empty 
add element (Branch l a r) = if element > a then Branch l a (add element r)
    else Branch (add element l) a r

remove:: Ord a => a -> BST a -> BST a
remove element Empty = Empty
remove element (Leaf a) = if element == a then Empty else Leaf a
--remove element (Branch l a r) = 

size:: BST a -> Integer
size Empty = 0
size (Leaf _) = 1
size (Branch l _ r) = 1 + size l + size r

height:: BST a -> Integer
height Empty = 0
height (Leaf _) = 1
height (Branch l _ r) = 1 + max (height l) (height r)
