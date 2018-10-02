cell society
====

This project implements a cellular automata simulator.

Names:
* Amy Kim (yk154)

* Brooke Keene (bzk2)

* Orgil Batzaya (ob29)

### Timeline

Start Date: September 15, 2018

Finish Date: October 2, 2018 (Grace Hopper extension)

Hours Spent: 75+hrs

### Primary Roles

* Amy Kim - Cell; implementing Game of Life, Spreading of Fire, refactoring Segregation and GUI.
* Brooke Keene - Frontend; GUI and XML
* Orgil Batzaya - Grid; Cell; WaTor, Segregation, and Rock Paper Scissors.
 


### Resources Used
GUI:
* Oracle JavaFX 8 API
* JavaFX Tutorial http://zetcode.com/gui/javafx/
* JavaFX Tutorial - JavaFX BorderPane http://www.java2s.com/Tutorials/Java/JavaFX/0330__JavaFX_BorderPane.htm
* JavaFX Button http://tutorials.jenkov.com/javafx/button.html#button-events
* CompSci308_2018Fall GitLab Repository (example_browser)

CSS:
* Buttons and Sliders - https://www.w3schools.com/howto/
* Oracle Getting Started with JavaFX - https://docs.oracle.com/javafx/2/get_started/css.htm
* Stack Overflow (Choice Box) - https://stackoverflow.com/questions/43530178/style-choicebox-list-with-css-in-javafx

XML:
* How to read and write XML files with code (https://www.makeuseof.com/tag/read-write-xml-file-code/)
* CompSci308_2018Fall GitLab Repository (spike_cellsociety)

File:
* JavaFX Tutorial - JavaFX FileChooser http://www.java2s.com/Tutorials/Java/JavaFX/0555__JavaFX_FileChooser.htm

### Running the Program

Main class: Main.java

Data files needed: 
* All .xml files in data folder
* English.properties in src/resources folder
* CSS styling file default.css in src folder

Interesting data files: 
* GameOfLife.xml
* Segregation.xml
* SpreadingOfFire.xml
* WaTorWorld.xml
* RockPaperScissors.xml

Features implemented:

    Buttons: Start, Stop, and Step control simulation, Cell shape, Grid Bounds
    Slider: Slider controls simulation speed
    Choose File Button: Allows you to select which .xml file you want to run the simulation on 
    Error messages: If you choose wrong input file (not .xml file), the error message shows up.
    State changable: Allow users to interact with the simulation dynamically to create or change a state at a grid location

Assumptions or Simplifications:
* Assumed that all our simulations Cell and Grid objects would be similar enough that we could simply extend a general
Cell and Grid class rather than using abstraction
* Assumed all XML files would be of the same format with the same tags that we used when writing test files
* Assumed that the data structure which held Cells (Grid) could also share logic with the 
different simulations

Known Bugs:
* Simulation specific parameters are hardcoded into our grid initialization rather than being read in from the XML file
* Grid configurations are initialized randomly every time a new Grid object is created instead of reading the initial 
states for each cell from an XML file 

Extra credit:


### Notes
* See Known Bugs
* Grid dimensions should not exceed 100 because of strain on machine

### Impressions
    Brooke: Connecting front and back end was much harder than I expected and took a lot longer. There where aspects of
    animating the grid that I never considered when first creating the GUI.
    
    Orgil: We should not have combined Grid functionality with Simulation
     functionality into one class (Grid) and its subclasses. We realized this 
     too late and could not easily extend to more simulations besides Rock, Paper,
     Scissors. This also made changing grid type and edge types very hard.
    
    Amy: The project was very hard and big. All of the rules were very different, 
    so we had a hard time making an abstract class.
