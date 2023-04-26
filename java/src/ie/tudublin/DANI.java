package ie.tudublin;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class DANI extends PApplet {

    public ArrayList<Word> model;
    public Random random;

    public void settings() {
        size(800, 800);
        // fullScreen(SPAN);
    }

	public String[] sonnet;

    public void setup() {
        colorMode(HSB);
        model = new ArrayList<Word>();
        random = new Random();
        loadFile("shakespere.txt");
    }

    public void draw() {
        background(0);
        fill(255);
        
    }

    public void keyPressed() {
		if (key == ' ') {
		}
	}	

    public void loadFile(String filename) {
        String[] lines = loadStrings(filename);
        for (String line : lines) {
            String[] words = split(line, ' ');
            for (int i = 0; i < words.length; i++) {
                String w = words[i].toLowerCase().replaceAll("[^\\w\\s]", "");
                if (w.equals("")) {
                    continue;
                }
                Word word = findWord(w);
                if (word == null) {
                    word = new Word(w);
                    model.add(word);
                }
                if (i < words.length - 1) {
                    String next = words[i + 1].toLowerCase().replaceAll("[^\\w\\s]", "");
                    if (!next.equals("")) {
                        word.addFollow(next);
                    }
                }
            }
        }
    }

    public Word findWord(String str) {
        for (Word word : model) {
            if (word.getWord().equals(str)) {
                return word;
            }
        }
        return null;
    }

    

    public Follow findFollow(Word word, String str) {
        for (Follow follow : word.getFollows()) {
            if (follow.getWord().equals(str)) {
                return follow;
            }
        }
        return null;
    }

}