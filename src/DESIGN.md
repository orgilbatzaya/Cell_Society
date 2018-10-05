Cell Society Design
====

This project implements a cellular automata simulator.

Name: Brooke Keene (bzk2)

Team Members: Amy Kim (yk154), Orgil Batzaya (ob29)

---

### High Level Design Overview
The high-level design goal of our project was to design a GUI that would allow a user to interact with the animation
of a cell automata. 

To accomplish this, we created an abstract Cell class to encapsulate the behavior and position of individual cells and
an abstract Grid class to encapsulate all the cells in a grid, the simulation rules, and boundary cases. The configuration
of each simulation is then specified by an XML file chosen by the user and parsed using our XMLParser. The GUI finally 
displays the animation of the cell automata, checks, and updates the states of the cells. The GUI also controls the 
simulation speed and specifies when it starts, stops, or makes a step forward. All of this user input is then fed back 
to the back-end Simulation class which reacts to the event of the buttons being pressed.   

---

### How to Add New Features


---

### Justification of Major Design Choices


----

### Assumptions or Dependencies Made


