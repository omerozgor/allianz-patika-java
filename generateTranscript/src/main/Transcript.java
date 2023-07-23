package main;

import java.util.ArrayList;

public class Transcript {

    private int studentId;
    private ArrayList<CourseGrade> courseGradeArrayList;
    public double gpa;

    public Transcript(int studentId) {
        this.studentId = studentId;
        this.gpa = 0.0;
        courseGradeArrayList = new ArrayList<>(){
            // Bu ArrayList'in toString methodunu overrride ederek köşeli istediğim şekilde çıktı aldım.
            @Override public String toString()
            {
                String totalString = "";
                for (CourseGrade item:this) {
                   totalString =  totalString.concat(item.toString() + "\n");

                }
                return totalString;
            }
        };

    }


    public void addCourseTaken(CourseGrade courseGrade){
        if (courseGrade == null) {
            System.out.println("Error! The argument given is null");
        }else {
            courseGradeArrayList.add(courseGrade);
            int sum = 0;
            for (CourseGrade grade:courseGradeArrayList) {
                sum += grade.getGradeTaken().numericValue;
            }
            gpa = (double) sum /courseGradeArrayList.size();
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\n" +
                 courseGradeArrayList +
                "GPA: " + gpa;
    }
}
