package warsztaty_2.SchoolOfProgramming.Admin;

import warsztaty_2.SchoolOfProgramming.Model.Exercise;
import warsztaty_2.SchoolOfProgramming.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExerciseAdmin {

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

                for(int i = 0; i<(Exercise.loadAllExercises(conn)).length; i++){
                    System.out.println(
                             ((Exercise.loadAllExercises(conn))[i]).getId()+" "
                            +((Exercise.loadAllExercises(conn))[i]).getTitle()+" "
                            +((Exercise.loadAllExercises(conn))[i]).getDescription()
                    );
                }

                System.out.print("\n");

                Exercise exercise =new Exercise();
                String userInput;

                System.out.println(".*****Wybierz jedną z poniższych opcji*****");
                System.out.println("*. Wybierz dodawanie ćwiczenia \"add\"");
                System.out.println("*. Wybierz edycja \"edit\"");
                System.out.println("*. Wybierz wyjscie z programu \"quit\"");
                System.out.println("Wprowadz komendę:");

                userInput = sn.next();
                switch (userInput) {

                    case "add":

                        System.out.println("Podaj nazwę: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        exercise.setTitle(scan.next());

                        System.out.println("Podaj opis: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        exercise.setDescription(scan.next());

                        exercise.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "edit":
                        System.out.println("Podaj id: ");
                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        exercise.setId(scan.nextInt());

                        System.out.println("Podaj nazwę: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        exercise.setTitle(scan.next());

                        System.out.println("Podaj opis: ");
                        while (!scan.hasNext()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:"); }
                        exercise.setDescription(scan.next());

                        exercise.saveToDB(conn);

                        System.out.println("\n");
                        break;

                    case "delete":
                        System.out.println("Podaj id: ");

                        while (!scan.hasNextInt()) {
                            System.out.print("Nieprawidłowe dane. Podaj jeszcze raz:");
                        }
                        exercise.setId(scan.nextInt());
                        exercise.delete(conn);

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
