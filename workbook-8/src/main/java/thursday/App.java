package thursday;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static CategoryDAO categoryDAO;

    public static void main(String[] args) {
        init(args);

        boolean input = true;
        while (input) {
            System.out.println(" What do you want to do?");
            System.out.println("1) See all categories");
            System.out.println("2) See category by name");
            System.out.println("3) See category by ID");
            System.out.println("0) Exit");
            switch (scanner.nextLine()) {
                case "1":
                    print(categoryDAO.getAll());
                    break;
                case "2":
                    System.out.println("What category name do you want to see?");
                    print(List.of(categoryDAO.getName(scanner.nextLine())));
                    break;
                case "3":
                    System.out.println("What category id do you want to see?");
                    print(List.of(categoryDAO.getId(Integer.parseInt(scanner.nextLine()))));
                    break;
                case "0":
                    System.out.println("Bye!");
                    input = false;
            }
        }
    }

    public static void print(List<Category> categories) {
        for (Category category : categories) {
            System.out.println(category.toString());
        }
    }

    public static void init(String[] args) {
        if (args.length != 2) {
            System.out.println("I need to parameters!");
        }

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        categoryDAO = new CategoryDAO(dataSource);
    }
}
