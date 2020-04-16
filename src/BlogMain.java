
import model.Post;
import model.PostNotFoundException;
import storage.PostStorageImpl;

import java.time.LocalDate;
import java.util.Scanner;


public class BlogMain implements Comads {
    static PostStorageImpl dataStorage = new PostStorageImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            comands();
            int comand;
            try{

                comand=Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Please try again");
                comand=-1;
            }
            switch (comand) {
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
        try{

            String str = scanner.nextLine();
            String[] array = str.split(",");
            LocalDate data = LocalDate.now();
            Post post = new Post();
            post.setTitle(array[0]);
            post.setText(array[1]);
            post.setCategory(array[2]);
            post.setCreateDate(data);
            try{
                dataStorage.getPostByTitle(array[0]);
            }catch (PostNotFoundException e){
                post=null;
                System.out.println(e.getMessage());
            }
            if(post!=null){
            dataStorage.addPost(post);
                System.out.println("Post was added");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Incorect value");
            addPost();
        }
    }

    private static void comands() {
        System.out.println("input " + EXIT + " for exit");
        System.out.println("input "+ADD_POST+" for post added");
        System.out.println("input "+SEARCH_POST +" for search post");
        System.out.println("input "+POSTS_BY_CATEGORY+" for search post by category");
        System.out.println("input "+ALL_POSTS+" for print posts");
    }
    private static void serachPost(){
        System.out.println("input keyword");
        String str=scanner.nextLine();
        dataStorage.searchPostsByKeyword(str);
    }
    private static void category(){
        System.out.println("input category ");
        String cat=scanner.nextLine();
        dataStorage.printPostsByCategory(cat);

    }
}
