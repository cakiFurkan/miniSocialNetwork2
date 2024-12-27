package networkağ;

import java.util.*;

public class User {
    
    private int id;
    private String name;
    private Hashtable<Integer, User> connections;
    private LinkedList<Post> posts;
    private Stack<Activity> activities = new Stack<>();

    // Static field to track all users
    private static Hashtable<String, User> users = new Hashtable<>();
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        posts = new LinkedList<>();
        connections = new Hashtable<>();
        // Add this user to the global user list
        addUserToNetwork(this);
    }

    // Method to add this user to the global network
    public static void addUserToNetwork(User user) {
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
        }
    }
    
    public void addPosts(Post post) {
        posts.add(post);
        activities.push(post);
        System.out.println(post.toString());
    } // for adding posts

    public void removePosts(Post post) {
        posts.remove(post);
    } // for removing posts

    public void addActivity(Activity a) {
        activities.push(a);
    }
    
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
    
    public void addConnection(User user) {
        connections.put(user.getId(), user);
    }
    
    public void removeConnection(int userId) {
        connections.remove(userId);
    }
    
    public static User findUserByName(String name) {
        return users.get(name);
    }
    
    public void searchUser(String name) {
        if (users.isEmpty()) {
            System.out.println("There is no user.");
        } else if (users.containsKey(name)) {
            System.out.println("The user was found.\n" + users.get(name).toString());
        } else {
            System.out.println("There is no such a user named " + name);
        }
    }
    
    public void displayFriendship() {
        for (User u : connections.values()) {
            System.out.println(u.toString());
        }
    }
    
    public void viewFeed() {
        for (User user : connections.values()) {
            for (int j = 0; j < user.getConnections().size(); j++) {
                System.out.println(user.getConnections().get(j).toString());
            }
        }
    }
    
    public void viewAllFeed() {
        for (int i = activities.size() - 1; i >= 0; i--) {
            System.out.println(activities.get(i));
        }
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public LinkedList<User> getConnections() {
        return new LinkedList<>(connections.values());
    }
    
    public static User currentUser(String name) {
        return users.get(name);
    }
    
    public static User wantedUser(String name) {
        return users.get(name);
    }
}
