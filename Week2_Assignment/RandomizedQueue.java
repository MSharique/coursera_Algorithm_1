import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] rQueueEle = (Item[]) new Object[0];
    private int rQueuesize = 0;

    public RandomizedQueue()
    {
        // construct an empty randomized queue
    }

    public boolean isEmpty()
    {
        // is the queue empty?
        return rQueuesize == 0;
    }

    public int size()
    {
        // return the number of items on the queue
        if (isEmpty()) 
            return 0;
        int count = 0;
        for (Item i : this)
        {
            count++;
        }
        return count;
    }

    public void enqueue(Item item)
    {
        // add the item
        if (item == null)
        {
            throw new java.lang.NullPointerException();
        }

        final int temp = rQueueEle.length;
        
        if (temp == 0)
        {
            resize(1);
        }
        else if (temp <= rQueuesize)
        {
            resize(2 * temp);
        }
        rQueueEle[rQueuesize++] = item;
    }

    private void resize(int max)
    {
        final Item[] temp = (Item[]) new Object[max];
        final int arrLength = rQueueEle.length;
        int numElemToCopy;
        if (arrLength > max)
        {
            numElemToCopy = max;
        }
        else
        {
            numElemToCopy = arrLength;
        }
        System.arraycopy(rQueueEle, 0, temp, 0, numElemToCopy);
        rQueueEle = temp;
    }

    public Item dequeue()
    {
        // delete and return a random item
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        int rand = StdRandom.uniform(rQueuesize);
        final Item obj = rQueueEle[rand];
        rQueueEle[rand] = rQueueEle[rQueuesize - 1];
        rQueuesize--;
        final int arrLength = rQueueEle.length;
        if (rQueuesize > 0 && rQueuesize == arrLength / 4)
        {
            resize(arrLength / 2);
        }
        return obj;
    }

    public Item sample()
    {
        // return (but do not delete) a random item
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        Item obj = null;
        while (obj == null)
        {
            int rand = StdRandom.uniform(rQueuesize);
            obj = rQueueEle[rand];
        }
        return obj;
    }

    public Iterator<Item> iterator()
    {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator<Item>(rQueueEle);
    }

    private static class RandomizedQueueIterator<Item> implements Iterator<Item>
    {
        private RandomizedQueue<Item> newColl = new RandomizedQueue<Item>();

        public RandomizedQueueIterator(Object[] items)
        {
            for (Object o : items)
            {
                if (o == null)
                    break;
                newColl.enqueue((Item) o);
            }
        }

        @Override
        public boolean hasNext()
        {
            return !newColl.isEmpty();
        }

        @Override
        public Item next()
        {
            if (newColl.isEmpty())
            {
                throw new java.util.NoSuchElementException();
            }
            return newColl.dequeue();
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
