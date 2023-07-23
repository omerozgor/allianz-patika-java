package main;

import util.Grade;

public class CourseGrade {

    private String courseDepartment;
    private int courseCode;
    private int courseCredit;
    private Grade gradeTaken;

    public CourseGrade(String courseDepartment) {
        this(courseDepartment,100,4,Grade.F);
    }

    public CourseGrade(String courseDepartment, int courseCode) {
        this(courseDepartment,courseCode,4,Grade.F);
    }

    public CourseGrade(String courseDepartment, int courseCode, int courseCredit) {
        this(courseDepartment,courseCode,courseCredit,Grade.F);
    }

    public CourseGrade(String courseDepartment, int courseCode, int courseCredit, Grade gradeTaken) {
        setCourseDepartment(courseDepartment);
        setCourseCode(courseCode);
        setCourseCredit(courseCredit);
        setGradeTaken(gradeTaken);
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(String courseDepartment) {
        if (courseDepartment.equals("CENG") || courseDepartment.equals("COMP") ||
                courseDepartment.equals("ECE") || courseDepartment.equals("MATH") || courseDepartment.equals("ME")) {
            this.courseDepartment = courseDepartment;
        }else {
            this.courseDepartment = "CENG";
        }

    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        if (courseCode >= 100 && courseCode <= 599) {
            this.courseCode = courseCode;
        }else {
            this.courseCode = 100;
        }

    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        if (courseCredit == 3 || courseCredit == 4) {
            this.courseCredit = courseCredit;
        }else {
            this.courseCredit = 4;
        }

    }

    public Grade getGradeTaken() {
        return gradeTaken;
    }

    public void setGradeTaken(double val) {
        if (val >= 0 && val <= 4) {
            for (Grade grade:Grade.values()) {
                // Alınan parametrenin bir harf notuna ait olması için o harf notunun
                // sayısal değerinden en fazla 0.5 büyük olmalı (0.5 dahil değil) ve
                // en fazla 0.5 küçük olmalı
                if (grade.numericValue - val > -0.5 && grade.numericValue - val <= 0.5){
                    this.gradeTaken = grade;
                    break;
                }
            }
        }else {
            this.gradeTaken = Grade.F;
        }


    }

    public void setGradeTaken(Grade gradeTaken) {
        this.gradeTaken = gradeTaken;
    }

    @Override
    public String toString() {
        return "Department:" + " "
                + courseDepartment  +
                " CourseCode: " + courseCode +
                " Credit: " + courseCredit +
                " Grade: " + gradeTaken.stringValue;
    }
}
