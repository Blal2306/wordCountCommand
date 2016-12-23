package wordCount.dsForString;

import java.util.Map;
import java.util.TreeMap;
import wordCount.util.Node;
import wordCount.visitors.Visitable;
import wordCount.visitors.Visitor;

public class TreeDataStructure implements Visitable, TreeDuplicator {
    
    //*** DATA STRUCTURE ***
    Map<String, Node> data;
    
    //constructor
    public TreeDataStructure()
    {
        //initialize the data structure
        data = new TreeMap<String, Node>() {};
    }
    public void insert(String key, Node value)
    {
        data.put(key, value);
    }
    public Node get(String key)
    {
        Node out = data.get(key);
        return out;
    }
    public boolean contains(String key)
    {
        return data.containsKey(key);
    }
    public Map<String, Node> getDataStructure()
    {
        return data;
    }
    
    //VISITOR IMPLEMENTATION
    public void accept(Visitor x)
    {
        x.visit(this);
    }
    public TreeDataStructure copy()
    {
        //code to copy everthing in this tree
        TreeDataStructure newTree = new TreeDataStructure();
        
        for(Map.Entry<String, Node> entry: data.entrySet())
        {
            //get the key
            String key = entry.getKey();
            
            //get the value
            Node value = entry.getValue();
            
            //create a new Node
            //and make it a listner
            Node newNode = new Node(value);
            
            //insert the new Node and the key into the new structure
            newTree.insert(key, newNode);
        }
        
        return newTree;
    }
}
