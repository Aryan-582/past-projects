Assignment #2 for CMSC433

Introduction
============

The goal of this assignment is to reinforce your practice and intuitions
of basic Haskell Typeclasses and Concurrency primitives.

Main.hs is a "literate" Haskell program, meaning that explanation is
interspersed with actual Haskell code. To complete your assignment, edit
Main.hs and submit it through Gradescope.


Recommended Workflow
====================

Everyone has their own workflow when programming. This course does not
enforce a particular coding workflow. With that said, the following are
some tried-and-true approaches to working with Haskell code. If you'd
like to add to this list, please email jmct@umd.edu.

REPL-first development
----------------------

You can ensure that all the necessary libraries are installed, and launch a repl
with those libraries by running `cabal repl`, as follows

```{shell}
$ cabal repl .
```

This will install the libraries as specified in `hw02.cabal` and launch the repl.
At the interactive prompt, you can check the types of various functions with `:t`,
or edit `Main.hs` with `:e`.

If at any point you want to execute the `main` function, you can use `:main`

IDE-first development
---------------------

If you have an IDE of choice you can load the files you'd like to edit
(`Main.hs`, in this case), in that IDE, and have a repl open in a terminal
window.

When you save changes to your file, you'll need to 'reload' the file in you
repl with `:r`

Compiling the executable
------------------------

Regardless of how you develop your code, you may want to actually compile
your solution into an executable. You can do this with the `cabal` command:

```{shell}
$ cabal build
```

The above will build the executable and link all of the necessary libraries.

To run the executable you can run `cabal run`.
