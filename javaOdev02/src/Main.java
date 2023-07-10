import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Film yönetim sistemine hoşgeldiniz!");
        String role;
        ArrayList<Platform> platforms = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Film> films = new ArrayList<>();

        while (true){
            System.out.print("Lütfen admin girişi için 1'e, müşteri girişi için 2'ye, " +
                    "Çıkış için farklı bir rakama basınız : ");
            role = sc.nextLine();
            if (role.equals("1")) {
                String input;
                while (true) {
                    System.out.println("Platform ekle : (Kategori eklemeye geçmek için 0'a basın)");
                    input = sc.nextLine();
                    if (input.equals("0")) {
                        break;
                    }
                    platforms.add(new Platform(input));
                }

                while (true) {
                    System.out.println("Kategori ekle : (Film eklemeye geçmek için 0'a basın)");
                    input = sc.nextLine();
                    if (input.equals("0")) {
                        break;
                    }
                    categories.add(new Category(input));

                }

                do {
                    System.out.println("Film ekle :");
                    String filmName,director,time;
                    int year,categoryNumber,platformNumber;
                    double imdb;
                    ArrayList<Category> filmCategories = new ArrayList<>();
                    ArrayList<Platform> filmPlatforms = new ArrayList<>();

                    System.out.print("Film adını giriniz : ");
                    filmName = sc.nextLine();
                    System.out.print("Film yılını giriniz : ");
                    year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Filmin yönetmenini giriniz : ");
                    director = sc.nextLine();
                    System.out.print("Filmin imdb puanını giriniz(x,x olarak) : ");
                    imdb = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Filmin süresini giriniz : ");
                    time = sc.nextLine();



                    while (true) {
                        System.out.println("Film için kategori ekleyiniz : (Eklemeyi bitirmek için 0'a basın)");
                        for (int i = 0; i < categories.size(); i++) {
                            System.out.println((i + 1) + ": " + categories.get(i).getName());
                        }
                        categoryNumber = sc.nextInt();
                        sc.nextLine();
                        if (categoryNumber == 0) {
                            break;
                        }
                        filmCategories.add(categories.get(categoryNumber-1));
                    }


                    while (true) {
                        System.out.println("Film için platform ekleyiniz : (Eklemeyi bitirmek için 0'a basın)");
                        for (int i = 0; i < platforms.size(); i++) {
                            System.out.println((i + 1) + ": " + platforms.get(i).getName());
                        }
                        platformNumber = sc.nextInt();
                        sc.nextLine();
                        if (platformNumber == 0) {
                            break;
                        }
                        filmPlatforms.add(new Platform(platforms.get(platformNumber-1).getName()));
                    }
                    Film film = new Film(filmName,year,director,imdb,filmCategories,time,filmPlatforms);
                    films.add(film);
                    for (Category c:filmCategories
                    ) {
                        c.addFilm(film);
                    }
                    System.out.println("Film eklendi. Rol seçme ekranına geçmek için 0'a basın," +
                            "Film eklemeye devam etmek istiyorsanız 0 harici bir tuşa basın :");

                    input = sc.nextLine();

                }while (!input.equals("0"));

            } else if (role.equals("2")) {
                while (true){
                    if (categories.size() == 0) {
                        System.out.println("Şu an herhangi bir film bulunmamaktadır.");
                        break;
                    }else {
                        System.out.println("Görmek istediğiniz filmler için kategori numarası seçiniz : ");
                        for (int i = 0; i < categories.size(); i++) {
                            System.out.println((i + 1) + ": " + categories.get(i).getName() + "(" +
                                    categories.get(i).getFilmArrayList().size() + " film bulunmakta)");
                        }
                        int selectedCategory = sc.nextInt();
                        sc.nextLine();
                        System.out.println(categories.get(selectedCategory-1).getName() + " Kategorisindeki filmler : ");
                        for (int i = 0; i < categories.get(selectedCategory-1).getFilmArrayList().size(); i++) {
                            System.out.println(categories.get(selectedCategory-1).getFilmArrayList().get(i).getFilmName());

                        }

                        System.out.println("Rol seçme ekranına dönmek için 0'a basın," +
                                "Film kategorilerini görüntülemek için 0 harici bir tuşa basın :");

                        String input = sc.nextLine();
                        if (input.equals("0")) {
                            break;
                        }
                    }
                }
            }else{
                break;
            }
        }

    }

}
