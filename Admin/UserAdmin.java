package warsztaty_2.SchoolOfProgramming.Admin;

import warsztaty_2.SchoolOfProgramming.Model.User;

import java.sql.*;
import java.util.*;

public class UserAdmin {

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

                for(int i=0; i<(User.loadAllUsers(conn)).length; i++){
                    System.out.println(
                             ((User.loadAllUsers(conn))[i]).getId()+" "
                            +((User.loadAllUsers(conn))[i]).getUsername()+" "
                            +((User.loadAllUsers(conn))[i]).getEmail()+" "
                            +((User.loadAllUsers(conn))[i]).getPassword()+" "
                            +((User.loadAllUsers(conn))[i]).getUserGroupId()+" "
                    );
                }

                System.out.print("\n");

                User newUser =new User();
                String userInput;

                System.out.println(".*****Wybierz jedną z poniższych opcji*****");
                System.out.println("*. Wybierz dodawanie użytkownika \"add\"");
                System.out.println("*. Wybierz edycja \"edit\"");
                System.out.println("*. Wybierz usunięcie \"delete\"");
                System.out.println("*. Wybierz wyjscie z programu \"quit\"");
                System.out.println("Wprowadz komendę:");

                userInput = sn.next();
                switch (userInput) {

                    case "add":

                        System.out.println("Podaj imię: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setUsername(scan.next());

                        System.out.println("Podaj email: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setEmail(scan.next());

                        System.out.println("Podaj hasło: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setPassword(scan.next());

                        System.out.println("Podaj id grupy: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setUserGroupId(scan.nextInt());

                        newUser.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "edit":
                        System.out.println("Podaj id: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setId(scan.nextInt());

                        System.out.println("Podaj imię: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setUsername(scan.next());

                        System.out.println("Podaj email: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setEmail(scan.next());

                        System.out.println("Podaj hasło: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setPassword(scan.next());

                        System.out.println("Podaj id grupy: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        newUser.setUserGroupId(scan.nextInt());

                        newUser.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "delete":
                        System.out.println("Podaj id: ");

                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:");
                        }
                        newUser.setId(scan.nextInt());
                        newUser.delete(conn);

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
