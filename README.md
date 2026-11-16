# Hospital Patient Queue and Appointment Management System

## Problem Statement (BTL-5 – Evaluate)
Design a hospital appointment management application to maintain patient details, appointment schedules, and consultation records. Use LinkedList to manage patient appointments and HashMap to retrieve patient information. Implement sorting based on appointment time and patient priority using Comparator. Use Iterator and ListIterator to traverse and update appointment records. Store patient appointment details using object serialization. Evaluate the suitability of different collection classes for appointment management. 

**Concepts Used:** LinkedList, HashMap, Iterator, ListIterator, Comparator, Serialization.

## Features
- **Patient Registration:** Register new patients with a unique ID.
- **Appointment Scheduling:** Schedule appointments and assign priorities (Emergency, High, Normal).
- **Search:** Fast patient lookup using `HashMap`.
- **Sorting:** View the queue sorted by time or by priority using custom `Comparator`s.
- **Update/Cancel:** Use `ListIterator` to update consultation notes and `Iterator` to safely remove cancelled appointments.
- **Data Persistence:** Saves all data to `hospital.dat` using Java Object Serialization, so data is not lost between runs.

## How to Run
1. Compile: `javac *.java`
2. Run: `java HospitalSystem`
