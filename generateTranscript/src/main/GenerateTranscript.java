package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenerateTranscript {

    public void takeInputFromUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student Id: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        Transcript transcript = new Transcript(studentId);
        while (true){
            System.out.println("Enter Department: ");
            String department = sc.nextLine();
            System.out.println("Enter Course Code: ");
            int courseCode = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Credit: ");
            int credit = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Grade: ");
            double grade = sc.nextDouble();
            sc.nextLine();

            System.out.println("Do you want to continue adding courses?(y/N) ");
            CourseGrade courseGrade = new CourseGrade(department,courseCode,credit);
            courseGrade.setGradeTaken(grade);
            transcript.addCourseTaken(courseGrade);
            if (sc.nextLine().equalsIgnoreCase("n")){
                break;
            }

        }
        System.out.println(transcript);
    }

    public void takeInputFromFile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = sc.nextLine();

        Transcript transcript = null;
        File file = new File("src\\" + fileName);
        if (!file.exists()){
            System.out.println("There is no file with the given name");
        }else {
            try {
                Scanner myreader = new Scanner(file);
                int lineCount = 0;
                while (myreader.hasNextLine()){

                    if (lineCount == 0){
                        int studentId = Integer.parseInt(myreader.nextLine());
                        transcript = new Transcript(studentId);
                    }else {
                        String department = myreader.next();
                        int courseCode = myreader.nextInt();
                        int credit = myreader.nextInt();
                        double grade = Double.parseDouble(myreader.next());
                        CourseGrade courseGrade = new CourseGrade(department,courseCode,credit);
                        courseGrade.setGradeTaken(grade);
                        if (transcript != null) {
                            transcript.addCourseTaken(courseGrade);
                        }
                        
                    }

                    lineCount++;
                }
                System.out.println(transcript);
                myreader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
