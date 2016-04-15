
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class Functions {

	ArrayList<ArrayList<String>> helpList;
	ArrayList<HashMap<ArrayList<String>, Double>> methodList;
	ArrayList<String> methods;


	public Functions(ArrayList<ArrayList<String>> helpList, ArrayList<HashMap<ArrayList<String>, Double>> methodList, ArrayList<String> inputMethods) {
		this.helpList = helpList;
		this.methodList = methodList;
		this.methods = inputMethods;
	}

	public boolean checkForHelp(String studentInput) {
		//Split the input string into separate words
		String words[] = studentInput.split(" |\\.|\\,|\\?");
		boolean containsAll = true;

		//put student input into a array list
		ArrayList<String> studentWords = new ArrayList<String>(Arrays.asList(words));

		for(ArrayList<String> s: helpList) {
			//check that every keyword in each grouping of keywords in help list is contained in the input
			for(String keyword : s) {
				if(studentWords.contains(keyword)) {
					containsAll = true;
				} else {
					containsAll = false;
				}
			}

			//If it does contain an entire grouping, return true
			if(containsAll) {
				return true;
			}
		}

		//If it contains none of the groupings, return false
		return false;
	}

	public double checkForMethods(String str, int method) {
		if(method < methodList.size()) {
			return checkForMethods(str, methodList.get(method));
		}
		else {
			return 0;
		}
	}

	public double checkForMethods(String studentInput, HashMap<ArrayList<String>, Double> method) {
		double confidence = 0.0;
		if(method != null) {
			boolean containsAll = true;

			//Split the input string into separate words and put into array list
			String words[] = studentInput.split(" |\\,|\\.|\\?|\\!");
			ArrayList<String> studentWords = new ArrayList<String>(Arrays.asList(words));

			for(List<String> s: method.keySet()) {
				//check that every keyword in each grouping of keywords in method keyword list is contained in the input
				for(String keyword : s) {
					containsAll = containsAll && studentWords.contains(keyword);
				}
				if(s.isEmpty()) {
					containsAll = false;
				}

				//If it does contain the entire grouping, update confidence number to the highest seen so far
				if(containsAll) {
					if(confidence < method.get(s)) {
						confidence = method.get(s);
					}
				}
				//reset for the next grouping
				containsAll = true;
			}
		}
		//return the confidence rating
		return confidence;
	}

}