package LinkedRRSet;
 
import java.util.Collection;

/**
 *
 * @author gracesubianto 16946441
 * @param <E>
 */
public class LinkedRRSet<E extends Comparable<E>> extends LinkedSet<E> 
{
    /**
     * Default constructor
     */
    public LinkedRRSet()
    {  
        super();
    }

    /**
     * Calling existing constructor
     * @param c 
     */
    public LinkedRRSet(Collection<? extends E> c)
    {  
        this(); //Calls above constructor  
    }
 
    /**
     * Add method adds numbers to a set and organizes them in a natural order
     * @param element
     * @return true or false
     */
    @Override
    public boolean add(E element) 
    {
        if(element != null) 
        {
            Node<E> newNode = new Node<>(element);
            if(!contains(element)) 
            {
                if(firstNode == null) 
                {
                    firstNode = newNode;
                    numElements++;
                    return true;
                } 
                else 
                {
                    if(firstNode.element.compareTo(newNode.element) > 0) 
                    {
                        addAtStart(firstNode, newNode);
                    } 
                    else 
                    {
                        Node<E> currentNode = firstNode;
                        while (currentNode.next != null) 
                        {
                            if(currentNode.next.element.compareTo(newNode.element) < 0) 
                            {
                                currentNode = currentNode.next;
                            } 
                            else 
                            {
                                break;
                            }
                        }
                        if(currentNode.next != null) 
                        {
                            newNode.next = currentNode.next;
                            currentNode.next = newNode;
                            numElements++;
                            return true;
                        } 
                        else 
                        {
                            addAtEnd(currentNode, newNode);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
 
    /**
     * 
     * @param begin
     * @param end
     * @return removedSet
     * @throws Exception 
     */
    public LinkedRRSet<E> remove(E begin, E end) throws Exception 
    {
        boolean firstInRange = false;
        boolean finalNull = false;
        
        LinkedRRSet<E> removedSet = new LinkedRRSet<>();
        Node<E> currentNode = firstNode; 
        
        if((begin == null) && (end != null)) 
        {
            begin = firstNode.element;
        } 
        else if((begin != null) && (end == null)) 
        {
            end = getLast();
            finalNull = true;
        } 
        else if((begin == null) && (end == null)) 
        {
            begin = firstNode.element;
            end = getLast();
            finalNull = true;
        }
        if((!contains(begin)) || (!contains(end))) 
        {
            throw new Exception("Error. Not found.");
        } 
        else 
        {
            while(currentNode.next != null) 
            {
                if((currentNode.element == firstNode.element) && (currentNode.element.compareTo(begin)) == 0) 
                {
                    removedSet.add(firstNode.element);
                    firstNode = currentNode.next;
                    firstInRange = true;
                } 
                else if(currentNode.next.element.compareTo(begin) >= 0) 
                {
                    if((currentNode.next.element.compareTo(end) == 0) && (finalNull == false)) 
                    {
                        break;
                    } 
                    else 
                    {
                        if(firstInRange) 
                        {
                            removedSet.add(currentNode.next.element);
                            currentNode = currentNode.next;
                            firstNode = currentNode.next;
                        } 
                        else 
                        {
                            removedSet.add(currentNode.next.element);
                            currentNode.next = currentNode.next.next;
                        }
                    }
                } 
                else 
                {
                    currentNode = currentNode.next;
                }
            }
        }
        return removedSet;
    }
 
    /**
     * 
     * @param begin
     * @param end
     * @return removedSet
     * @throws Exception 
     */
    public LinkedRRSet<E> retain(E begin, E end) throws Exception 
    {
        boolean finalNull = false;
        
        LinkedRRSet<E> removedSet = new LinkedRRSet<>();
        
        Node<E> currentNode = firstNode;
        Node<E> stopPoint = firstNode;
 
        if((begin == null) && (end != null)) 
        {
            begin = firstNode.element;
        } 
        else if((begin != null) && (end == null)) 
        {
            end = getLast();
            finalNull = true;
        } 
        else if((begin == null) && (end == null)) 
        {
            begin = firstNode.element;
            end = getLast();
            finalNull = true;
        }
        if((!contains(begin)) || (!contains(end))) 
        {
            throw new Exception("Error. Not found.");
        } 
        else 
        {
            while(currentNode.next != null) 
            {
                if((currentNode.element == firstNode.element) && (currentNode.element.compareTo(begin)) != 0) 
                {
                    removedSet.add(currentNode.element);
                    firstNode = currentNode.next;
                    currentNode = currentNode.next;
                } 
                else 
                {
                    if(currentNode.next.element.compareTo(end) == 0) 
                    {
                        if(finalNull) 
                        {
                            stopPoint = currentNode.next;
                            currentNode = currentNode.next;
                        } 
                        else 
                        {
                            stopPoint = currentNode;
                            removedSet.add(currentNode.next.element);
                            currentNode = currentNode.next;
                        }
                    } 
                    else if(currentNode.next.element.compareTo(end) > 0) 
                    {
                        removedSet.add(currentNode.next.element);
                        currentNode = currentNode.next;
                    } 
                    else 
                    {
                        currentNode = currentNode.next;
                    }
                }
            }
            stopPoint.next = null;
        }
        return removedSet;
    }
    
    
    /**
     * Stores all numbers in an original list of numbers
     * @param num 
     */
    private void originalSet(LinkedRRSet<Integer> num) 
    {
        num.add(2);
        num.add(5);
        num.add(1);
        num.add(3);
        num.add(4);
        num.add(6);
        num.add(7);
    }
 
    /**
     * An isolated method to help add node to the start of the list of numbers
     * Moved outside of the larger methods to maintain less errors
     * @param current
     * @param toAdd 
     */
    private void addAtStart(Node<E> current, Node<E> toAdd) 
    {
        toAdd.next = current;
        firstNode = toAdd;
        numElements++;
    }
 
    /**
     * An isolated method to help add node to the end of the list of numbers
     * Moved outside of the larger methods to maintain less errors
     * @param current
     * @param toAdd
     * @return true
     */
    private boolean addAtEnd(Node<E> current, Node<E> toAdd) 
    {
        current.next = toAdd;
        toAdd.next = null;
        numElements++;
        return true;
    }
 
    /**
     * Finds and returns the last element in the list
     * @return currentNode.element
     */
    private E getLast() 
    {
        Node<E> currentNode = firstNode;
        while(currentNode.next != null) 
        {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }
 
    /**
     * A method to print the list of numbers returned and retained from the original list
     * Adds brackets before and after numbers
     * @param num
     * @param method
     * @return 
     */
    private String getNumbers(LinkedRRSet<E> num, String method) 
    {
        String print = method + " = { ";
        
        if(num.firstNode != null) 
        {
            Node<E> currentNode = num.firstNode;
            while(currentNode != null) 
            {
                print += currentNode.element + ", ";
                currentNode = currentNode.next;
            }
        }
        if(num.firstNode == null) 
        {
            return print + " }";
        } 
        else 
        {
            print = print.substring(0, print.length() - 2) + " }";
            return print;
        }
    }
 
    public static void main(String[] args) throws Exception 
    {
        LinkedRRSet<Integer> num = new LinkedRRSet<>();
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("retain(2,6)");
        System.out.print(num.getNumbers(num.retain(2, 6), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("remove(2,6)");
        System.out.print(num.getNumbers(num.remove(2, 6), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("remove(4,5)");
        System.out.print(num.getNumbers(num.remove(4, 5), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("retain(6,7)");
        System.out.print(num.getNumbers(num.retain(6, 7), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("retain(null, 4)");
        System.out.print(num.getNumbers(num.retain(null, 4), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("retain(4, null)");
        System.out.print(num.getNumbers(num.retain(4, null), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
 
        num.originalSet(num);
        System.out.println("initial set = { 1, 2, 3, 4, 5, 6, 7 }");
        System.out.println("retain(null,null)");
        System.out.print(num.getNumbers(num.retain(null, null), "returned set"));
        System.out.println(num.getNumbers(num, " retained set"));
        System.out.println("");
    }
}
 
