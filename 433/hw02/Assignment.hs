{-# LANGUAGE InstanceSigs #-}
module Assignment
 ( studentName
 , assignment2
 , Thing(..)
 , getThing
 , mkThing
 , thingToList
 , NonEmpty(..)
 , nonEmptyToList
 , listToNonEmpty
 , singletonNE
 , userTokens
 , why
 , Lock(..)
 , newLock
 , obtainLock
 , liberateLock
 , withLock
 ) where

import Control.Concurrent

import Test.HUnit
    ( assertBool,
      (~:),
      (~?=),
      assertFailure,
      runTestTT,
      Test(TestList),
      Assertion )



-- | Don't redefine this... That won't help you in the long run.
todo = error "It is your job to fill in all `todo`s"

studentName :: String
studentName = "Aryan Patel, 4/3/23, Homework 2"

testName :: Test
testName = "testName" ~:
   assertBool "You need to provide a `studentName`" (not (null studentName))

{-
The main "entry point" for this assignment runs the tests for each
homework problem below.

-}

assignment2 :: IO ()
assignment2 = do
  runTestTT testName
  runTestTT testThing
  runTestTT testThingMonad
  runTestTT testNEBasic
  runTestTT testNEMonad
  return ()

--------------------------------------------------------------------------------
-- Problem (The most basic generic type)
-------------------------------------------------------------------------------- 

-- The following type may not seem immediately useful, but it will allow us to
-- stretch our legs (aside: It is actually useful! But that's outside the scope
-- of this problem)

data Thing t = T t
  deriving (Show, Eq)

-- Part 1 --------------

-- Define the following functions for `Thing`. While you cannot change the type
-- signatures, you can change the function definitions (e.g. you can add/remove
-- parameters as you see fit, as long as the type signature remains the same).

getThing :: Thing a -> a
getThing (T x) = x

testGetThing :: Test
testGetThing =
   "tGetThing" ~:
    TestList [getThing (T 'x')   ~?= 'x',
              getThing (T 1)     ~?= 1,
              getThing (T "abc") ~?= "abc"]

mkThing :: z -> Thing z
mkThing x = (T x)

testMkThing :: Test
testMkThing =
   "tMkThing" ~:
   TestList [mkThing 'x'   ~?= T 'x',
             mkThing 1     ~?= T 1,
             mkThing "abc" ~?= T "abc"]

thingToList :: Thing a -> [a]
thingToList (T x) = [x]

testThingToList :: Test
testThingToList =
   "tThingToList" ~:
   TestList [thingToList (T 'x')   ~?= "x",
             thingToList (T 1)     ~?= [1],
             thingToList (T "abc") ~?= ["abc"]]

-- Part 2 ------------

-- write at least two unit tests for each of the above functions (i.e. a
-- minimum of 6 tests total)


-- Part 3 ------------

-- Write the Functor instance for `Thing`

instance Functor Thing where
  fmap f (T a) = T (f a)

-- Testing the `Functor` instance for thing shouldn't require too many tests.
--
-- One test you'll want to do is that using `fmap f (T x)` should be the same
-- as `mkThing (f x)`
--
-- There's also a similar sort of test that would use `getThing`, you'll need
-- to write that test as well.
testThingFmap :: Test
testThingFmap =
   "tThingFmap" ~:
   TestList [fmap id (T 'x') ~?= T 'x',
             fmap id (T 'x') ~?= mkThing (id 'x')]

-- Write the `Monad` instance for `Thing`

instance Monad Thing where

  return :: a -> Thing a
  return a = T a
  (>>=) :: Thing a -> (a -> Thing b) -> Thing b
  (>>=) (T a) k = k a

testThingMonad :: Test
testThingMonad =
   "testThingMonad" ~:
    TestList [(>>=) (T 'a') mkThing      ~?= T 'a',
              (>>=) (return 'a') mkThing ~?= mkThing 'a',
              (>>=) (T 'a') return       ~?= T 'a']


testThing :: Test
testThing = "testThing" ~: TestList
  [testGetThing, testMkThing, testThingToList, testThingFmap, testThingMonad]

--------------------------------------------------------------------------------
-- Problem (Non-empty Lists)
-------------------------------------------------------------------------------- 

-- Part 0: Encoding important properties in our datatypes ----------------------

-- Lists are a useful datastructure, but sometimes we want to write functions
-- that only make sense if the list is non-empty. The consequence of this is
-- we get partial functions, but we don't like partial functions!
--
-- One way around this is to _encode_ the property we can about in the datatype
-- itself: If certain functionality only makes sense for non-empty lists, let's
-- make a datatype representing non-empty lists and write our code using that
-- datatype. Now we never even have to consider the case when it's empty,
-- because it can't be!

data NonEmpty a = NE a [a]
  deriving (Show, Eq)

-- We can easily convert a `NonEmpty` into a list safely:
nonEmptyToList :: NonEmpty a -> [a]
nonEmptyToList (NE x xs) = x:xs

-- We _cannot_ convert any possible list into a `NonEmpty` safely :(
--
-- Because of this, we encode the result as a `Maybe`
listToNonEmpty :: [a] -> Maybe (NonEmpty a)
listToNonEmpty []     = Nothing
listToNonEmpty (x:xs) = Just (NE x xs)

-- Part 1: Basic functions for `NonEmpty` --------------------------------------

-- `head` was unsafe on lists, but it's safe for `NonEmpty`!
headNonEmpty :: NonEmpty a -> a
headNonEmpty (NE x xs) = x

testNEHead :: Test
testNEHead =
   "testNEHead" ~: (headNonEmpty (NE (42 :: Int) [1,2,3]) ~?= (42 :: Int))

-- Create a `NonEmpty` from an initial value
singletonNE :: a -> NonEmpty a
singletonNE x = NE x []

testNESingle :: Test
testNESingle =
   "testNESingle" ~: (singletonNE (42 :: Int) ~?= (NE (42 :: Int) []))

-- There's a function in the standard library called `words` with the following
-- type:
--
-- words :: String -> [String]
--
-- The function breaks the string into a list of all the non-whitespace groups
-- of characters, for example:
--
-- words ("this is a                test") == ["this", "is", "a", "test"]
--
-- this function is really useful when doing things like parsing command-line
-- arguments or getting input from the user when you want the tokens but not
-- the whitespace

-- Write a function that gets input from the user, and creates a `NonEmpty`
-- when there are any 'words'
userTokens :: IO (Maybe (NonEmpty String))
userTokens = do
  line <- getLine
  let list = listToNonEmpty (words line)
  return list




-- We don't have you writing tests for this Part 1, the first two functions 
-- are simple enough that the provided tests are 'good enough'. The third
-- function `userTokens` is harder to test via a unit test.
--
-- Why?

-- Answer as the following string:
why :: String
why = "There are so many possible user inputs that it would be impossible to" ++
      " test efficiently enough to be confident in the function's" ++
      " functionality with just unit tests"

testNEWhy :: Test
testNEWhy = "testNEWhy" ~:
   assertBool "You need to provide an reason for why unit tests are hard for `userTokens`" (not (null why))

testNEBasic :: Test
testNEBasic = "testNEBasic" ~: TestList
  [testNEHead, testNESingle, testNEWhy]


-- Part 2: Class instances for `NonEmpty` --------------------------------------

-- Implement and test the following Class instances for `NonEmpty`

instance Functor NonEmpty where
  fmap :: (a -> b) -> NonEmpty a -> NonEmpty b
  fmap f (NE x xs) = NE (f x) (fmap f xs)

testNEFunctor :: Test
testNEFunctor = "testNEFunctor" ~:
  TestList [fmap (+1) (NE 1 [2,3,4])                    ~?= NE 2 [3,4,5],
            fmap (++ "hi") (NE "aryan" ["see", "seep"]) ~?=
              NE "aryanhi" ["seehi","seephi"]]

instance Monad NonEmpty where
  return x = NE x []
  (>>=) (NE x xs) k = let (NE y ys) = k x
    in NE y (ys ++ concatMap (\x -> nonEmptyToList (k x)) xs)


-- When thinking about what to test for `Monad` remember the Monad Laws :D

testNEMonad :: Test
testNEMonad = "testNEMonad" ~:
  TestList[(return 1 >>= (\x -> (NE (x) []))) ~?= NE 1 [],
           (NE 1 [] >>= return)               ~?= NE 1 [],
           ((NE "a" [] >>= (\x -> (NE (x) []))) >>= (\x -> (NE (length x) []))) 
            ~?= NE 1 []]

testNEInstances :: Test
testNEInstances = "testNEInstances" ~: TestList
  [testNEFunctor, testNEMonad]

--------------------------------------------------------------------------------
-- Problem (Locks)
-------------------------------------------------------------------------------- 


-- Part 1: The Basic Lock API --------------------------------------------------

-- MVars are useful _building blocks_ for APIs that leverage concurrency.
--
-- One such useful API is that for 'locks' which we only discussed informally.
--
-- Let's make it formal!
--
-- A usefule API for locks would Look like the following:
--
-- data Lock
-- newLock      :: IO Lock
-- obtainLock   :: Lock -> IO ()
-- liberateLock :: Lock -> IO ()
--
-- We're going to provide the datatype declaration for `Lock`, but you'll
-- have to implement the rest of the API.
--
-- A lock has two possible states:
--
-- * locked
-- * unlocked
--
-- When in the 'locked' state, any thread that calls `obtainLock` will _block_
-- until some other thread calls `liberateLock`. Once `liberateLock` is called
-- in another thread, one of the threads the is blocked on `obtainLock` will be
-- unblocked, the unblocked thread will obtain the Lock.
--
-- When in an 'unlocked' state, a thread that calls `obtainLock` will
-- successfully obtain the lock, which results in the lock being put in the
-- 'locked' state.

data Lock = L (MVar ()) -- a Lock is just an `MVar` that doesn't hold any
                        -- information itself

-- `newLock` should create a lock in the 'unlocked' state.
newLock :: IO Lock
newLock = do
  mv <- newEmptyMVar
  putMVar mv ()
  return (L mv)

-- `obtainLock` should _either_ block or should result in the lock being put
-- in the 'locked' state (hint: You can-and-should lean on the behavior `MVar`
-- provides)
obtainLock :: Lock -> IO ()
obtainLock (L mv) = takeMVar mv


-- `liberateLock` should _unlock_ the given Lock. The expecation is that the thread
-- that locked the lock is also the thread the calls `liberateLock`, but this is
-- _not_ enforced by the implementation of `liberateLock`.
liberateLock :: Lock -> IO ()
liberateLock (L mv) = putMVar mv ()


-- Part 2: A useful addition to the API ---------------------------------------

-- The issue with the Lock API is basically described in the behavior of
-- `liberateLock`: Well-behaved usage of `Lock` requires careful thought by the
-- _user_ of the API! But using Concurrency APIs can be tricky! Let's think
-- about how we can help the user of our API.
--
--
-- There's a common 'shape' of how someone might want to use our `Lock` API:
--
-- * obtain the lock
-- * do something that requires having the lock
-- * liberate the lock
--
-- The problem is that our API has no way of _enforcing_ this shape, so let's
-- add something to our API that does!

-- `withLock` can enforce the shape of the basic usage above, the idea is that
-- we tell `withLock` what we'd like to do with the lock, and it ensures that
-- it obtains and liberates the lock appropriately
withLock :: Lock -> IO a -> IO a
withLock (L mv) doSomething = do
  obtainLock (L mv)
  output <- doSomething 
  liberateLock (L mv)
  return output



--------------------------------------------------------------------------------
-- Ignore everything below this line
-------------------------------------------------------------------------------- 










































































































--------------------------------------------------------------------------------
-- Seriously, just ignore it....
-------------------------------------------------------------------------------- 








































































--------------------------------------------------------------------------------
-- Why? Why are you still looking?
-------------------------------------------------------------------------------- 








































































instance Applicative Thing where
  pure  = undefined
  (<*>) = undefined

instance Applicative NonEmpty where
  pure  = undefined
  (<*>) = undefined
