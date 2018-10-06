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
* To add a new simulation
    * Create a new Cell class and @override or implement the new methods necessary
    * Create a new  Grid class and @override or implement the new methods necessary
    * Create a new XML file with the proper tags
    * Add conditions to the XML parsing methods in UISetup to check for the new simulation
    
* To add a new grid shape or edge case
    * Alter the conditions in the makeGrid method in the simGrid Class
        * For a new shape, create a new method that will create the polygon, and determine how to loop through the List
        myCells
        * Also alter the inBounds and getNeighbors methods to account for different geometry
    * For edge cases change all instances of getNearCells to getNearToroidal Cells

* To add extra XML tags/parameters
    * Create the new XML tag 
    * Alter the XML files in question
    * Add conditions to the XML parsing method call in UISetup to check for the new tag

* To add new elements to the GUI
    * Decide which area of the BorderPane to add the new element to
    * Create a new method in UISetup, or possibly a new class, that handles the initialization of the new UI element as
    well as its functionality

---

### Justification of Major Design Choices
* 

----

### Assumptions or Dependencies Made
* 

