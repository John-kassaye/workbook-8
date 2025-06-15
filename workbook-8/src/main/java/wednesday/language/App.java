package wednesday.language;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class App {
    private static LanguageDAO languageDAO;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        init(args);

        System.out.println("What do you want to do?");
        System.out.println("1) Get all languages");
        System.out.println("2) Get language by name");
        System.out.println("3) Get language by id");
        System.out.println("4) Add language");
        System.out.println("5) Update language");
        System.out.println("6) Delete language");
        System.out.println("0) Exit");

        boolean input = true;
        while (input) {
            String choice= scanner.nextLine();
            switch (choice) {
                case "1":
                    print(languageDAO.getAll());
                    break;
                case "2":
                    System.out.println("name?");
                    print(List.of(languageDAO.getName(scanner.nextLine())));
                    break;
                case "3":
                    System.out.println("id?");
                    print(List.of(languageDAO.getId(Integer.parseInt(scanner.nextLine()))));
                    break;
                case "4":
                    System.out.println("What language do you want to add?");
                    Language language = new Language();
                    language.setName(scanner.nextLine());
                    languageDAO.add(language);
                    break;
                case "5":
                    System.out.println("What is the id you want to update?");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("What language do you want to change it to?");
                    Language language1 = new Language();
                    language1.setName(scanner.nextLine());
                    languageDAO.update(id,language1);
                case "6":
                    System.out.println("What is the id you want to delete");
                    languageDAO.delete(Integer.parseInt(scanner.nextLine()));
                case "0":
                    System.out.println("Bye!!");
                    input = false;
            }
        }
    }

    public static void print(List<Language> languages){
        for (Language language : languages){
            System.out.println(language.toString());
        }
    }

    public static void init(String[] args){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/Sakila");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        languageDAO = new LanguageDAO(dataSource);
    }
}
