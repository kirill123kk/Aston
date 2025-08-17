
import dao.UserDao;
import dto.CreateUserDto;
import entity.UserEntity;
import mapper.UserMapper;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(new UserDao(), new UserMapper());

        while (true) {

            System.out.println("\n1. Create\n2. Read\n3. Update\n4. Delete\n5. List All\n0. Exit");

            switch (scanner.nextInt()) {
                case 1 -> {
                    CreateUserDto user = new CreateUserDto();
                    System.out.print("Name: "); user.setName(scanner.next());
                    System.out.print("Email: "); user.setEmail(scanner.next());
                    System.out.print("Age: "); user.setAge(scanner.nextInt());
                    userService.save(user);
                }
                case 2 -> {
                    System.out.print("ID: ");
                    UserEntity user = userService.get(scanner.nextLong());
                    System.out.println(user);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    System.out.print("New Name: ");
                    userService.update(scanner.nextLong(),scanner.next());
                }
                case 4 -> {
                    System.out.print("ID: ");
                    userService.delete(scanner.nextLong());
                }
                case 5 -> userService.getAll().forEach(System.out::println);
                case 0 -> System.exit(0);
            }
        }
    }
}
