module Main where

hcf :: Integral a => a -> a -> a
hcf x 0 = x
hcf x y =  hcf y $ x `rem` y

relprime :: Integral a => a -> a -> Bool
relprime x y = hcf x y == 1

eulerSum :: Integral a => a -> Int
eulerSum n = length $ filter (\x -> relprime n x) [1..n]

sumTotient :: Integral a => a -> a -> Int
sumTotient lower upper = foldl (+) 0 $ map eulerSum [lower..upper]

