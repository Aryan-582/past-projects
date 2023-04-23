use std::{
    borrow::BorrowMut,
    ops::{Deref, DerefMut},
    sync::{Arc, RwLock},
};

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum Component {
    Helmet(bool),              //is damaged?
    LeftThrusters(bool, i32),  //is damaged? How much power left?
    RightThrusters(bool, i32), //is damaged? How much power left?
    LeftRepulsor(bool, i32),   //is damaged? How much power left?
    RightRepulsor(bool, i32),  //is damaged? How much power left?
    ChestPiece(bool, i32),     //is damaged? How much power left?
    Missiles(i32),             //how many missiles left?
    ArcReactor(i32),           // How much power left?
    Wifi(bool),                // connected to wifi?
}

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Armor {
    pub component: Component,
    pub version: i32,
}

// Part 2

// Students should fill in the Link type themselves. The Node and List types are given as is.
type Link = Option<Arc<RwLock<Node>>>;

struct Node {
    data: Armor,
    rest: Link,
}

#[derive(Clone)]
pub struct List {
    head_link: Link,
    size: usize,
}

impl List {
    pub fn new() -> Self {
        let new_list = List {
            head_link: None,
            size: 0,
        };
        return new_list;
    }

    pub fn size(&self) -> usize {
        self.size
    }

    pub fn peek(&self) -> Option<Armor> {
        if self.size == 0 {
            return None;
        } else {
            let placeholder = (match &self.head_link {
                Some(x) => Arc::clone(&x),
                None => Arc::new(RwLock::new(Node {
                    data: Armor {
                        component: Component::Wifi(true),
                        version: 1,
                    },
                    rest: None,
                }))
            });
            return Some(placeholder.read().unwrap().data);
        }

    }

    pub fn push(&mut self, component: Armor) {
        
        if self.size == 0 {
            self.head_link = Some(Arc::new(RwLock::new(Node {
                data: component,
                rest: None,
            })));
            self.size += 1
        } else {
            let placeholder = (match &self.head_link {
                Some(x) => Arc::clone(&x),
                _ => Arc::new(RwLock::new(Node {
                    data: component,
                    rest: None,
                }))
            });
            self.head_link = Some(Arc::new(RwLock::new(Node {
                data: Armor {
                    component: Component::Wifi(true),
                    version: 1,
                },
                rest: Some(placeholder),
            })));
            self.size += 1
        }
    }

    pub fn pop(&mut self) -> Option<Armor> {
        if self.size == 0 {
            return None;
        }
        let placeholder = (match &self.head_link {
            Some(x) => Arc::clone(&x),
            None => Arc::new(RwLock::new(Node {
                data: Armor {
                    component: Component::Wifi(true),
                    version: 1,
                },
                rest: None,
            }))
        });
        self.head_link = placeholder.read().unwrap().rest.clone();
        self.size -= 1;
        return Some(placeholder.read().unwrap().data);
    }
}

// Part 3

#[derive(Clone)]
pub struct Suit {
    pub armor: List,
    pub version: i32,
}

impl Suit {
    pub fn is_compatible(&self) -> bool {
        for i in 0..self.armor.size {
            let ver = match self.armor.peek() {
                Some(x) => x.version,
                None => 0
            };
            if ver != self.version {
                return false;
            }
        }
        return true;
    }

    pub fn repair(&mut self) {
        unimplemented!()
    }
}
