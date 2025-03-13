public class SortedList extends IntegerList {

    // Default constructor that initializes the list with the MIN_CAPACITY.
    public SortedList() {
        super();
    }

    // Constructor that initializes the list with the given capacity.
    public SortedList(int capacity) {
        super(capacity);
    }

    // Override the add method to insert the integer in sorted order.
    @Override
    public void add(int integer) {
        // Find the correct index to insert the integer
        int index = 0;
        while (index < size() && get(index) < integer) {
            index++;
        }
        
        // Insert the integer in the correct position directly
        super.insert(index, integer);
    }

    // Override the insert method to ensure the integer is inserted in sorted order.
    @Override
    public void insert(int index, int integer) {
        // Insert operation is not supported for SortedList
        throw new UnsupportedOperationException("Insert operation is not supported in SortedList.");
    }
}
