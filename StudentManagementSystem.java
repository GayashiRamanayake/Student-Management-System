import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    static Scanner input = new Scanner(System.in);
    static Student[] student = new Student[100];
    static int maximumStudent = 100;
    static int studentCount;

    public static void main(String[] args) {
        String choice;
        loadStudent();
        do {   // Display menu options
            System.out.println(" ");
            System.out.println("STUDENT ACTIVITY MANAGEMENT SYSTEM");
            System.out.println();
            System.out.println("1. Check available seats: ");
            System.out.println("2. Register student (with student Id): ");
            System.out.println("3. Delete student: ");
            System.out.println("4. Find student with student id: ");
            System.out.println("5. Store student details into a file: ");
            System.out.println("6. Load student details from the file to the system; ");
            System.out.println("7. View the list of students based on their names: ");
            System.out.println("8. Choose an option: ");
            System.out.println("     Choose 'a' to add student name");
            System.out.println("     Choose 'b' to enter student module marks");
            System.out.println("     Choose 'c' to get a summary of total students");
            System.out.println("     Choose 'd' to generate reports of students");
            System.out.println("0. Exit!!");
            System.out.println(" ");
            System.out.print("Enter Your Choice: ");
            choice = input.nextLine(); //reads user choice
            switch (choice) {
                case "1":
                    checkSeats(); // Check available seats
                    break;
                case "2":
                    register(); // Register a new student
                    break;
                case "3":
                    delete(); // Delete a student
                    break;
                case "4":
                    searchStudent(); // Search for a student
                    break;
                case "5":
                    storeStudent(); // Store student details into a file
                    break;
                case "6":
                    loadStudent(); // Load student details from a file
                    break;
                case "7":
                    viewStudent(); // View list of students
                    break;
                case "a":
                    addName(); // Add student name
                    break;
                case "b":
                    moduleMarks(); // Enter student module marks
                    break;
                case "c":
                    generateSummary(); // Generate summary of total students
                    break;
                case "d":
                    studentReport(); // Generate student reports
                    break;
                case "0":
                    System.out.println("Exiting from the system!"); // Exit the program
                    break;
            }
        } while (!choice.equals("0")); // Repeat until user chooses to exit
    }

//    TASK 01

    /**
     * Checks the available seats in the student management system.
     */
    private static void checkSeats() {
        int availableSeat = maximumStudent - studentCount;
        if (availableSeat != 0) {
            System.out.println("No of available seats are " + availableSeat);
        } else {
            System.out.println("There's no available seats");
        }
    }

    /**
     * Registers a new student by taking the student ID and name from the user.
     */
    private static void register() {
        String id;
        String name;
        if (studentCount != 100) {
            while (true) {
                System.out.println("Enter student id: ");
                id = input.nextLine();
                boolean alreadyExist = false;
                for (int i = 0; i < studentCount; i++) {
                    if (student[i].getstudentId().equals(id)) {
                        alreadyExist = true;
                        System.out.println("This student has already registered.");
                        break;
                    }
                }
                if (!alreadyExist) {
                    if (id.length() == 8 && id.charAt(0) == 'w') {
                        System.out.println("Enter student name: ");
                        name = input.nextLine();
                        student[studentCount] = new Student(id, name);
                        studentCount++;
                        System.out.println("Registered successfully");
                        break;
                    } else {
                        System.out.println("Enter a valid Student Id. It must be 8 characters long, starting with 'w' followed by 7 digits.");
                    }
                }
            }
        }
        else{
            System.out.println("Maximum number of students registered");
        }
    }

    /**
     * Deletes a student based on the student ID provided by the user.
     */
    private static void delete() {
        System.out.println("Enter the student Id to delete: ");
        String id = input.nextLine();
        boolean foundStudent = false;
        try {
            for (int i = 0; i <= studentCount; i++) {
                if (student[i].getstudentId().equals(id)) {
                    student[i] = student[studentCount - 1];
                    student[studentCount - 1] = null;
                    studentCount--;
                    System.out.println("Student deleted successfully ");
                    foundStudent = true;
                    if (foundStudent == true) {
                        break;
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("Student not found " + e.getMessage());
        }
    }

    /**
     * Searches for a student based on the student ID provided by the user.
     */
    private static void searchStudent() {
        boolean foundStudent = false;
        System.out.println("Enter student id: ");
        String id = input.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (student[i].getstudentId().equals(id)) {
                System.out.println("Student found at position " + (i + 1));
                System.out.println(student[i].getstudentName());
                foundStudent = true;
            }
        }
        if (!foundStudent) {
            System.out.println("Student not found");
        }
    }

    /**
     * Stores the details of all registered students into a file.
     */
    private static void storeStudent() {
        try {
            FileWriter myFile = new FileWriter("text.txt");
            for (int i = 0; i < studentCount; i++) {
                if (student[i] != null) {
                    if(student[i].getmodule() != null) {
                        myFile.write(student[i].getstudentId() + " " + student[i].getstudentName() + " " + student[i].getmodule().getmodule1() + " " + student[i].getmodule().getmodule2() + " " + student[i].getmodule().getmodule3() + "\n");
                    }
                    else {
                        myFile.write(student[i].getstudentId() + " " + student[i].getstudentName() + " " + 0 + " " + 0 + " " + 0 + "\n");
                    }
                }
                else{
                    System.out.println("There's no student to store");
                }
            }
            myFile.close();
            System.out.println("Stored students successfully");
        } catch (IOException e) {
            System.out.println("An Error occurred");
        } catch (Exception e){
            System.out.println("An error occurred");
        }
    }

    /**
     * Loads the details of students from a file into the system.
     */
    private static void loadStudent() {
        try {
            File myFile = new File("text.txt");
            Scanner file = new Scanner(myFile);
            while (file.hasNextLine()) {
                String data = file.nextLine();
                String[] temp = data.split(" ");
                String tempId = temp[0];
                boolean alreadyExist = false;
                for (int i=0; i<studentCount; i++) {
                    if (student[i].getstudentId().equals(tempId)) {
                        alreadyExist = true;
                        break;
                    }
                }
                if (!alreadyExist) {
                    if (temp.length == 5) {
                        String id = temp[0];
                        String name = temp[1];
                        int module1 = Integer.parseInt(temp[2]);
                        int module2 = Integer.parseInt(temp[3]);
                        int module3 = Integer.parseInt(temp[4]);

                        Module module = new Module(module1, module2, module3);
                        student[studentCount] = new Student(id, name, module);
                        studentCount++;
                    } else if (temp.length == 2) {
                        String id = temp[0];
                        String name = temp[1];

                        student[studentCount] = new Student(id, name);
                        studentCount++;
                    }
                }
            }
            System.out.println("Successfully loaded from the file");
        file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("An error occurred");
        }
    }

    /**
     * Views the list of students based on their names.
     */
    private static void viewStudent() {
        if (studentCount != 0) {
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = 0; j < studentCount - 1 - i; j++) {
                    if ((student[j].compareTo(student[j + 1])) > 0) {
                        Student placeHolder = student[j];
                        student[j] = student[j + 1];
                        student[j + 1] = placeHolder;
                    }
                }
            }
            System.out.println("Sorted Student List");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(student[i].getstudentId() + " " + student[i].getstudentName());
            }
        } else {
            System.out.println("No student has registered");
        }
    }

//    TASK 02

    /**
     * Prompts the user to enter a student ID and name, and updates the student's name.
     */
    public static void addName() {
        try {
            System.out.println("Enter student id to enter name: ");
            String id = input.nextLine();
            boolean foundStudent = false;
            for (int i = 0; i < studentCount; i++) {
                if (student[i].getstudentId().equals(id)) {
                    System.out.println("Enter student's name: ");
                    String newName = input.nextLine();
                    student[i].setStudentName(newName);
                    System.out.println("Student id is " + student[i].getstudentId() + "Student name is " + student[i].getstudentName());
                    foundStudent = true;
                }
            }
            if (!foundStudent) {
                System.out.println("Student not found!!");
            }
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to enter a student ID and marks for three modules, and updates the student's marks.
     */
    private static void moduleMarks() {
        System.out.println("Enter student id to enter marks: ");
        String id = input.nextLine();
        boolean foundStudent = false;
        try {
            for (int i = 0; i < studentCount; i++) {
                if (student[i].getstudentId().equals(id)) {
                    System.out.println("Student name is " + student[i].getstudentName());
                    System.out.println(" ");
                    System.out.println("Enter Module 01 marks: ");
                    int module1 = input.nextInt();
                    System.out.println("Enter Module 02 marks: ");
                    int module2 = input.nextInt();
                    System.out.println("Enter Module 03 marks: ");
                    int module3 = input.nextInt();

                    id = student[i].getstudentId();
                    String name = student[i].getstudentName();
                    Module module = new Module(module1, module2, module3);

                    student[i] = new Student(id, name, module);

                    System.out.println(student[i].getstudentId() + " " + student[i].getstudentName() + " " + student[i].getmodule().getmodule1() + " " + student[i].getmodule().getmodule2() + " " + student[i].getmodule().getmodule3());
                    foundStudent = true;
                }
            }
            if (!foundStudent) {
                System.out.println("Student not found!!");
            }
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

//    TASK 03

    /**
     * Generates and prints a summary of the total number of registered students and the number of students who passed each module.
     */
    private static void generateSummary() {
        try {
            int module1PassedStudents = 0;
            int module2PassedStudents = 0;
            int module3PassedStudents = 0;
            System.out.println("SUMMARY OF TOTAL STUDENTS");
            System.out.println("Total number of registered student: " + studentCount);
            System.out.println(" ");
            for (int i = 0; i < studentCount; i++) {
                if (student[i].getmodule().getmodule1() > 40) {
                    module1PassedStudents++;
                }
                if (student[i].getmodule().getmodule2() > 40) {
                    module2PassedStudents++;
                }
                if (student[i].getmodule().getmodule3() > 40) {
                    module3PassedStudents++;
                }
            }
            System.out.println("Total no of students who have passed Module01 : " + module1PassedStudents);
            System.out.println("Total no of students who have passed Module02 : " + module2PassedStudents);
            System.out.println("Total no of students who have passed Module03 : " + module3PassedStudents);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    /**
     * Generates and prints a report for each student, including their ID, name, marks, total marks, average marks,and grade.
     */
    private static void studentReport(){
        int average;
        int total;
        String grade;
        try {

            for (int i = 0; i < studentCount; i++) {
                int module1 = student[i].getmodule().getmodule1();
                int module2 = student[i].getmodule().getmodule2();
                int module3 = student[i].getmodule().getmodule3();
                total = module1 + module2 + module3;
                average = total / 3;
                student[i].setAverage(average);
                student[i].setTotal(total);
                String name = student[i].getstudentName();
                String id = student[i].getstudentId();
                Module module = student[i].getmodule();

                student[i] = new Student(id, name, module, total, average);
            }
            for (int i = 0; i < studentCount; i++) {
                for (int j = 0; j < studentCount - 1 - i; j++) {
                    if (student[j].getAverage() < student[j + 1].getAverage()) {
                        Student placeHolder = student[j];
                        student[j] = student[j + 1];
                        student[j + 1] = placeHolder;
                    }
                }
            }
            for (int i = 0; i < studentCount; i++) {
                System.out.println("Student id is " + student[i].getstudentId());
                System.out.println("Student name is " + student[i].getstudentName());
                System.out.println(" ");
                System.out.println("Marks for Module 01: " + student[i].getmodule().getmodule1());
                System.out.println("Marks for Module 02: " + student[i].getmodule().getmodule2());
                System.out.println("Marks for Module 03: " + student[i].getmodule().getmodule3());
                System.out.println(" ");
                System.out.println("Total Marks: " + student[i].getTotal());
                System.out.println("Average: " + student[i].getAverage());
                if (student[i].getAverage() >= 80) {
                    grade = "Distinction";
                } else if (student[i].getAverage() >= 70) {
                    grade = "Merit";
                } else if (student[i].getAverage() >= 40) {
                    grade = "Pass";
                } else {
                    grade = "Fail";
                }
                System.out.println("Grade: " + grade);
                System.out.println(" ");
                System.out.println(" ");
            }
        }
        catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}