{-
---
fulltitle: Functors
date: February 28, 2023
---
-}
{-# LANGUAGE InstanceSigs #-}

module Functor where

import qualified Data.List as List
import Test.HUnit (Test (TestList), runTestTT, (~:), (~?=))


{-
Let's remind ourselves of the Functor type class:

Functor
=======

    class Functor f where
        fmap :: (a -> b) -> f a -> f b


`Functor` only has one required method: `fmap`, which should implement
a structure-preserving map over the type of its instance.

The instance for lists is just `map` itself:

    instance Functor [] where
        fmap :: (a -> b) -> [a] -> [b]
        fmap = map

We can define a `Functor` instance for our own trees:
-}

data Tree a = Empty | Branch a (Tree a) (Tree a)
 deriving (Eq, Show)

instance Functor Tree where
  -- I like to re-write the for the methods for the instance we're defining:
  --
  -- fmap :: (a -> b) -> Tree a -> Tree b
  fmap = error "TODO: Functor instance for Tree"

tree1, tree2, tree3 :: Tree Int
tree1 = Branch 3 (Branch 2 Empty Empty) (Branch 1 Empty Empty)
tree2 = Branch 3 Empty Empty
tree3 = Branch 4 Empty Empty

testTreeFMap :: Test
testTreeFMap =
  TestList
    [ "identity tree1"           ~: (fmap id tree1)   == tree1 ~?= True,
      "fmap (+1) tree2 == tree3" ~: (fmap (+1) tree2) == tree3 ~?= True,
      "fmap Empty"               ~: (fmap (+1) (Empty :: Tree Int)) == (Empty :: Tree Int) ~?= True
    ]


{-
 - Trey defining `Functor` instances for the following types
 -}

data Status a = Freshman a
              | Sophomore a
              | Junior a
              | Senior a
              | Graduated
  deriving (Eq, Show, Read, Ord)

instance Functor Status where
  fmap f (Freshman x) = Freshman (f x)
  fmap f (Sophomore x) = Sophomore (f x)
  fmap f (Junior x) = Junior (f x)
  fmap f (Senior x) = Senior (f x)
  fmap f Graduated = Graduated

data Result a = Success a
              | Failure String
  deriving (Show, Eq)

instance Functor Result where
  fmap :: (a -> b) -> Result a -> Result b
  fmap f (Success x) = Success (f x)
  fmap f (Failure x) = Failure x

{- This next one is a little trickier, but you'll save yourself some stress if
 - you write out the type of fmap for this instance
 -}

data Sum e z = L e
             | R z
 deriving (Show, Eq)

instance Functor Sum where 
    fmap :: (e -> f) -> Sum e -> Sum f 
    fmap = error ""



main :: IO ()
main = do
  putStrLn "Now running tests."
  _ <- runTestTT testTreeFMap
  return ()