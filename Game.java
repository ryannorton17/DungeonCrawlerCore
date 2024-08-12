import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        // Import Scanner for user decisions
        Scanner scanner = new Scanner(System.in);

        // Import Random to generate random numbers
        Random random = new Random();

        // Game Variables
        String[] enemies = {"skeleton", "zombie", "bandit",};
        int minEnemyHealth = 10;
        int maxEnemyHealth = 50;
        int maxEnemyDamage = 15;
        int healthPotionDropChance = 25; // number is percentage

        // Player Variables
        int playerHealth = 200;
        int maxPlayerDamage = 26; // add one for randomizer function to work properly
        int numHealthPotions = 3; // potions in inventory
        int healthPotionHealing = 50;
        int numOfEnemiesDefeated = 0;

        boolean running = true; // keeps game running until boolean becomes false.

        System.out.println("######################################");
        System.out.println("\n");
        System.out.println("#     WELCOME TO DUNGEON CRAWLER     #");
        System.out.println("\n");
        System.out.println("######################################");

        GAME:
        while(running) {
            System.out.println("----------------------------------------------");

            int enemyHealth = random.nextInt(minEnemyHealth, maxEnemyHealth);
            String enemy = enemies[random.nextInt(enemies.length)];

            System.out.println("\t# A " + enemy + " is blocking the path! #\n"); // '\t' tabs forward and '\n' creates new line

            // Combat Loop
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + " HP: " + enemyHealth);
                System.out.println("\n\t What would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Heal");
                System.out.println("\t3. Run!");

                int input = scanner.nextInt();
                if (input == 1) { // attack
                    int damageDealt = random.nextInt(0, maxPlayerDamage);
                    int damageReceived = random.nextInt(0, maxEnemyDamage);
                    enemyHealth -= damageDealt;
                    playerHealth -= damageReceived;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t> The " + enemy + " strikes you for " + damageReceived + " damage.");

                    if (playerHealth < 1) {
                        System.out.println("\t You have taken too much damage! You must return to camp.");
                        break;
                    }
                }
                else if (input == 2) {
                    if (numHealthPotions > 0) {
                        playerHealth += healthPotionHealing;
                        numHealthPotions--;
                        System.out.println("\tThe health potion healed you for " + healthPotionHealing + " HP. "
                        + "\n\tyou now have " + playerHealth + " HP."
                        + "\n\tyou have " + numHealthPotions + " health potions remaining.");
                    }
                    else {
                        System.out.println("\tYou don't have any health potions!");
                    }
                }
                else if (input == 3) {
                    System.out.println("\tYou run for your life! Your camp provides a breath of fresh air.");
                    continue GAME;
                }
                else{
                    System.out.println("\tInvalid choice. Please make a valid selection.");
                }

            }
            if (playerHealth < 1) {
                System.out.println("\tYou barely escaped the dungeon with your life.");
                break;
            }

            System.out.println("----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # " );
            numOfEnemiesDefeated++;
            System.out.println(" # You have " + playerHealth + " HP left.");
            if (random.nextInt(100) <= healthPotionDropChance) {
                numHealthPotions++;
                System.out.println("# The " + enemy + " dropped a health potion! " +
                        "\n# You now have " + numHealthPotions + " health potions. ");
            }
            System.out.println("----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue");
            System.out.println("2. Return to camp");

            int input = scanner.nextInt();

            while (input != 1 || input != 2) {
                System.out.println("\tInvalid choice. Please make a valid selection.");
                input = scanner.nextInt();

            }

            if (input == 1) {
                System.out.println("You continue deeper into the dungeon.");
            }
            else if (input == 2) {
                System.out.println("You return victoriously back to camp. ");
                System.out.println("Well done! you defeated " + numOfEnemiesDefeated + " enemies!");
                break;
            }

            System.out.println("######################################");
            System.out.println("\n");
            System.out.println("# THANKS FOR PLAYING DUNGEON CRAWLER #");
            System.out.println("\n");
            System.out.println("######################################");

        }

    }
}
