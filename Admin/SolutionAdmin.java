package warsztaty_2.SchoolOfProgramming.Admin;

import warsztaty_2.SchoolOfProgramming.Model.Exercise;
import warsztaty_2.SchoolOfProgramming.Model.Solution;
import warsztaty_2.SchoolOfProgramming.Model.User;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class SolutionAdmin {


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

                System.out.print("\n");

                User newUser =new User();
                Exercise exercise =new Exercise();
                Solution solution=new Solution();
                Date date= new Date();

                String userInput;

                System.out.println(".*****Wybierz jedną z poniższych opcji*****");
                System.out.println("*. Wybierz dodawanie rozwiązań do użytkownika \"add\"");
                System.out.println("*. Wybierz przeglądanie rozwiązań danego użytkownika \"view\"");
                System.out.println("*. Wybierz wyjscie z programu \"quit\"");
                System.out.println("Wprowadz komendę:");

                userInput = sn.next();
                switch (userInput) {

                    case "add":

                        for(int i = 0; i<(User.loadAllUsers(conn)).length; i++){
                            System.out.println(
                                    ((User.loadAllUsers(conn))[i]).getId()+" "
                                            +((User.loadAllUsers(conn))[i]).getUsername()+" "
                                            +((User.loadAllUsers(conn))[i]).getEmail()+" "
                                            +((User.loadAllUsers(conn))[i]).getPassword()+" "
                                            +((User.loadAllUsers(conn))[i]).getUserGroupId()+" "
                            );
                        }

                        System.out.println("Podaj id użytkownika: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        solution.setUsersId(scan.nextInt());

                        for(int i = 0; i<(Exercise.loadAllExercises(conn)).length; i++){
                            System.out.println(
                                    ((Exercise.loadAllExercises(conn))[i]).getId()+" "
                                            +((Exercise.loadAllExercises(conn))[i]).getTitle()+" "
                                            +((Exercise.loadAllExercises(conn))[i]).getDescription()
                            );
                        }

                        System.out.println("Podaj id zadania: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        solution.setExerciseId(scan.nextInt());

                        solution.setCreated((new Timestamp(date.getTime()))+" ");
                        solution.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "view":
                        System.out.println("Podaj id użytkownika: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        solution.setUsersId(scan.nextInt());

                        for(int i = 0; i<(solution.loadAllByUserId(conn)).length; i++){
                            System.out.println(
                                    ((solution.loadAllByUserId(conn))[i]).getId()+" "
                                            +((solution.loadAllByUserId(conn))[i]).getCreated()+" "
                                            +((solution.loadAllByUserId(conn))[i]).getUpdated()+" "
                                            +((solution.loadAllByUserId(conn))[i]).getDescription()+" "
                                            +((solution.loadAllByUserId(conn))[i]).getExerciseId()+" "
                                            +((solution.loadAllByUserId(conn))[i]).getUsersId()+" "
                            );
                        }

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
