package wordCount.visitors;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import wordCount.dsForString.TreeDataStructure;
import wordCount.util.Node;

public class WordcountVisitorImpl implements Visitor {

    private String OUTPUT_FILE = "";
    
    public WordcountVisitorImpl(String in)
    {
        OUTPUT_FILE = in;
    }

    public void visit(TreeDataStructure in) 
    {
        Map<String, Node> input = in.getDataStructure();
        
        // **** FILE WRITING STARTS HERE ****
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(OUTPUT_FILE, "UTF-8");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File could't be created ...");
            System.exit(0);
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("Unsupported Encoding ...");
            System.exit(0);
        }
        
        writer.println("Total words:\t"+numWords(input));
        writer.println("Distinct Words:\t"+numDistinctWords(input));
        writer.println("Characters:\t"+numChar(input));
        
        writer.close();
    }
    private int numWords(Map<String, Node> input)
    {
        //to keep track of the count
        int out = 0;
        
        for(Map.Entry<String, Node> entry: input.entrySet())
        {
            Node value = entry.getValue();
            out = out + value.getCount();
        }
        return out;
    }
    private int numDistinctWords(Map<String, Node> input)
    {
        int out;
        return input.size()-1;
    }
    private int numChar(Map<String, Node> input)
    {
        int count = 0;
        for(Map.Entry<String, Node> entry: input.entrySet())
        {
            String key = entry.getKey();
            Node value = entry.getValue();
            int temp = value.getCount();
            count = count + (key.length()*temp);
        }
        return count-1;
    }

}
