
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class ReferenceGenerator {
    ArrayList<ArrayList<String>> helpList = new ArrayList<ArrayList<String>>();
    ArrayList<HashMap<ArrayList<String>, Double>> methodList = new ArrayList<HashMap<ArrayList<String>, Double>> ();
    ArrayList<String> methods = new ArrayList<String>();

    public ReferenceGenerator(ArrayList<String> inputMethods, String helpFilePath,ArrayList<String> methodsFilePath) {

        //record methods names, helpList and method lists
        this.methods = inputMethods;
        this.helpList = recordHelp(helpFilePath);
        for (String filePath : methodsFilePath){
            this.methodList.add(recordMethods(filePath));
        }

    }

    public ArrayList<ArrayList<String>> recordHelp(String filePath) {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        Scanner line = null;
        Scanner words = null;
        try {
            line = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //reading in and adding all words
        while (line.hasNextLine()) {
            words = new Scanner(line.nextLine());
            
            //Read in all the keywords in a group
            ArrayList<String> subList = new ArrayList<String>();
            while (words.hasNext()) {
                subList.add(words.next());
            }
            
            //Add this grouping of keywords to the main list
            list.add(subList);
        }
        line.close();
        return list;
    }

    public HashMap<ArrayList<String>, Double> recordMethods(String filePath) {
        HashMap<ArrayList<String>, Double> map = new HashMap<>();

        Scanner line = null;
        Scanner words = null;
        try {
            line = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //reading in and adding all words
        while (line.hasNextLine()) {
            words = new Scanner(line.nextLine());
            
            //Read in all the keywords in a group
            ArrayList<String> subList = new ArrayList<String>();
            while (words.hasNext()) {
                subList.add(words.next());
            }
            
            //Add this grouping of keywords along with the associated confidence to the main map
            double confidence = Double.parseDouble(subList.get(subList.size()-1));
            subList.remove(subList.size()-1);
            map.put(subList, confidence);
        }
        line.close();
        return map;
    }

}
