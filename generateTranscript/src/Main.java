import main.CourseGrade;
import main.Transcript;
import util.Grade;

public class Main {
    public static void main(String[] args) {


        Transcript transcript = new Transcript(1234234);

        CourseGrade courseGrade = new CourseGrade("c",100,4, Grade.A);
        CourseGrade courseGrade1 = new CourseGrade("CENG",200,3, Grade.B);
        CourseGrade courseGrade2 = new CourseGrade("MATH",600,5, Grade.A);
        CourseGrade courseGrade3 = new CourseGrade("ME",400,4, Grade.F);



        transcript.addCourseTaken(courseGrade);
        transcript.addCourseTaken(courseGrade1);
        transcript.addCourseTaken(courseGrade2);
        transcript.addCourseTaken(courseGrade3);

        System.out.println(transcript);
    }
}