extern crate stark_suit_repair;
use std::collections::HashMap;

use stark_suit_repair::basics::{
    factorize, gauss, in_range, longest_sequence, mean, rotate, subset, substr, to_decimal,
};
use stark_suit_repair::communicator::{to_command, Command};

// adding linked list tests
use stark_suit_repair::linkedlist::{Armor, Component, List, Suit};

/*
 * Create a new function for each test that you want to run.  Please be sure to add
 * the #[test] attribute to each of your student tests to ensure they are all run, and
 * prefix them all with 'student_' (see example below).
 * Then, run `cargo test student` to run all of the student tests.
 */

#[test]
fn student_example() {
    assert_eq!(true, true);
}

#[test]
fn student_gauss() {
    assert_eq!(190, gauss(19)); //prime
    assert_eq!(1, gauss(1));
    assert_eq!(54615, gauss(330)); //composite
    assert_eq!(-1, gauss(-400));
}