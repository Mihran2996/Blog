
import model.Post;
import model.PostNotFoundException;
import model.User;
import storage.PostStorageImpl;
import storage.UserStorage;

import java.time.LocalDate;
import java.util.Scanner;


public class BlogMain implements Comads, ComandLogin {
    static PostStorageImpl dataStorage = new PostStorageImpl();
    static Scanner scanner = new Scanner(System.in);
    static UserStorage userStorage = new UserStorage();
    static boolean bool;

    public static void main(String[] args) {
        boolean isRun = true;
        Outer:
        while (isRun) {
            comandLogin();
            int comand;
            try {
                comand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please try again");
                comand = -1;
            }
            switch (comand) {
                case LOGIN:
                    login();
                    Outer1:while (bool) {
                        comands();
                        int com;
                        try {
                            com = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Please try again");
                            com = -1;
                        }
                        switch (com) {
                            case EXIT:
                                isRun = false;
                                System.out.println("Good by");
                                break;
                            case ADD_POST:
                                addPost();
                                break;
                            case SEARCH_POST:
                                serachPost();
                                break;
                            case POSTS_BY_CATEGORY:
                                category();
                                break;
                            case ALL_POSTS:
                                print();
                                break;
                            case LOGOUT:
                                continue Outer;
                            default:
                                System.out.println("Wrong comand!");
                        }
                        continue  Outer1;
                    }
                    break ;
                case REGISTR:
                    registr();
                    break;
                default:
                    System.out.println("Wrong comand!");
            }
        }
    }

    private static void print() {
        dataStorage.printAllPosts();
    }

    private static void addPost() {
        System.out.println("Please input Post` (title,text,category)");
        try {

            String str = scanner.nextLine();
            String[] array = str.split(",");
            LocalDate data = LocalDate.now();
            Post post = new Post();
            post.setTitle(array[0]);
            post.setText(array[1]);
            post.setCategory(array[2]);
            post.setCreateDate(data);
            try {
                dataStorage.getPostByTitle(array[0]);
            } catch (PostNotFoundException e) {
                post = null;
                System.out.println(e.getMessage());
            }
            if (post != null) {
                dataStorage.addPost(post);
                System.out.println("Post was added");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorect value");
            addPost();
        }
    }

    private static void comands() {
        System.out.println("input " + EXIT + " for exit");
        System.out.println("input " + ADD_POST + " for post added");
        System.out.println("input " + SEARCH_POST + " for search post");
        System.out.println("input " + POSTS_BY_CATEGORY + " for search post by category");
        System.out.println("input " + ALL_POSTS + " for print posts");
        System.out.println("input " + LOGOUT + " for exit");
    }

    private static void serachPost() {
        System.out.println("input keyword");
        String str = scanner.nextLine();
        dataStorage.searchPostsByKeyword(str);
    }

    private static void category() {
        System.out.println("input category ");
        String cat = scanner.nextLine();
        dataStorage.printPostsByCategory(cat);

    }

    public static void comandLogin() {
        System.out.println(LOGIN + " LOGIN");
        System.out.println(REGISTR + " REGISTR");
    }

    public static void registr() {
        System.out.println("Please input for user add` (name,surname,email,password)");
        String dataStr = scanner.nextLine();
        String[] split = dataStr.split(",");
        try {
            User user = new User();
            user.setName(split[0]);
            user.setSurname(split[1]);
            user.setEmail(split[2]);
            user.setPassword(split[3]);
            userStorage.add(user);
            System.out.println("User was added");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorect value! Please try agaun");
            registr();
        }
    }

    public static void login() {
        if (userStorage.empty()) {
            System.out.println("User is empty");
            registr();
            return;
        } else {
            System.out.println("Please input email/password");
            System.out.print("email: ");
            String email = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();
            bool = userStorage.check(email, password);
            if (bool) {
                System.out.println("Welcome!");
            } else {
                System.out.println("Incorect value! Please try again");
                login();
            }
        }

    }
}
