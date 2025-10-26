package vn.fpt.coursesupport.prm.contentprovider.libraryservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryService {
    private String user1;
    private String user2;
    private String[] columns;
    private List<String> userList;
    public static LibraryService INSTANCE = new LibraryService();

    private LibraryService() {
        user1 = "Tôn Hành Giả";
        user2 = "Giả Hành Tôn";

        columns = new String[]{"_id", "name"};
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    public String[] getColumns() {
        return columns;
    }

    public List<String> getAllUsers() {
        return userList;
    }

    public boolean createUser(String name) {
        if (!userList.contains(name)) {
            userList.add(name);
            return true;
        }
        return false;
    }
    public String getUserById(int id) {
        return userList.get(id-1);
    }

    public void updateUser(int id, String name) {
        userList.set(id-1, name);
    }

    public void deleteUser(int id) {
        userList.remove(id-1);
    }
}
