package ie.tudublin;

import java.util.ArrayList;

public class Word {
    public String word;
    public ArrayList<Follow> follows;

    public Word(String word) {
        this.word = word;
        this.follows = new ArrayList<Follow>();
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void addFollow(String word) {
        for (Follow follow : follows) {
            if (follow.getWord().equals(word)) {
                follow.count++;
                return;
            }
        }
        follows.add(new Follow(word, 1));
    }

    public String toString() {
        String output = word + ": ";
        for (Follow follow : follows) {
            output += follow.toString() + " ";
        }
        return output;
    }
}

