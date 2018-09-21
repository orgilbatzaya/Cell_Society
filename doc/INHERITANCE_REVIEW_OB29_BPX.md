##PART 1
1. What is an implementation decision that your design is encapsulating (i.e., hiding) for other areas of the program?
    * My design encapsulates the creation of cells in the Simulation
    class and specific behaviors of simulations and simulation-specific
    cells within those classes.
2. What inheritance hierarchies are you intending to build within your area and what behavior are they based around?
    * I am building abstract Simulation and Cell classes that will extend
    to sub-classes specific to the simulation
    * They are based around the rules pertaining to each CA because some
    simulations contain different initial cell states and the cells themselves
    respond to their surroundings in their own ways.
3. What parts within your area are you trying to make closed and what parts open to take advantage of this polymorphism you are creating?
    * I am trying to make properties of each Cell closed because the state
    of a cell and its position in the grid should be kept closed. 
    * I am also trying to make the properties of the Simulation subclasses
    private because each Simulation has its own starting parameters and configurations.
    * I am open to using polymorphism in both Cell and Simulation because
    some of the methods should be implemented the same but some will be 
    abstract because subclasses will have different ways of checking Cells
    or updating states
4. What exceptions (error cases) might occur in your area and how will you handle them (or not, by throwing)?
   * During the update steps for the cells, I might run into collisions
   when cells switch states. I plan to deal with this by writing short methods and organizing
   helper methods
   * There might be problems when there are too many dependencies and passing in
   parameters and using getter/setters could get complicated. I plan to deal with by not using 
   too many parameters in single methods
5. Why do you think your design is good (also define what your measure of good is)?
   * I think my design is good because it has a good amount of inheritance 
   and splits behavior and properties between specific Simulation and Cell classes
   * My measure of good is when all the components of the project are easily
   understandable and have a clear purpose. I also want to write shorter,
   more independent classes that have organized methods and structures

##Part 2
1. How is your area linked to/dependent on other areas of the project?
    * My part of the project (Cell/Simulation/Grid) is linked to the front-end
    because we need to figure out a way to connect the two. 
2. Are these dependencies based on the other class's behavior or implementation?
    * As of now, Simulation depends on Grid, which depends on Cell. 
3. How can you minimize these dependencies?
    * I can minimize these dependencies by changing how I'm using instance variables
4. Go over one pair of super/sub classes in detail to see if there is room for improvement. 
Focus on what things they have in common (these go in the superclass) and what about them varies (these go in the subclass).
    * Segregation Cell has an extra method compared to Cell super class called isSatisfied() which
    is unique to the Segregation game. 
##Part 3
1. Come up with at least five use cases for your part (most likely these will be useful for both teams).
    * Cell will be used by Grid to fill it up
    * Cell will be 
2. What feature/design problem are you most excited to work on?
    * I am most excited to figure out the rules for each cell.
3. What feature/design problem are you most worried about working on?
    * I am most worried about potential collisions or out of bounds
    errors
