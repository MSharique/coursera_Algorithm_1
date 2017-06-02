import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
    public static void main(String[] args)
    {
        final int queueEle = Integer.parseInt(args[0]);
        final RandomizedQueue<String> elems = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            elems.enqueue(item);
        }
        
        for (int i = 0; i < queueEle; i++)
        {
            System.out.println(elems.dequeue());
        }
    }
}
