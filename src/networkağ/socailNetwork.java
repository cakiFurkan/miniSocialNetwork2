package networkağ;

import java.util.*;

public class socailNetwork {

    public static Map<User, List<User>> network = new HashMap<>();

    public socailNetwork() {
    }

    public static void addNetwork(User user) { //addVertex
        if (!network.containsKey(user)) {
            System.out.println("New user was added to Network.");
            network.put(user, user.getConnections());
        } else {
            System.out.println("User is already in the Network.");
        }
    }

    public void createFriendship(User user1, User user2) { //addEdge
        if (!network.containsKey(user1) || !network.containsKey(user2)) {
            System.out.println("Both of users must be in friendship graph");
        }
        if (network.get(user1).contains(user2)) {
            System.out.println(user1.getName() + " and " + user2.getName() + " are already friend");
        }
        Activity activity1 = new Activity("Friendship has been created between " + user1.getName() + " " + user2.getName(), user1);
        Activity activity2 = new Activity("Friendship has been created between " + user2.getName() + " " + user1.getName(), user2);
        network.get(user1).add(user2);
        user1.addConnection(user2);
        user1.addActivity(activity1);
        network.get(user2).add(user1);
        user2.addConnection(user1);
        user2.addActivity(activity2);
        System.out.println("Friendship has been created.");
    }

    public void removeFriendship(User user1, User user2) {
        if (!network.containsKey(user1) || network.get(user1) == null || !network.get(user1).contains(user2)) {
            System.out.println(user1.getName() + " and " + user2.getName() + " are not friends or do not exist in the network.");
            return;
        }

        // Kullanıcı 1'in arkadaş listesinde kullanıcı 2'yi kaldırıyoruz
        network.get(user1).remove(user2);
        user1.removeConnection(user2.getId());

        // Kullanıcı 2'nin arkadaş listesinde kullanıcı 1'i kaldırıyoruz
        if (network.containsKey(user2) && network.get(user2) != null) {
            network.get(user2).remove(user1);
            user2.removeConnection(user1.getId());
        }

        System.out.println("Friendship successfully removed.");
    }

    public List<User> findMutualFriends(User user1, User user2) {
        List<User> mutualFriends = new LinkedList<>();

        if (!network.containsKey(user1) || !network.containsKey(user2)) {
            System.out.println("Both users must be in the network");
            return Collections.emptyList();
        }

        // Kullanıcıların arkadaş listelerini alıyoruz
        List<User> list1 = network.get(user1);
        List<User> list2 = network.get(user2);

        // Ortak arkadaşları buluyoruz
        for (User user : list1) {
            if (list2.contains(user)) {
                mutualFriends.add(user);
            }
        }

        return mutualFriends;
    }

    public List<User> suggestionFriends(User user) {
        ArrayList<User> sFriends = new ArrayList<>();
        LinkedList<User> friendships = new LinkedList<>(user.getConnections());
        if (user.getConnections().isEmpty()) {
            return Collections.emptyList();
        } else {
            for (User user1 : friendships) {
                for (User user2 : user1.getConnections()) {
                    if (!user2.equals(user) && !user.getConnections().contains(user2)) {
                        sFriends.add(user2);
                    }
                }
            }
        }
        return sFriends;
    }
}
