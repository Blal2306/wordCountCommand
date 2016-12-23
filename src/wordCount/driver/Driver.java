package wordCount.driver;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import wordCount.util.Node;
import wordCount.visitors.PopulateVisitor;
import wordCount.dsForString.TreeDataStructure;
import wordCount.util.Logger;
import wordCount.visitors.Visitor;
import wordCount.visitors.WordcountVisitorImpl;

public class Driver {
    static String input_file = "";
    static String output_file = "";
    static int num_iterations = 0;
    
    public static void main(String[] args)
    {
        //disable the logger
        Logger.setDebugValue(-1);
        
        input_file = args[0];
        output_file = args[1];
        num_iterations = Integer.parseInt(args[2]);
		
		//validate command line arguments
        if(num_iterations < 1 || input_file == null || output_file == null)
        {
            System.exit(0);
        }
        
        //create the data Structure
        TreeDataStructure  ts = null;
        
        long startTime = System.currentTimeMillis();
        
        for(int i = 0; i < num_iterations; i++)
        {
            ts = new TreeDataStructure();
            Visitor v = new PopulateVisitor(input_file);
            Visitor v2 = new WordcountVisitorImpl(output_file);
        
            //populate visitor
            ts.accept(v);
        
            //word count visitor
            ts.accept(v2);
        }
        
        long finishTime = System.currentTimeMillis();
        long total_time = (finishTime-startTime)/num_iterations;
        System.out.println("Total Time: "+total_time+" ms\n");
        
        //+++++++++++ BACKUP TEST ++++++++++++
        
        //create a backup copy
        TreeDataStructure ts2 = ts.copy();

        //update the original data structure
        Map<String, Node> original = ts.getDataStructure();
        System.out.println("Updating the original data structure ...");
        System.out.println("Setting count for all terms to zero ...");
        for(Map.Entry<String, Node> entry: original.entrySet())
        {
            Node x = entry.getValue();
            x.setCount(0);
        }
        
        //print the original data strucutre
        System.out.println("Printing the original Data Structure ...");
        print(ts);
        
        //print the backup data structure
        System.out.println("Printing the backup copy of the Data Structure ...");
        print(ts2);
        
        //++++++++ END OF BACKUP TEST +++++++++

    }
    public static void print(TreeDataStructure in)
    {
        Map<String, Node> input = in.getDataStructure();
        System.out.println("\nKEY VALUE");
        System.out.println("*** *****");
        
        for(Map.Entry<String, Node> entry: input.entrySet())
        {
            String key = entry.getKey();
            Node value = entry.getValue();
            int count = value.getCount();
            System.out.println(key+", "+count);
        }
        System.out.println();
    }
}
