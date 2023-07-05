import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> studentClassMap = new HashMap<>();

        studentClassMap.put("Ali Veli", "1-A");
        studentClassMap.put("Ayşe Yılmaz", "2-B");
        studentClassMap.put("Mehmet Demir", "1-B");
        studentClassMap.put("Fatma Kaya", "3-C");
        studentClassMap.put("Ahmet Yıldız", "2-A");
        studentClassMap.put("Elif Öztürk", "3-B");
        studentClassMap.put("Ali Rahman Yılmaz", "1-A");
        studentClassMap.put("Muhsin Mehmet Keleş", "1-B");
        studentClassMap.put("Mustafa Şahin", "1-C");
        studentClassMap.put("Zeynep Arslan", "2-C");
        studentClassMap.put("Emre Doğan", "3-A");
        studentClassMap.put("İrem Korkmaz", "1-A");
        studentClassMap.put("Selman Kadir Can", "2-B");
        studentClassMap.put("Selin Şen", "3-C");
        studentClassMap.put("Hakan Demir", "1-B");
        studentClassMap.put("Deniz Akgün", "2-A");
        studentClassMap.put("Gizem Tosunoğlu", "3-B");
        studentClassMap.put("Sefa Güneş", "1-C");
        studentClassMap.put("Ebru Akın", "2-C");
        studentClassMap.put("Oğuzhan Gürbüz", "3-A");
        studentClassMap.put("İlayda Özkan", "1-A");
        studentClassMap.put("Berkay Aydın", "2-B");


        for (String student : studentClassMap.keySet()) {
            String className = studentClassMap.get(student);
            String[] names = student.split(" ");

            for (int i = 0; i < names.length; i++) {
                if (i != names.length-1 && names[i].endsWith("an")) {
                    System.out.println(student + " -> " + className);
                }
            }
        }
    }
}