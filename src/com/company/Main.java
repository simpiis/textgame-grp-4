package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Keys keys = new Keys();
        keys.setDirtkeys(0);
        keys.setFirekeys(0);
        keys.setOceankeys(0);
        keys.setWindkeys(0);
        int chest1Check = 0;
        int chest2Check = 0;
        int chest3Check = 0;
        int minotaurPos = 0;
        int typhonePos = 0;
        int wampaPos = 0;
        Doors.createDoor();
        String mainMenu = "0";
        Rooms [] list = new Rooms[49];
        Scanner input = new Scanner(System.in);
        Heroes hero = new Heroes(1, 1, "");
        Monster minotaur = new Monster(50,10, "Minotaur", 0);
        Monster typhone = new Monster(60,30, "Typhone", 0);
        Monster wampa = new Monster(100,20, "Wampa", 0);
        Score score = new Score();
        Treasure chest1 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem(), 0);
        Treasure chest2 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem(), 0);
        Treasure chest3 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem(), 0);
        Keyboard keyboard = new Keyboard("2","1", "3", "4", "9");
        String choice = "4";
        int position = 0;
        int position2 = 0;
        int round = 0;
        int highscore = 0;



        ArrayList<Item> playerInventory = new ArrayList<>();
        boolean cont = true;

        Path filePath = Paths.get("HighScore");
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                highscore = scanner.nextInt();
            } else {
                scanner.next();
            }
        }
        while (cont) {
            System.out.println("--- Main Menu ---");
            System.out.println("1. Start new game\n2. Load game\n3. Game Instructions\n4. Scoreboard\n5. Quit");
            mainMenu = input.nextLine();
            if(mainMenu.equals("5")){
                System.exit(0);}
            else if(mainMenu.equals("2")) {
                String pos;
                try {
                    Scanner scanner1 = new Scanner(new File("hero.txt"));
                    int i = 0;
                    while (i <= 7) {
                        if (i == 0) {
                            hero.setName(scanner1.nextLine());
                        } else if (i == 1) {
                            hero.setDamage(scanner1.nextInt());
                        } else if (i == 2) {
                            hero.setHp(scanner1.nextInt());
                        } else if (i == 3) {
                            hero.setRogueMana(scanner1.nextInt());
                        } else if (i == 4) {
                            hero.setMageCounter(scanner1.nextInt());
                        } else if (i == 5) {
                            position = scanner1.nextInt();
                        } else if (i == 6) {
                            score.setScore(scanner1.nextInt());
                        } else if (i == 7) {
                            round = scanner1.nextInt();
                        }
                        i += 1;
                        choice = "1";
                    }
                    scanner1.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

               FileInputStream fis = new FileInputStream("inventory.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                playerInventory = (ArrayList<Item>) ois.readObject();
                ois.close();

                try {
                    Scanner scanner2 = new Scanner(new File("Monster.txt"));
                    int i = 0;
                    while (i <= 5) {
                        if (i == 0) {
                            wampa.setHp(scanner2.nextInt());
                        } else if (i == 1) {
                            wampaPos = scanner2.nextInt();
                        } else if (i == 2) {
                            typhone.setHp(scanner2.nextInt());
                        } else if (i == 3) {
                            typhonePos = scanner2.nextInt();
                        } else if (i == 4) {
                            minotaur.setHp(scanner2.nextInt());
                        } else if (i == 5) {
                            minotaurPos = scanner2.nextInt();
                        }
                        i += 1;
                    }
                    scanner2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Scanner scanner3 = new Scanner(new File("Treasure.txt"));
                    int i = 0;
                    while (i <= 5) {
                        if (i == 0) {
                            chest1.setPos(scanner3.nextInt());
                        } else if (i == 1) {
                            chest2.setPos(scanner3.nextInt());
                        } else if (i == 2) {
                            chest3.setPos(scanner3.nextInt());
                        } else if (i == 3) {
                            if (scanner3.nextInt() == 1) {
                                chest1Check = 1;
                            }
                        }
                        else if (i == 4) {
                            if (scanner3.nextInt() == 1) {
                                chest2Check = 1;
                            }
                        }
                        else if (i == 5) {
                            if (scanner3.nextInt() == 1) {
                                chest3Check = 1;
                            }
                        }
                        i += 1;
                    }
                    scanner3.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                cont= false;
            }
            else if (mainMenu.equals("4")) {
                System.out.println("Current High Score is: " + highscore);
                System.out.println("Press '1' to go back to Main Menu");
                input.nextLine();
            }
            else if (mainMenu.equals("3")) {
                Info.info();
            }
            else if (mainMenu.equals("1")) {
                cont = false;
            }
            else {
                System.out.println("Invalid command\n");
            }
        }
        Rooms[] rooms = Rooms.createRooms(minotaur, typhone, wampa, chest1, chest2, chest3, wampaPos, typhonePos, minotaurPos, list, chest1Check, chest2Check, chest3Check);
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.println("***************************");
            System.out.println("*  Welcome to WoW Borgen  *");
            System.out.println("*                         *");
            System.out.println("*    Choose your hero!    *\n" +
                    "*    1. Rogue             *\n" +
                    "*    2. Warrior           *\n" +
                    "*    3. Mage              *");
            System.out.println("***************************");
            System.out.print("> " );

            choice = input.nextLine();
            hero.choiceHero(choice);
        }

        while(round <= 49){
            if(position != 0 && round > 1){
                String[] keytypes = new String[]{"Wind", "Fire", "Ocean", "Dirt"};
                Random rand = new Random();
                int number = rand.nextInt(4);
                if (position != position2){
                    if (keytypes[number] == "Ocean"){
                        keys.setOceankeys(keys.getOceankeys()+1);
                    }else if(keytypes[number] == "Wind"){
                        keys.setWindkeys(keys.getWindkeys()+1);
                    }else if (keytypes[number] == "Fire"){
                        keys.setFirekeys(keys.getFirekeys()+1);
                    }else{
                        keys.setDirtkeys(keys.getDirtkeys()+1);
                    }
                    System.out.println("You found a key with the " + keytypes[number]+ " element!");
                }

            }
            input.nextLine();
            position2 = position;
            System.out.println("Round: " + round);
            System.out.println("Score: " + score.getScore());
            if(rooms[position].getMonster()!= null){
                if ( rooms[position].getMonster() == minotaur) {
                    int positionPrev = position;
                    int positionCombat = position;
                    position=Combat.combatMethod(hero, minotaur, playerInventory, score, keyboard, position, round, chest1, chest2,chest3, list);
                    if (positionPrev == position +7 || positionPrev == position -7){
                        round-=1;
                    }
                    if(positionCombat == position && minotaur.getHp() <=0) {
                        round -=1;
                        rooms[position].setMonster(null);
                    }
                }
                else if (rooms[position].getMonster() == typhone) {
                    int positionPrev = position;
                    int positionCombat = position;

                    position=Combat.combatMethod(hero, typhone, playerInventory, score, keyboard, position, round, chest1, chest2, chest3, list);
                    if (positionPrev == position + 7 || positionPrev == position - 7){
                        round-=1;
                    }
                    if(positionCombat == position && typhone.getHp() <=0) {
                        round -=1;
                        rooms[position].setMonster(null);
                    }
                } else {
                    int positionPrev = position;
                    int positionCombat = position;

                    position=Combat.combatMethod(hero, wampa, playerInventory, score, keyboard, position, round, chest1, chest2, chest3, list);
                    if (positionPrev == position +7 || positionPrev == position - 7){
                        round-=1;
                    }
                    if(positionCombat == position && wampa.getHp() <=0) {
                        round -=1;
                        rooms[position].setMonster(null);
                    }
                }

            }else if(rooms[position].getTreasure()!= null){
                playerInventory = Treasure.openTreasure(rooms,position,rooms[position].getTreasure(), playerInventory, score);
                round -= 1;
            }else {
                position=Map.move(position, hero, rooms, keyboard,playerInventory, keys, wampa, typhone, minotaur, score, round, chest1, chest2, chest3);
            }
            round++;
        }
        if (round > 49) {
            if (score.getScore() > highscore) {
                Writer fileWriter = new FileWriter("HighScore", false);
                int finalScore = score.getScore();
                fileWriter.write(finalScore + "");
                fileWriter.close();

                System.out.println("Your final score is: " + finalScore);
                System.out.println("You have now the High Score!");
            } else {
                System.out.println("Your final score is: " + score.getScore() + "\nWell Played!");
            }
        }
    }
}




