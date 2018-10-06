Cell Society Design
====

This project implements a cellular automata simulator.

Team Members: Brooke Keene (bzk2), Amy Kim (yk154), Orgil Batzaya (ob29)

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
* Abstraction vs. Inheritance for Cell and Grid Classes
   * We began by using normal inheritance for our Cell and Grid classes, but for our second sprint we changed these 
   basic classes to be abstract. This is because abstraction allows for code flexibility while still fulfilling the
   open-closed principle. The benefit of using normal inheritance was that we were able to actually create simple 
   Cell and Grid objects that functioned on the most basic level necessary, but the flexibility we gained from making
   these classes abstract was better for our design in the end. Therefore, someone would not be able to change the
   basic functions of a Cell or Grid, but would simply need to extend their functionality depending on the simulation 
   they wanted to add. 
   
* Cell Class vs. Grid Class
    * We decided that our Cell class should encapsulate the properties of an individual cell of a grid, while our Grid 
    class should encapsulate the general behavior of the grid and the cells in it. 
        * For example, getNeighbors() was a method that was originally in our Cell class, but under further
        consideration, we realized that it would make more sense for getNeighbors() to be a method in our Grid class. 
        The getNeighbors() method is supposed to access information about more than one cell, so because of the scope of 
        the Grid class, it made more sense to us to have that functionality encapsulated by the Grid class and then passed
        to the Cell class. 
   
* UI and Animation were separated into Multiple Classes
    * When we first created our GUI, we were initializing and placing our UI elements all in a single class, UISetup. 
    We soon found that the code became very difficult for us to follow, however, and we began refactoring our GUI code
    so that the similar elements would be handled by a single class, as a way of encapsulating their functionality. This
    proved to be difficult and lead to more dependencies between classes, but I think having the UI elements initialized
    in separate classes makes setting up the UI more clear and able to be expanded in the future. 

* Had Separate XMLParser and XMLWriter Classes 
    * It was important to create separate classes for these tasks because they are both very different from each other 
    and require very different methods. Our XMLParser specifically includes methods that will parser through a Document
    and identify data from a String, whereas our XMLWriter class would have been used to save the current state of a grid
    to an XML file by writing a completely new XML file. 

----

### Assumptions or Dependencies Made
* Assumptions
    * Cells had a fixed number of statuses and parameters applied to them
       *That a person would not want to control the initial configuration of cells or save the previous starting 
    configuration of cells.
    * All XML files would be written in a similar format with exactly the same tags that we included
    * All XML files would contain valid, non error-throwing parameters
    * The data structure which held individual Cells and encapsulated their overall behavior could
    also share logic across simulations and configurations

* Dependencies
    * Some dependencies exist between Cell and Grid
        * the getNeighbors() and checkCells() methods in the RPS and Wator Cells both depend on our Grid classs 
        * multiple methods inside multiple Grid classes rely on getter and setter Cell methods
    * Many UI dependencies
        * creating simGrid requires Cell objects to dictate the colors of each cell based on cells' statuses
        * simGrid also requires Grid variable myCells to loop through and determine the initial positions of each Cell 
        * updateMyGrid() in UI_Setup depends on simGrid() object, which depends on Cell and Grid objects
        * UISetup depends on buttons initialized in separate classes, but that have their functionality dictated in 
        UI set upl 
        * Additionally, button functionality works only by setting it in UI-Setup and not simply on a different device. 



