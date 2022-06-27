# Airport-Simulation---Java


Asia Pacific Airport
You have been tasked to automate the task of Air Traffic Controller (ATC). ATCs have three 
main tasks as an aircraft approaches an airport, all of which must be carried out as quickly as 
possible:
*******************************Basic Requirements******************************
• There is only 1 runway for all planes to land and depart.
• Ensure that the aircraft does not collide with another aircraft on the runway or gates
• Once an aircraft obtains permission to land, it should land on the runway, coast to the 
assigned gate, dock to the gate, allow passengers to disembark, refill supplies and fuel, 
receive new passengers, undock, coast to the assigned runway and take-off.
• Each step should take some time.
• A congested scenario should be simulated where planes are waiting to land while the 2
gates are occupied.
• As the airport is small, there is no waiting area on the ground for the planes to wait for a 
gate to become available.
Concurrent Programming - CT074-3-2 Asia Pacific University of Technology & Innovation Level 2
Page 3 of 5
*****************************Additional Requirements****************************
These events should happen concurrently:
- Passengers disembarking/embarking
- Refill supplies and cleaning of aircraft
As there is only 1 refuelling truck, this event should happen exclusively:
- Refuelling of aircraft
