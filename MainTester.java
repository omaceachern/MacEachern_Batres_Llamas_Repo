package MacEachern_Batres_Llamas_Repo;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainTester {
    /*asks the user their name and grade
        constructs a student
        also create a spoon object to use
        also automatically gives them their status
        if they are eliminated
            they cant do anything
        if they are not eliminated
            allow them to self report themselves as eliminated
            or report someone else
                eliminated
                    go into spoon and change map
                eliminated someone*
                    go into spoon and change map*/
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        // ask for user info
        System.out.print("What is your last name? (Please enter in the following format: Batres): ");
        String name = sc.nextLine().trim();

        System.out.print("Enter your grade (9-12) or 0 if faculty: ");
        int grade = sc.nextInt();
        sc.nextLine();

        //creates spoons object
        Spoons game = new Spoons("MacEachern_Batres_Llamas_Repo/Spoon_Sample_Data - Sheet1.csv");

        //create a students object for the player
        Students player = new Students(name,grade);

        //check player's status
        int status = game.getStatus(player.getName());

        if (status == 0) {
            System.out.println("Sorry, "+player.getName()+" you have been eliminated!");
            sc.close();
            return;
        }

        System.out.println("Welcome " + player.getName() + "! You are still in the game.");
        
        while (true) {
            //Check if all groups have winners*
            String winner9 = game.getWinnerInGroup(game.getNines());
            String winner10 = game.getWinnerInGroup(game.getTens());
            String winner11 = game.getWinnerInGroup(game.getElevens());
            String winner12 = game.getWinnerInGroup(game.getTwelves());
            String winnerFaculty = game.getWinnerInGroup(game.getFaculty());

            if (winner9 != null && winner10 != null && winner11 != null &&
                winner12 != null && winnerFaculty != null) {
                System.out.println("\nGame Over! Winners for each group:");
                System.out.println("9th Grade: " + winner9);
                System.out.println("10th Grade: " + winner10);
                System.out.println("11th Grade: " + winner11);
                System.out.println("12th Grade: " + winner12);
                System.out.println("Faculty: " + winnerFaculty);
                break; // all groups have winners --> end game
            }

        //show current target
        System.out.println("Your current target is: " + game.getTarget(player.getName()));

        //offer actions
        System.out.println("Choose an action:");
        System.out.println("1. Self-report elimination");
        System.out.println("2. Report someone else as eliminated");

        int choice = sc.nextInt();
        sc.nextLine();

        //Use eliminatePlayer() to update only the correct group
        if (choice == 1) {
            game.eliminatePlayer(player.getName());
            System.out.println(player.getName()+ " has been eliminated.");
            //last player check
            if (game.isLastPlayerInGroup(player.getName())) {
                System.out.println("Congratulations " + player.getName() + "! You are the winner!");
            }
        }
        else if (choice == 2) {
            System.out.print("Enter the name of the person you eliminated: ");
            String target = sc.nextLine().trim();
            if (game.getStatus(target) == 1) {
                game.eliminatePlayer(target);
                System.out.println(target + " has been eliminated.");

                //last player check
                if (game.isLastPlayerInGroup(player.getName())) {
                    System.out.println("Congratulations " + player.getName() + "! You are the winner!");
                }
            } else {
                System.out.println("Invalid target or already eliminated.");
            }
        }
        else {
            System.out.println("Invalid choice.");
        }
    }
    sc.close();
    }
}
