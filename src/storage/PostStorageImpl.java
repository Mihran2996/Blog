package storage;

import model.Post;
import model.PostNotFoundException;

public class PostStorageImpl {

        private Post[] post = new Post[10];
    private int size = 0;

    public void addPost(Post value) {
        if (size == post.length) {
            extend();
        }
        post[size++] = value;
    }

    private void extend() {
        Post[] post1 = new Post[post.length + 10];
        System.arraycopy(post, 0, post1, 0, post.length);
        post = post1;

    }

    //վերադարձնում ենք Գրառումը ըստ տրված վերնագրի
    public Post getPostByTitle(String title) throws PostNotFoundException {

        for (int i = 0; i < size; i++) {
            if (post[i].getTitle().equals(title)) {
                throw new PostNotFoundException("Post with " + title + " already exists `" + post[i]);
            }
        }
        return null;
    }

    //տպում է գրառումը եթե տրված keyword բառը պարունակում է կամ վերնագրի մեջ, կամ տեքստ-ի։(String-ը ունի contains մեթոդը, իրանով երեք)
    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (post[i].getText().contains(keyword) || post[i].getTitle().contains(keyword)) {
                System.out.println(post[i].getText());
            } else {
                System.out.println("Incorrect value! Please try again");
            }
        }
    }

    // տպում է բոլոր գրառումները
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(post[i]);
        }

    }

    //տպում է գրառումները ըստ կատեգորիայի։
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (category.equals(post[i].getCategory())) {
                System.out.println(post[i].getText());
            } else {
                System.out.println("Incorrect value! Please try again");
            }
        }

    }

    public boolean empty() {
        return size == 0;
    }
}
