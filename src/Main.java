
import java.util.ArrayList;

public class Main {

	public static boolean checkHelp(Functions questionType, String sentence) {
		//Check if the sentence contains help keywords
		return questionType.checkForHelp(sentence);
	}

	/*
    Purpose: Check if the sentence has method keywords
    Input: termsList, a List that contains all methods
           sentence, a String student inputs
    Output: a String that contains the keywords or nothing
	 */
	public static String checkMethods(Functions questionType, String sentence) {
		//Check if the sentence has the terms
		String methodUsed  = "";
		for (int i = 0; i < questionType.methods.size(); i++) {
			if (questionType.checkForMethods(sentence, questionType.methodList.get(i)) != 0) {
				methodUsed += "Method: " + questionType.methods.get(i) + ", Possibility: " +
						questionType.checkForMethods(sentence, questionType.methodList.get(i)) + ".    ";
			}
		}
		return methodUsed;
	}

	//Check the sentence with all the tools
	/*
    Input: IDKList, a list contains possible combinations for IDK situation.
           methodList, a list contains all methods.
           sentence, student's input.
    Output: a String contains the result.
	 */
	public static String checkSentence(Functions checking, String sentence) {

		//If there are any help keywords, return Help
		if (checkHelp(checking, sentence)) {
			return "Help";
		}
		//If there are any valid methods, return those
		else if (!checkMethods(checking, sentence).equals("")){
			return checkMethods(checking,sentence);
		}
		//Otherwise, return unrecognized method
		else {
			return "Unrecognized Method";
		}
	}

	public static void main(String[] args) {

		//Add all the methods that can be used for this type of question
		ArrayList<String> quadraticEquationMethods = new ArrayList<String>();
		quadraticEquationMethods.add("QuadraticFormula");
		quadraticEquationMethods.add("CompleteSquare");
		quadraticEquationMethods.add("TakeSquareRoots");
		quadraticEquationMethods.add("FactorQuadratic");

		//Add all the files for keywords for each method
		ArrayList<String> quadraticMethodsFileList = new ArrayList<String>();
		quadraticMethodsFileList.add("TextFiles/Method1Keywords.txt");
		quadraticMethodsFileList.add("TextFiles/Method2Keywords.txt");
		quadraticMethodsFileList.add("TextFiles/Method3Keywords.txt");
		quadraticMethodsFileList.add("TextFiles/Method4Keywords.txt");

		//Add all the methods that can be used for this type of question
		ArrayList<String> solvingEquationsMethods = new ArrayList<String>();
		solvingEquationsMethods.add("EliminationMethod");
		solvingEquationsMethods.add("SubstitutionMethod");

		//Add all the files for keywords for each method
		ArrayList<String> equationsMethodsFileList = new ArrayList<String>();
		equationsMethodsFileList.add("TextFiles/Method5Keywords.txt");
		equationsMethodsFileList.add("TextFiles/Method6Keywords.txt");

		//filepath for help keywords
		String helpFilePath = "TextFiles/helpKeyWords.txt";

		//Create references (lists and hashMaps) for all methods for quadratic equations, and create functions object
		ReferenceGenerator quadraticEquationRef = new ReferenceGenerator(quadraticEquationMethods, helpFilePath, quadraticMethodsFileList);
		Functions quadraticFunctions = new Functions(quadraticEquationRef.helpList, quadraticEquationRef.methodList, quadraticEquationRef.methods);

		//Create references (lists and hashMaps) for all methods for simultaneous equations, and create functions object
		ReferenceGenerator equationsRef = new ReferenceGenerator(solvingEquationsMethods, helpFilePath, equationsMethodsFileList);
		Functions equationFunctions = new Functions(equationsRef.helpList, equationsRef.methodList, equationsRef.methods);

		//Create a number of test input strings
		ArrayList<String> inputStringsQuadEquations = new ArrayList<String>();
		inputStringsQuadEquations.add("I don't know what's going on...");
		inputStringsQuadEquations.add("I am not sure...");
		inputStringsQuadEquations.add("I used quadratic formula, and complete square");
		inputStringsQuadEquations.add("I used quadratic formula, and then take the square roots");

		//Check output for each input string
		for(String input : inputStringsQuadEquations) {
			System.out.println('\n' + "Student input: " + input);
			System.out.println(checkSentence(quadraticFunctions, input));
		}

		ArrayList<String> inputStringsSimulEquations = new ArrayList<String>();
		inputStringsSimulEquations.add("I will use the substitution method.");
		inputStringsSimulEquations.add("Use elimination method.");
		inputStringsSimulEquations.add("I dunno???");
		
		
		for(String input : inputStringsSimulEquations) {
			System.out.println('\n' + "Student input: " + input);
			System.out.println(checkSentence(equationFunctions, input));
		}
	}
}
