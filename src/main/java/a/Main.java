package a;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Main main = new Main();
        System.out.print("What is the word you want to memorize today?");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
//
//        main.putInFlashCard(word);
    }

    private void putInFlashCard(String word) {
        Database db = new Database();
        Dao dao = new Dao(db.openConnection());
        dao.insertWord(word);
        db.closeConnection(true);
    }

    private String giveWord() {
        Database db = new Database();
        Dao dao = new Dao(db.openConnection());
        String word = dao.findWord();
        db.closeConnection(true);
        return word;
    }
}

