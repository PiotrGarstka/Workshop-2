package warsztaty_2.SchoolOfProgramming.Admin;

import warsztaty_2.SchoolOfProgramming.Model.Group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GroupAdmin {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String urlCinemas = "jdbc:mysql://localhost:3306/warsztaty2?useSSL=false";
        String user = "root";
        String password = "coderslab";
        Scanner sn = new Scanner(System.in);
        while (true) {
            try (
                    Connection conn = DriverManager.getConnection(urlCinemas, user, password);
                    Statement stmt = (Statement) conn.createStatement()) {

                for(int i = 0; i<(Group.loadAllGroups(conn)).length; i++){
                    System.out.println(
                            ((Group.loadAllGroups(conn))[i]).getId()+" "
                                    +((Group.loadAllGroups(conn))[i]).getName()+" "
                    );
                }

                System.out.print("\n");

                Group group =new Group();
                String userInput;

                System.out.println(".*****Wybierz jedną z poniższych opcji*****");
                System.out.println("*. Wybierz dodawanie grupy \"add\"");
                System.out.println("*. Wybierz edycja \"edit\"");
                System.out.println("*. Wybierz usunięcie \"delete\"");
                System.out.println("*. Wybierz wyjscie z programu \"quit\"");
                System.out.println("Wprowadz komendę:");

                userInput = sn.next();
                switch (userInput) {

                    case "add":

                        System.out.println("Podaj nazwę: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        group.setName(scan.next());

                        group.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "edit":
                        System.out.println("Podaj id: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        group.setId(scan.nextInt());

                        System.out.println("Podaj nazwę: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        group.setName(scan.next());

                        group.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "delete":
                        System.out.println("Podaj id: ");

                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:");
                        }
                        group.setId(scan.nextInt());
                        group.delete(conn);

                        System.out.println("\n");
                        break;

                    case "quit":
                        //exit from the program
                        System.out.println("Program zamknięty");
                        System.exit(0);
                        System.out.println("\n");
                    default:
                        //inform user in case of invalid choice.
                        System.out.println("Niepoprawny wybór. Wprowadz polecenie jeszcze raz...");
                        System.out.println("\n");
                }
            } catch(
                    SQLException e){
                e.printStackTrace();
            }
        }
    }
}
