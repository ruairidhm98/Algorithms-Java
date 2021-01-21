module Main where

quicksort :: Ord a => [a] -> [a]
quicksort [] = []
quicksort (n:ns) = quicksort left ++ [n] ++ quicksort right
  where
    left = [x | x <- ns, x < n]
    right = [x | x <- ns, x >= n]

main :: IO ()
main = print $ quicksort [2, 3, 4, 5, 6]