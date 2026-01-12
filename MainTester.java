package MacEachern_Batres_Llamas_Repo;

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
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is your last name? (Please enter in the following format: Batres): ");
        String name = sc.nextLine();

        System.out.print("Enter your grade (9-12) or 0 if faculty: ");
        int grade = sc.nextInt();
        sc.nextLine();

        Spoons game = null;
        try {
            game = new Spoons("/Users/vbatres/Desktop/CS_Seminar/MacEachern_Batres_Llamas_Repo/Spoon_Sample_Data - Sheet1.csv");
        } catch (Exception exception) {
            System.out.println("Error!: " + exception.getMessage());
            return;
        }

        int status = game.getStatus(name);
        if (status != 0) {
            System.out.println("Sorry, you have been eliminated!");
            return;
        }

        System.out.println("Welcome " + name + "! You are still in the game.");

        System.out.println("Choose an action:");
        System.out.println("1. Self-report elimination");
        System.out.println("2. Report someone else as eliminated");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("You reported yourself as eliminated.");
            // Update Spoons status
            game.statusMap.put(name, 1); // 1 = eliminated
            System.out.println("Your status is now eliminated.");
        } 
        else if (choice == 2) {
            System.out.print("Enter the name of the person you eliminated: ");
            String target = sc.nextLine();
        
            // Check if target exists
            if (game.statusMap.containsKey(target) && game.getStatus(target) == 0) {
                game.statusMap.put(target, 1); // mark as eliminated
                System.out.println(target + " has been eliminated.");
            } else {
                System.out.println("Invalid target or already eliminated.");
            }
        } 
        else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
