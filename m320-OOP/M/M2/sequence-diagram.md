# Sequence diagram #

```mermaid

sequenceDiagram
    participant Main
    participant Seed
    participant Flight
    participant Passenger
    participant Util
    
    Main->>Seed: seedFlight()
    Seed-->>Main: Flight with pre-seeded passengers
    
    Main->>Util: read() for firstName
    Util-->>Main: firstName
    
    Main->>Util: read() for lastName
    Util-->>Main: lastName
    
    Main->>Passenger: new Passenger(name)
    Passenger-->>Main: passenger instance
    
    Main->>Flight: getPassengers()
    Flight-->>Main: passenger list
    
    Main->>Flight: add(you)
    Main->>Flight: setPassengers(updatedList)
    
    Main->>Flight: printPassengers()
    Flight->>Passenger: printName()
    
    alt Start Flight
        Main->>Util: read() for choice
        alt choice is 's'
            Main->>Util: current time formatting
            Main->>Main: print flight start message
        else choice is 'r'
            Main->>Flight: getPassengers()
            Main->>Flight: remove(you)
            Main->>Flight: setPassengers(updatedList)
            Main->>Flight: printPassengers()
        end
    end
    
    Main->>Main: print "Goodbye!"
    
```

## Typische Use Case ##

Adding a New Passenger to a Flight.
A user wants to join a pre-seeded flight by entering their first and last name. The program adds them to the passenger list and allows them to start the flight.

## Spezieller Use Case ##

Removing a Passenger from the Flight. 
A user decides they do not want to be on the flight and chooses to remove themselves from the passenger list before the flight starts.
