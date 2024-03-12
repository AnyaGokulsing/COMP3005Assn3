COMP 3005 Assignment 3 Question 1 

Purpose:
Implement a PostgreSQL database with the students table and write a Java application using JDBC to connect to a local postgresql server (which has the students table in its schema) to perform CRUD (Create, Read, Update, Delete) operations using the following functions:
 * getAllStudents(): Retrieves and displays all records from the students table.
 * addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
 * updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
 * deleteStudent(student_id): Deletes the record of the student with the specified student_id

Files:
 * studentsDDL - DDL + DML statements to create + insert records into students table
 * pom.xml - Maven packages + dependencies (including jdbc for postgresql connection)

Folders:
 * .idea - IntelliJ IDEA module + project level configuration
 * src/main/java - includes the Main.java class which performs the Database connection to postgres and the required 4 functions 

Author: Anya Gokulsing (Student ID: 101170595)

Links:
Codespace : https://miniature-potato-46x5464j75gfvq5.github.dev/
GitHub URL : https://github.com/AnyaGokulsing/COMP3005Assn3
