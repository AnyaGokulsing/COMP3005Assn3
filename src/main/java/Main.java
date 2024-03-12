import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/* ***
 * TO DO:
 * create the students db
 * add functions for
 * getAllStudents(): Retrieves and displays all records from the students table.
 * addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
 * updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
 * deleteStudent(student_id): Deletes the record of the student with the specified student_id
 * ***/
public class Main {
    static void displayMenu(){
        System.out.println("Please input your choice : ");
        System.out.println("1. getAllStudents(). Retrieves + displays all students in students relation.");
        System.out.println("2. addStudent(first_name, last_name, email, enrollment_date): Inserts new student into students relation.");
        System.out.println("3. updateStudentEmail(student_id, new_email): Updates student's email address for student at student_id.");
        System.out.println("4. deleteStudent(student_id): Deletes student's record for student at student_id.");
        System.out.println("0. exit program.");
    }
    static void deleteStudent(Connection connection, int student_id){
        try {
            //here we prepare query string
            String update_str= ("DELETE  FROM students WHERE student_id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(update_str);
            preparedStatement.setInt(1,student_id);
            //execute delete
            preparedStatement.executeUpdate();
            //here we format the query values
            System.out.println("Deleted this record at "+ student_id );
            System.out.println();

        }
        catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("### deleteStudent() finished :)");
    }
    static void updateStudentEmail(Connection connection, int student_id, String email){
        try {
            //here we prepare query string
            String update_str= ("UPDATE students SET email = ? WHERE student_id = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(update_str);
            preparedStatement.setInt(2,student_id);
            preparedStatement.setString(1,email);
            //execute update
            preparedStatement.executeUpdate();
            //here we format the query values
            System.out.println("Updated this record at "+ student_id +" to the students relation and set email to "+email );
            System.out.println();

        }
        catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("### updateStudentEmail() finished :)");
    }
    static void addStudent(Connection connection, int student_id, String first_name, String last_name, String email, Date enrollment_date){
        try {
            //here we prepare query string
            String update_str= ("INSERT INTO students(student_id, first_name, last_name, email, enrollment_date) VALUES(?,?,?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(update_str);
            preparedStatement.setInt(1,student_id);
            preparedStatement.setString(2,first_name);
            preparedStatement.setString(3,last_name);
            preparedStatement.setString(4,email);
            preparedStatement.setDate(5,enrollment_date);
            //execute addition
            preparedStatement.executeUpdate();
            //here we format the query values
            System.out.println("Added this record to the students relation :" );
            System.out.print(String.format("%1$-" + 10 + "s",student_id));
            System.out.print(String.format("%1$-" + 15 + "s", first_name));
            System.out.print(String.format("%1$-" + 20 + "s", last_name));
            System.out.print(String.format("%1$-" + 20 + "s", email));
            System.out.print(String.format("%1$-" + 20 + "s",enrollment_date));
            System.out.println();

        }
        catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("### addStudent() finished :)");
    }

    static void getAllStudents(Connection connection){
        try {
            //here we execute a query
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT  * FROM students");
            //here we get the query result set
            ResultSet resultSet = statement.getResultSet();
            //here we output query result set
            System.out.println("student_id  first_name     last_name           email                    enrollment_date");
            while(resultSet.next()){
                System.out.print(String.format("%1$-" + 12 + "s",resultSet.getInt("student_id")));
                System.out.print(String.format("%1$-" + 15 + "s", resultSet.getString("first_name")));
                System.out.print(String.format("%1$-" + 20 + "s", resultSet.getString("last_name")));
                System.out.print(String.format("%1$-" + 25 + "s", resultSet.getString("email")));
                System.out.print(String.format("%1$-" + 20 + "s",resultSet.getDate("enrollment_date")));
                System.out.println();
            }
        }
        catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("### getAllStudents() finished :)");
    }
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/COMP3005Assignment1";
        String user = "postgres";
        String password = "anya1234";
        int student_id = 7;
        int temp_student_id=0;
        String first_name = "";
        String last_name = "";
        String email = "";
        String temp ="";//temp string to hold date
        Date enrollment_date = null;
        DateTimeFormatter formatter; //to format the dates
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        int choice = 5;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        //here we add infinite menu
        while (true){
            displayMenu();
            choice= scanner.nextInt();
            if (choice == 0) {
                System.out.println("Thank you. Exiting now :) ");
                break;
            } else if(choice == 1){
                System.out.println("Please find below all students in students relation :");
                getAllStudents(connection);
            } else if(choice == 2){
                scanner.nextLine(); // Consume the newline character
                System.out.println("Please input new student's first name to insert :");
                first_name= scanner.nextLine();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Please input new student's last name to insert :");
                last_name= scanner.nextLine();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Please input new student's email address to insert :");
                email= scanner.nextLine();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Please input new student's enrollment date (yyyy-MM-dd) to insert :");
                temp = scanner.nextLine();
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                enrollment_date = Date.valueOf(LocalDate.parse(temp,formatter));
                addStudent(connection,student_id +1, first_name, last_name, email, enrollment_date);
            } else if(choice == 3){
                System.out.println("Please input student_id of student whose email address you are trying to update :");
                temp_student_id = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Please input student's new email address to update to :");
                email= scanner.nextLine();
                updateStudentEmail(connection,temp_student_id, email);
            } else if(choice == 4) {
                System.out.println("Please input student_id of student whose entry you are trying to delete :");
                temp_student_id = scanner.nextInt();
                deleteStudent(connection, temp_student_id);
            }else{
                System.out.println("Invalid choice. :( ");
            }

        }
        System.out.printf("Finished outputting DB Content!");
    }
}