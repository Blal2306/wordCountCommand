package wordCount.visitors;

//The tree data structure will implements this interface
public interface Visitable 
{
    public void accept(Visitor x);
}
