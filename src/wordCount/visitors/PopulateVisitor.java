package wordCount.visitors;

import wordCount.dsForString.TreeDataStructure;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import wordCount.util.Logger;
import wordCount.util.Node;

public class PopulateVisitor implements Visitor {

    private String FILE_LOC = "";
    
    //constructor
    public PopulateVisitor(String file)
    {
        FILE_LOC = file;
    }
    public void visit(TreeDataStructure input) {
        //code to populate the data structure based
        //on the file location
        
        //read the file
        String fileContents = readFile(FILE_LOC);
        
        //split in to words
        String[] splitedData = fileContents.split(" ");
        
        for(int i = 0; i < splitedData.length;i++)
        {
            //the words must have length of atleast 1
            if(splitedData[i].length() > 0)
            {
                Logger.writeOutput("Word inserted ", 1);
                
                //remove all white space around the term
                String term = splitedData[i].trim();
                
                //it is a new term
                if(!input.contains(term))
                {
                    Node newTerm = new Node(1);
                    input.insert(term, newTerm);
                }
                else
                {
                    Node temp = input.get(term);
                    
                    //increment the count
                    temp.setCount(temp.getCount()+1);
                }
            }
        }
        
    }
    //*** Read the file and put it in a string ***//
    private static String readFile(String in) 
    {
        String output = "";
        String fileName = in;
        String line = null;
        StringBuilder br = new StringBuilder();
        try 
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                line = line.trim();
                //add a space at the end of the line
                line = line + " ";
                br.append(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) 
        {
            System.out.println("Couldn't open the file ...");             
        }
        catch(IOException ex) 
        {
            System.out.println("Couldn't read the file ...");
        }
        
        //output everything
        output = br.toString();
        return output;
    }
    
}
