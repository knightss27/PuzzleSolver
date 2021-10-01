public interface Frontier {
    // removes all elements from the Frontier
    void clear();

    // returns true if the Frontier has no elements
    boolean isEmpty();

    // adds a new node to the Frontier
    void insert(SearchNode node);

    // removes and returns the next node in the Frontier
    SearchNode removeNext();
}