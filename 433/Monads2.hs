-- class Monad m where
--   return :: a -> m a
--   (>>=) :: m a -> (a -> m b) -> m b

-- Monad Laws
--
-- Left Identity:
--
--     return a >>= k   === k a
--
-- Right Identity:
--
--     m >>= return     === m
--
-- Associativity
--
--     (m >>= g) >>= h  === m >>= (\x -> g x >>= h)



data CouldFail v = Failed
                 | Worked v
  deriving (Show, Eq, Ord)


safeDiv :: Int -> Int -> CouldFail Int
safeDiv _ 0 = Failed
safeDiv x y = Worked (x `div` y)


instance Functor CouldFail where
  fmap f Failed     = Failed
  fmap f (Worked x) = Worked (f x)


instance Monad CouldFail where
  return x = Worked x
  Failed >>= _     = Failed
  (Worked v) >>= k = k v

-- return :: a -> m a
-- bind   :: m a -> (a -> m b) -> m b

f :: Int -> CouldFail Bool
f x = if x `mod` 2 == 0
      then Failed
      else Worked True

calc :: Int -> CouldFail Bool
calc i = do
 n <- safeDiv i 2
 f n

instance Functor (Except e) where
  -- fmap :: (a -> b) -> Except e a -> Except e b
  fmap f something =
    case something of
      Exception z -> Exception z
      Success v   -> Success (f v)

data Except e s = Exception e
                | Success s
  deriving (Eq, Show, Ord)

instance Monad (Except e) where
  -- return :: a -> Except e a
  -- bind :: Except e a -> (a -> Except e b) -> Except e b
  return x = Success x
  (>>=) (Exception e) _ = Exception e
  (>>=) (Success x) f   = f x


data MyList a = End
              | Cons a (MyList a)
  deriving (Eq, Show, Ord)

instance Functor MyList where
  fmap f End        = End
  fmap f (Cons x y) = Cons (f x) (fmap f y)

instance Monad MyList where
  return x = Cons x End
  (>>=) End _        = End
  (>>=) (Cons x y) f = case fmap f (Cons x y) of 
                        End -> End
                        Cons x y -> x



-- Ignore everything below this

instance Applicative (Except e) where
  pure x = undefined
  (<*>) = undefined

instance Applicative CouldFail where
  pure x = Worked x
  (<*>) = undefined

instance Applicative MyList where
  pure x = undefined
  (<*>) = undefined