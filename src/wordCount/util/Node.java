package wordCount.util;

import java.util.Observable;
import java.util.Observer;

public class Node extends Observable implements Observer
{
    Observable observable;
    private int count = 0;
    
    //no argument constructor
    public Node()
    {
        
    }
    //constrcutor for the observer
    public Node(Observable x)
    {
        observable = x;
        observable.addObserver(this);
        
        //initialize the count
        Node temp = (Node)observable;
        this.count = temp.getCount();
    }
    public Node(int x)
    {
        count = x;
    }
    public void update(Observable o, Object arg)
    {
        if(o instanceof Node)
        {
            Node temp = (Node)o;
            count = temp.getCount();
        }
    }
    public void dataChanged()
    {
        setChanged();
        notifyObservers();
    }
    public void setCount(int x)
    {
        count = x;
        dataChanged();
    }
    public int getCount()
    {
        return count;
    }
}

