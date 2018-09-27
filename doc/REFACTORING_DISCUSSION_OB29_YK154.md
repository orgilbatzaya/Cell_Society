### Duplication Refactoring
We removed the method getNearCellPositions from WatorGrid and FireGrid because 
it was already in the Grid class.

###Checklist Refactoring
One field in SegregationCell was accidently public so we made it private.
We have cyclomatic complexity of 20 in SimGrid's method SetCellColor.
This will be addressed when our third team member returns since she is primarily responsible for front-end.
This problem can be fixed by not hard coding situations. 

###General Refactoring
We have a few empty methods in Cell because the actual implementations were
put into the sub classes. We need to log or rethrow our exceptions in
XMLParser. We have a code smell in getUnsatisfiedCells in SegGrid. It has a double for loop and nested if statements.
We could not fix it currently, because it is part of a larger problem.

###Longest Method Refactoring
Our longest method is currently setCellColor, which will be taken care of 
when our third team member returns. 


