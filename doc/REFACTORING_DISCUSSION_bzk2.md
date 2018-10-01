### Duplication Refactoring
This was a work in progress refactoring, but I realized that I was calling the same setFill, setStroke, and updateCell 
methods each time I created a shape for the over all grid. To refactor this code for readability and to avoid the
duplicated code, I created a class colorCell that encapsulated these method calls.

###Checklist Refactoring
When thinking about the communication category of our Design Checkup, I think one of my biggest issues is magic values.
I made an effort when refactoring to correct the magic values that I had included. At the very least I made sure that 
formatting values were defined as instance variables so that it was easy to understand what purpose the values are 
serving. Some specific instances of magic values that we will definitely correct in the future are the parameters
specific to various simulations, such as the red-blue ratio for Segregation. These will later be handled by our XML. 

When focusing on the Design Checkup category of modularity, I realized that I had variables that I had defined as 
instance variables that were actually unnecessary or that should have been declared as local variables. I had defined a 
simGrid object in Main that was never used throughout the class so I deleted the creation of this variable and then 
commented out the method it called, getMyGrid. I also changed a button from UISetup, myStepBtn, because it was only 
being used in a single method in UISetup. Instead of initializing it as an instance variable, I refactored my code so 
that the button was only initialized as a local variable in the method in which it was used. 

When considering Flexibility, this seemed like more of a refactoring issue for back-end related classes. It is important
for us to implement a flexible set of UI buttons, but this is something that I will be working on in the near future, 
but that have not been implemented yet. 

###General Refactoring
One thing I noticed when reviewing my code was that many of the constructors called in UISetup needed a long list of 
parameters. This is one of "The Bloaters," a code smell that possibly represents inefficient code. However, this is an 
issue that I plan to fix in my final refactoring. 

Another thing I noticed that definitely needs to be refactored is the makeGrid method in the simGrid class. To change 
the grid configuration shape at the moment, you must uncomment the portion of code that handles that specific shape. 
This greatly hinders the flexibility of the code, but I plan on creating an if statement in the makeGrid method that 
will in turn call the method that handles the creation of either a square of a triangle grid. This increases the 
flexibility of our code because to add another grid shape, one would simply have add another option to the if-else 
conditional that would call a new method to create the new layout of new polygons. 

###Longest Method Refactoring
The method I would like to refactor so that it is not as long is the getXMLParameters method in the UISetup class.
The reason I believe this method should be refactored is because if you were to add another simulation to this program,
this class would become even bigger to read in all the specific parameters for that simulation. It would be easier to 
add to a conditional and then create a new method to handle the XML parsing for that parameter. 
