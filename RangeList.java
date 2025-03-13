public class RangeList extends IntegerList {

    // Constructor for initializing with a range of values
    public RangeList() {
        super();
    }

    public RangeList(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }

        for (int i = lowerBound; i <= upperBound; i++) {
            super.add(i);
        }
    }

    // Implementing the add method to add a range of numbers
    public void add(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
        }
        
        System.out.println("Adding range from " + lowerBound + " to " + upperBound);
        
        for (int i = lowerBound; i <= upperBound; i++) {
            super.add(i);
            System.out.println("Added: " + i);
        }
    }

}
