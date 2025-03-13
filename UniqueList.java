public class UniqueList extends IntegerList {
    
    // Default constructor that initializes the list with the MIN_CAPACITY.
    public UniqueList() {
        super();
    }
    
    // Constructor that initializes the list with the given capacity.
    public UniqueList(int capacity) {
        super(capacity);
    }
    
    // Override the add method to prevent duplicates.
    @Override
    public void add(int integer) {
        // Check if the integer already exists in the list
        if (indexOf(integer) != -1) {
            throw new IllegalArgumentException("The integer " + integer + " is already in the list.");
        }
        super.add(integer);
    }
    
    // Override the insert method to prevent duplicates.
    @Override
    public void insert(int index, int integer) {
        // Check if the integer already exists in the list
        if (indexOf(integer) != -1) {
            throw new IllegalArgumentException("The integer " + integer + " is already in the list.");
        }
        super.insert(index, integer);
    }
}
