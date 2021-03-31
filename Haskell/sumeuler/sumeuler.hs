module Main where

import Control.Parallel.Strategies

hcf :: Int -> Int -> Int
hcf x 0 = x
hcf x y =  hcf y $ x `rem` y

relprime :: Int -> Int -> Bool
relprime x y = hcf x y == 1

eulerSum :: Int -> Int
eulerSum n = length $ filter relPrime [1..n]
  where
    relPrime :: Int -> Bool
    relPrime = relprime n

sumTotient :: (Int, Int) -> Int
sumTotient (lower, upper) = sum $ map eulerSum [lower..upper]

divConq :: (a -> b) ->
            a ->
           (a -> Bool) ->
           (b -> b -> b) ->
           (a -> Maybe (a, a)) ->
            b
divConq f arg threshold conquer divide = go arg
  where
    go arg = case divide arg of
      Nothing      -> f arg
      Just (l0,r0) -> conquer l1 r1 `using` strat where
        l1 = go l0
        r1 = go r0
        strat x = do r l1; r r1; return x
          where r | threshold arg = rseq
                  | otherwise     = rpar

range :: (Int, Int)
range = (0, 5000)

chunk :: Int
chunk = 250

divide_range :: (Int, Int) -> Maybe ((Int, Int), (Int, Int))
divide_range (n1, n2)
  | n2 - n1 > chunk  = Just ((n1, mid), (mid+1, n2))    
  | otherwise        = Nothing
  where
      mid = (n1+n2) `div` 2

main :: IO()
main = print $ divConq sumTotient range (\(x1,x2) -> (x2-x1) <= chunk) (+) divide_range
