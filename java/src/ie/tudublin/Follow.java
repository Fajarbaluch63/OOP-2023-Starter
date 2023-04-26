package ie.tudublin;

// Define a public class named Follow
public class Follow {
    private String word;
    private int count;
    
    public Follow(String word) {
      this.word = word;
      count = 1;
    }
    
    // Define a method that increments count
    public void incrementCount() {
      count++;
    }

    // Define a method that returns the word field
    public String getWord() {
      return word;
    }
    
    // Define a method that returns the count field
    public int getCount() {
      return count;
    }
    
    // Define a toString method
    public String toString() {
      return word + "(" + count + ")";
    }
  }
  