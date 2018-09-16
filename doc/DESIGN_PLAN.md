# plan

#### Names:
* Amy Kim (yk154)

* Brooke Keene (bzk2)

* Orgil Batzaya (ob29)
    

### Introduction

> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

The goal of this project is to use JavaFX to design an GUI that allows a user to view different cell automata in the form of a 2D grid. We hope to write code that will allow us to easily add new features and simulations that build off of our basic framework of the program. Furthermore, we intend to make sure that customizable features, such as the user defined parameters, are as flexible and extendible as possible. 

We envision that the classes that are responsible for handling the simulations themselves will be "closed", meaning that they are wholly self-contained. The classes which relate to the user interface and animation will be "open" in the sense that they can take in any specified simulation and run it.

We will design our program so that the user interface allows the user to choose the size of the grids and the speed of simulation. The user can start and stop the simulation whenever they want to.

Our classes fall into 3 categories: front-end, back-end, and XML file handling. 

### Overview

> This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should discuss specific classes, methods, and data structures, but not individual lines of code.

We envision having a main class that launches the GUI, a base Cell class, a base Simulation class, and a base Grid class. These classes will implement the Game of Life simulation since it is the simplest. We will have subclasses for Cell, Simulation, and Grid for each of the different simulations. These subclasses will override certain methods of their parent classes, for example, the
checkNeighbors will be implemented differently depending on the simulation's rules. 

The Simulation class(es) will act as the "engine" of the program and contain methods that will initialize a Grid and cell states, start, stop, update, and reset animations, and return quantitative date regarding cell states. 
![](https://)



### User Interface

> This section describes how the user will interact with your program (keep it very simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.

![UI image](https://coursework.cs.duke.edu/CompSci308_2018Fall/cellsociety_team16/blob/master/doc/UIplan.png)

### Design Details 

> This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions.

### Design Consideration
> This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any assumptions or dependencies regarding the program that impact the overall design.

* Abstract classes vs. Inheritance

### Team Responsibilities

> This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

 * Our primary responsibilities are focused on Back-end and each of us will take different types of simulation.
 * All of us will be working on Front-end and XML handling as secondary responsibility.