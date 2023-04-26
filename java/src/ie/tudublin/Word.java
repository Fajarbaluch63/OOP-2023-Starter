package ie.tudublin;
import java.util.ArrayList;

// Define a public class named Word
public class Word {
 // Define two public fields, one for the word itself and one for a list of its followers
  public String word;
  public ArrayList<Follow> follows;

   // Define a constructor that takes a String parameter representing the word and initializes the fields
  public Word(String word) {
    this.word = word;
    follows = new ArrayList<Follow>();
  }

  // Define a method that returns the word field
  public String getWord() {
    return word;
  }

  // Define a method that returns the list of follows
  public ArrayList<Follow> getFollows() {
    return follows;
  }

  // Define a method that adds a new follow to the list or increments the count of an existing follow
  public void addFollow(String word) {
    // Loop through the follows list to see if the given word is already a follow
    for (Follow follow : follows) {
      // If it is, increment the count of the existing follow and return
      if (follow.getWord().equals(word)) {
        follow.incrementCount();
        return;
      }
    }
    // If it's not already a follow, create a new follow object and add it to the list
    follows.add(new Follow(word));
  }

  // Define a method that returns a String representation of the Word object
  public String toString() {
    String result = word + ": ";
    // Loop through the follows list and add the String representation of each Follow object to the result
    for (Follow follow : follows) {
      result += follow.toString() + " ";
    }
    return result;
  }
}