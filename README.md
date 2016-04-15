Implementation by Medha Gopalaswamy and Jimin Tan

This implementation of the grader works by matching words given in student
input to a pre-processed HashMap of words and confidence ratings
that it makes from a initial series of txt files.

Using the example of when getting input to solve Quadratic Equations:
There are 4 methods of solving these equations. Lets say we look at the method
of using the Quadratic Formula. Then, the relevant txt file will have a number
of keywords which relate to using this method: "quadratic", or
"quadratic formula", for example.

All words on the same line are referred to as a "grouping" of keywords -
all of these words must occur in the student input for this grouping to be
considered to have appeared in the input (though not necessarily in
any particular order).

At the end of each grouping, we have a double between 0 and 1 that indicates
the confidence measure of this grouping of keywords. If multiple groupings are
found in a single input, the grouping with the highest confidence measure
is reported.

The help keyword lists do not have confidence measures - if there is
any indication of uncertainty in the student's answer, we give them
help. This is because we believe that students should try to give
answers they are very confident about, and if they are not sure,
we should try to help them (so that they can be confident next time).

The program then parses the student's input:
	-If the student's input has keywords that match those of the
		help keywords, help is immediately returned.
	-Otherwise, if the input matches any of the keywords of relevant
		methods, the methods and their confidence measures are returned
	-Otherwise, if none of the methods match, "Unknown Method" is returned.
	
There are examples for both Quadratic Equation and Simultaneous Equation
questions in the main method of the Main class. The sample output is
displayed when the Main class is run.

While basic keyword text files are included in this implementation, we leave
it to the organization to create fully comprehensive files for
keywords and groupings of keywords for the help lists and each method.