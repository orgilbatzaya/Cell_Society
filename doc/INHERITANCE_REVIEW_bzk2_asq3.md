# Inheritance Review

#### Names:
* Allen Qiu (asq3)

* Brooke Keene (bzk2)

### Part 1
1. What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
    
    At the moment, my UISetup class is what handles the creation of the GUI, including the making of the scene, the 
    buttons, the sliders, and the choice box. Therefore, this decision hides all of the GUI creation from the Main
    class. Hopefully in the future, this will add flexibility to my design as Main will function as my runner class and
    I will be able to add methods to the UISetup as necessary.
    
2. What inheritance hierarchies are you intending to build within your area and what behavior are they based around?

    I am intending on creating a PopupMessage super class that will create a popup message for various scenarios. I am 
    thinking of using inheritance to create subclasses for the different types of messages that the GUI will display
    depending on the user's actions, such as an error popup when the user enters a faulty input.
    
3. What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism 
you are creating?

    The pieces of code that I am trying to make closed are the pieces that handle initial GUI setup. This is because I 
    don't want others to be able to access it or make changes to it too freely.

4. What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
    
    * Invalid XML file code
    * User enters invalid input (grid size that is too large or small)

5. Why do you think your design is good (also define what your measure of good is)?

    I think my design is good because it will use inheritance to reduce repetitions in my code. I also think that my
    code is good because it uses the principles of being "shy" and "tell the other guy". I define good as code that uses
    concepts that we have discussed in class appropriately.

### Part 2
1. How is your area linked to/dependent on other areas of the project?
    
    The front-end aspect of this project is linked to the XML parts of the project because the user is able to specify
    some of the starting conditions of the grid. Additionally, this part of the project is dependent on the simulation 
    pieces because it is what displays the animation of the cell automata.
    
2. Are these dependencies based on the other class's behavior or implementation?

    These dependencies are based on the other class's behavior because as long as the simulations are programmed 
    correctly in the back-end of the project, it will be implemented properly in the front-end.
    
3. How can you minimize these dependencies?

    I don't know if there is any way to minimize the dependency of the GUI on the simulation because the GUI is simply 
    the piece that displays this data to the user.

4. Go over one pair of super/sub classes in detail to see if there is room for improvement. 
   Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
   
   The super class PopupMessage and the subclass ErrorMessage will share a method that creates the pop-up window (a.k.a.
   creates a new scene and displays text) and includes a button to dismiss the message. However, the ErrorMessage will 
   explicitly throw an exception, while the pop-up message does not need to do so.

### Part 3
1. Come up with at least five use cases for your part (most likely these will be useful for both teams).
    * User selects a simulation name from the choice box
        * New grid with specific configuration displays
        * Potentially simulation specific controls appear
    * Start button is pressed and simulation begins
    * Stop button is pressed and the simulation pauses
    * Step button is pressed and one stage of the simulation is performed
    * Reset button is pressed and the simulation is reset to its original conditions
    
2. What feature/design problem are you most excited to work on?

    I am most excited to begin connecting the back-end to the front-end. I am really excited to see the simulations
    display and perform the appropriate actions based on the user-specified controls.
    
3. What feature/design problem are you most worried about working on?
    
    I am mot worried to begin tackling the issue of creating simulation specific controls depending on the simulation
    the user has chosen. I feel that making these appear and disappear will be very challenging.
