package networkağ;

import java.util.*;
import java.util.Scanner;

public class SocialMediaNetwork {

    public static void main(String[] args) {
        boolean isThereUser = false;
        Scanner keyb = new Scanner(System.in);
        int choice = 0;
        socailNetwork network = new socailNetwork();

        while (choice != 11) {
            // 3 - 5 - 9 

            System.out.println("---------------------------------");
            System.out.println("\nMenu : \n"
                    +"1) Add User\n"
                    + "2) Add Friend\n"
                    + "3) Find Mutual Friends\n"
                    + "4) Post \n"
                    + "5) View Feed\n"
                    + "6) View Activity\n"
                    + "7) Search User\n"
                    + "8) Friend Recommendations\n"
                    + "9) Remove Friendship\n"
                    + "10) Display Friendship Connections\n"
                    + "11) Exit");
            System.out.println("---------------------------------");
            System.out.print("Choose an option: ");
            choice = keyb.nextInt();

            switch (choice) {
                case 1: // Add User
                    System.out.print("Enter your ID: ");
                    int id = keyb.nextInt();
                    System.out.print("Enter your name: ");
                    String name = keyb.next();
                    new User(id, name);
                    System.out.println("User created successfully");
                    isThereUser = true;
                    break;

                case 2: // Add Friend
                    if (isThereUser) {
                        System.out.print("Enter your name: ");
                        String userName = keyb.next();
                        System.out.print("Enter a name for friendship: ");
                        String friendName = keyb.next();
                        User current = User.currentUser(userName);
                        User friend = User.wantedUser(friendName);
                        if (current != null && friend != null) {
                            current.addConnection(friend);
                            friend.addConnection(current); // Bidirectional connection
                            System.out.println("Friendship created successfully");
                        } else {
                            System.out.println("One or both users not found");
                        }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 3: // Find Mutual Friends
                    if (isThereUser) {
                     System.out.print("Enter the name of the first user: ");
                          String user1Name=keyb.next();
                          keyb.nextLine();
                     System.out.print("Enter the name of the second user: ");
                         String user2Name =keyb.nextLine();

                    User user1 = User.findUserByName(user1Name);
                    User user2 = User.findUserByName(user2Name);

                    if (user1 != null && user2 != null) {
                        List<User> mutualFriends = network.findMutualFriends(user1, user2);
                        System.out.println("Mutual Friends: " + mutualFriends);
                    } else {
                        System.out.println("One or both users not found.");
                    }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 4: // Post
                    if (isThereUser) {
                        System.out.print("Enter your name: ");
                        String userName = keyb.next();
                        User current = User.currentUser(userName);
                        if (current != null) {
                            System.out.print("Write content for the post: ");
                            keyb.nextLine(); // Consume newline
                            String content = keyb.nextLine();
                            Post post = new Post(content, current);
                            current.addPosts(post);
                        } else {
                            System.out.println("User not found");
                        }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 5: // View Feed
                    if (isThereUser) {
                       System.out.print("Enter your username: ");
                    name = keyb.nextLine();
                    User feedUser = User.findUserByName(name);

                    if (feedUser != null) {
                        feedUser.viewFeed();
                    } else {
                        System.out.println("User not found.");
                    }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 6: // View Activity
                    if (isThereUser) {
                        System.out.print("Enter your name: ");
                        String userName = keyb.next();
                        User current = User.currentUser(userName);
                        if (current != null) {
                            current.viewAllFeed();
                        } else {
                            System.out.println("User not found");
                        }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 7: // Search User
                    if (isThereUser) {
                        System.out.print("Enter your name: ");
                        String userName = keyb.next();
                        User current = User.currentUser(userName);
                        if (current != null) {
                            System.out.print("Enter the name you want to search for: ");
                            String searchedName = keyb.next();
                            current.searchUser(searchedName);
                        } else {
                            System.out.println("Your profile is not found");
                        }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 8: // Friend Recommendations
                    if (isThereUser) {
                        System.out.print("Enter your name: ");
                        String userName = keyb.next();
                        User current = User.currentUser(userName);
                        if (current != null) {
                            System.out.println(network.suggestionFriends(current));
                        } else {
                            System.out.println("User not found");
                        }
                    } else {
                        System.out.println("Firstly, you have to create a user");
                    }
                    break;

                case 9: // Delete Friendships
                    if (isThereUser) {
                        System.out.print("Enter the name of the first user: ");
                    String user1Name = keyb.next();
                    keyb.nextLine();
                    User user1 = User.findUserByName(user1Name);

                    System.out.print("Enter the name of the second user: ");
                    String user2Name = keyb.nextLine();
                    User user2 = User.findUserByName(user2Name);

                    if (user1 != null && user2 != null) {
                        network.removeFriendship(user1, user2);
                    } else {
                        System.out.println("One or both users not found.");
                    }
                    } else {
                        System.out.println("Firstly, you have to create a user.");
                    }
                    break;    
                case 10:
                    if (isThereUser) {
                     System.out.print("Enter your username: ");
                    name = keyb.next();
                    keyb.nextLine();
                    User displayUser = User.findUserByName(name);

                    if (displayUser != null) {
                        System.out.println("Friend List:");
                        displayUser.displayFriendship();
                    } else {
                        System.out.println("User not found.");
                    }
                    }else {
                        System.out.println("Firstly, you have to create a user.");
                    }
                  
                    break;
                case 11: // Exit
                    System.out.println("The system is over.");
                    break;

                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }
}
