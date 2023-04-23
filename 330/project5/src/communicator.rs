#[derive(Debug)]
#[derive(PartialEq)]
pub enum Command
{
    Power(bool,i32),    // [Increase/Decrease] power by [number].
    Missiles(bool,i32), // [Increase/Decrease] missiles by [number].
    Shield(bool),       // Turn [On/Off] the shield.
    Try,                // Try calling pepper.
    Invalid             // [anything else]
}


/**
    Adds functionality to Command enums
    Commands can be converted to strings with the as_str method
    
    Command     |     String format
    ---------------------------------------------------------
    Power       |  /Power (increased|decreased) by [0-9]+%/
    Missiles    |  /Missiles (increased|decreased) by [0-9]+/
    Shield      |  /Shield turned (on|off)/
    Try         |  /Call attempt failed/
    Invalid     |  /Not a command/
**/
impl Command {
    pub fn as_str (&self) -> String {
        match self {
            &crate::communicator::Command::Power(x, y) => if x == true { format!("Power increased by {}%", y.to_string()) } else { format!("Power decreased by {}%", y.to_string()) },
            &crate::communicator::Command::Missiles(x, y) => if x == true { format!("Missiles increased by {}", y.to_string()) } else { format!("Missiles decreased by {}", y.to_string()) },
            &crate::communicator::Command::Shield(x) => if x == true {("Shield turned on").to_string()} else {("Shield turned off").to_string() },
            &crate::communicator::Command::Try => ("Call attempt failed").to_string(),
            &crate::communicator::Command::Invalid => ("Not a command").to_string()
        }    
    }
}

/**
    Complete this method that converts a string to a command 
    We list the format of the input strings below

    Command     |     String format
    ---------------------------------------------
    Power       |  /power (inc|dec) [0-9]+/
    Missiles    |  /(fire|add) [0-9]+ missiles/
    Shield      |  /shield (on|off)/
    Try         |  /try calling Miss Potts/
    Invalid     |  Anything else
**/
pub fn to_command(s: &str) -> Command {
    let mut string = s.split_whitespace();
    match (string.next()) {
        Some("power") => (match string.next() {
                            Some("inc") => if string.clone().count() == 1 {crate::communicator::Command::Power(true, string.next().unwrap().parse::<i32>().unwrap())} else {crate::communicator::Command::Invalid},
                            Some("dec") => if string.clone().count() == 1 {crate::communicator::Command::Power(false, string.next().unwrap().parse::<i32>().unwrap())} else {crate::communicator::Command::Invalid},
                            _ => crate::communicator::Command::Invalid
        }),
        Some("fire") => if string.clone().count() == 2 {crate::communicator::Command::Missiles(false, string.next().unwrap().parse::<i32>().unwrap())} else {crate::communicator::Command::Invalid}, 
        Some("add") => if string.clone().count() == 2 {crate::communicator::Command::Missiles(true, string.next().unwrap().parse::<i32>().unwrap())} else {crate::communicator::Command::Invalid},
        Some("shield") => (match string.next() {
                            Some("on") => if string.next() == None {crate::communicator::Command::Shield(true)} else {crate::communicator::Command::Invalid},
                            Some("off") => if string.next() == None {crate::communicator::Command::Shield(false)} else {crate::communicator::Command::Invalid},
                            _ => crate::communicator::Command::Invalid
        }),
        Some("try") => if s == "try calling Miss Potts" {crate::communicator::Command::Try} else {crate::communicator::Command::Invalid},
        _ => crate::communicator::Command::Invalid
    }
}
