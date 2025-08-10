import dao.Dao;
import entity.UserEntity;

import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);
        Dao dao = new Dao();

        while (true) {

            System.out.println("\n1. Create\n2. Read\n3. Update\n4. Delete\n5. List All\n0. Exit");

            switch (scanner.nextInt()) {
                case 1 -> {
                    UserEntity user = new UserEntity();
                    System.out.print("Name: "); user.setName(scanner.next());
                    System.out.print("Email: "); user.setEmail(scanner.next());
                    System.out.print("Age: "); user.setAge(scanner.nextInt());
                    dao.save(user);
                }
                case 2 -> {
                    System.out.print("ID: ");
                    UserEntity user = dao.get(scanner.nextLong());
                    System.out.println(user);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    UserEntity user = dao.get(id);
                    if (user != null) {
                        System.out.print("New Name: "); user.setName(scanner.next());
                        dao.update(user);
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    dao.delete(scanner.nextLong());
                }
                case 5 -> dao.getAll().forEach(System.out::println);
                case 0 -> System.exit(0);
            }
        }
    }
}
