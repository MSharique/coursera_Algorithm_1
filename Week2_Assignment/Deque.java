import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private static class Node<Item>
    {
        private Item obj;
        private Node<Item> next;
        private Node<Item> previous;

        public Node(Item obj, Node<Item> next, Node<Item> previous)
        {
            this.obj = obj;
            this.next = next;
            this.previous = previous;
        }

        public Item getObj()
        {
            return obj;
        }

        public Node<Item> getNext()
        {
            return next;
        }

        public void setNext(Node<Item> n)
        {
            this.next = n;
        }

        public void setPrevious(Node<Item> p)
        {
            this.previous = p;
        }

        public Node<Item> getPrevious()
        {
            return previous;
        }
    }

    private Node<Item> first;
    private Node<Item> last;

    public Deque()
    {
        // construct an empty deque
        first = null;
        last = null;
    }

    public boolean isEmpty()
    {
        // is the deque empty?
        return first == null && last == null;
    }

    public int size()
    {
        // return the number of items on the deque
        if (isEmpty())
            return 0;
    
        int count = 0;
        for (Item i : this)
        {
            count++;
        }
        return count;
    }

    public void addFirst(Item item)
    {
        // insert the item at the front
        nullCheck(item);
        if (first == null && last == null)
        {
            final Node<Item> itemNode = new Node<Item>(item, null, null);
            first = itemNode;
            last = itemNode;
            return;
        }

        final Node<Item> oldFirst = first;
        first = new Node<Item>(item, oldFirst, null);
        if (oldFirst != null)
            oldFirst.setPrevious(first);
    }

    public void addLast(Item item)
    {
        // insert the item at the end
        nullCheck(item);
        if (first == null && last == null)
        {
            final Node<Item> itemNode = new Node<Item>(item, null, null);
            first = itemNode;
            last = itemNode;
            return;
        }

        final Node<Item> newLast = new Node<Item>(item, null, last);
        last.setNext(newLast);
        last = newLast;
    }

    public Item removeFirst()
    {
        // delete and return the item at the front
        checkAvailability();
        final Node<Item> f = first;
        first = f.getNext();
        if (first == null) 
            last = null;
        else
            first.setPrevious(null);
        
        return f.getObj();
    }

    private void checkAvailability()
    {
        if (first == null && last == null)
            throw new java.util.NoSuchElementException();
    }

    public Item removeLast()
    {
        // delete and return the item at the end
        
        checkAvailability();

        final Node<Item> l = last;
        last = l.getPrevious();
        if (last != null)
            last.setNext(null);
        else
        {
            first = null;
            last = null;
        }
        
        l.setNext(null);
        l.setPrevious(null);
        return l.getObj();
    }

    public Iterator<Item> iterator()
    {
        // return an iterator over items in order from front to end

        return new Iterator<Item>() {
            private Node<Item> currElement = first;
            @Override
            public boolean hasNext() {
                return currElement != null;
            }
            @Override
            public Item next() {
                if (currElement == null) {
                    throw new NoSuchElementException();
                }
                final Item obj = currElement.getObj();
                currElement = currElement.getNext();
                return obj;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void nullCheck(Item item)
    {
        if (item == null)
            throw new NullPointerException();
    }
}
