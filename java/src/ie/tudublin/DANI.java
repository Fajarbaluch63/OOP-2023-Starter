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
		printModel();
		sonnet = writeSonnet();
    }

    public void draw() {
        background(0);
        fill(255);
		noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);

        if (sonnet != null) {
            // display the sonnet on the screen
            float x = width / 2;
            float y = height / 2;
            for (int i = 0; i < sonnet.length; i++) {
                text(sonnet[i], x, y);
                y += 25;
            }
        } else {
            // display prompt to press space to generate a new sonnet
            text("To generate a new sonnet press SPACE", width / 2, height / 2);
        }
        
    }

    public void keyPressed() {
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
		//if no match return null
        return null;
    }

	public void printModel() {
        for (Word word : model) {
            println(word.toString());
        }
    }

    public Follow findFollow(Word word, String str) {
        for (Follow follow : word.getFollows()) {
            if (follow.getWord().equals(str)) {
                return follow;
            }
        }
		//if no match return null
        return null;
    }

	public String[] writeSonnet() {
		String[] sonnet = new String[14];
		for (int i = 0; i < 14; i++) {
            Word word = model.get(random.nextInt(model.size()));
            String sentence = word.getWord();
            for (int j = 0; j < 7; j++) {
                Follow follow = null;
                if (word.getFollows().size() > 0) {
                    int total = 0;
                    for (Follow f : word.getFollows()) {
                        total += f.getCount();
                    }
                    int rand = random.nextInt(total);
                    int count = 0;
                    for (Follow f : word.getFollows()) {
                        count += f.getCount();
                        if (count > rand) {
                            follow = f;
                            break;
                        }
                    }
                }
                if (follow == null) {
                    break;
                }
                sentence += " " + follow.getWord();
                word = findWord(follow.getWord());
            }
            sonnet[i] = sentence;
        }
		return sonnet;
	}

}