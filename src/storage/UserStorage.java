package storage;

import model.User;

public class UserStorage {
    private User array[] = new User[16];
    private int size;

    public void add(User user) {
        if (size == array.length) {
            extend();
        }
        array[size++] = user;
    }

    private void extend() {
        User tmp[] = new User[array.length + 10];
        System.arraycopy(array, 0, tmp, 0, array.length);
        array = tmp;
    }
    public Boolean check(String email,String password){
        for (int i = 0; i <size ; i++) {
            if(email.equals(array[i].getEmail()) && password.equals(array[i].getPassword())){
                return true;
            }
        }
        return false;
    }
    public boolean empty(){
        return size==0;
    }
}
