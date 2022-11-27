import java.util.ArrayList;

public class Admin extends User {
    // Constructor
    Admin(String name, String username, String password) {
        super(name, username, password);
    }

    // Function menu for user admin
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
            System.out.println("Welcome " + Main.loggedAdmin.getName());
            System.out.println("Fortune Freelance - Admin Menu");
            System.out.println("1. Delete Freelancer");
            System.out.println("2. Delete Client");
            System.out.println("3. Delete Project");
            System.out.println("0. Logout");
            input = Main.inputInteger("Choose your menu: ");
            switch (input) {
                case 1:
                    Main.clearScr();
                    deleteFreelancer();
                    break;
                case 2:
                    Main.clearScr();
                    deleteClient();
                    break;
                case 3:
                    Main.clearScr();
                    deleteProject();
                    break;
                // Logout
                case 0:
                    Main.loggedIn = false;
                    Main.loggedAdmin = null;
                    return;
                default:
                    System.out.println("Invalid Menu! Please try again!");
                    break;
            }
            Main.enterToContinue();
        }
    }

    // Function for delete a freelancer
    public static void deleteFreelancer() {
        Main.showAllFreelancer();
        String freelancerUsername;
        System.out.println("0. Cancel");
        System.out.println("\nDeleting Freelancer");
        System.out.print("Input Freelancer username: ");
        freelancerUsername = Main.scanner.nextLine();
        if (freelancerUsername.equals("0"))
            return;
        Freelancer freelancerDelete = Main.freelancer.get(freelancerUsername);
        if (freelancerDelete != null) {
            // If the freelancer is founded, loop to all the project
            // if the freelancer on the project, remove the freelancer on that project or
            // make it null
            Main.project.forEach((key, project) -> {
                // Remove if the object freelancer same
                if (project.getFreelancer() == freelancerDelete) {
                    project.setFreelancer(null);
                }
            });
            // Then we delete the freelancer from the hashmap
            Main.freelancer.remove(freelancerUsername);
            System.out.println("Freelancer with username " + freelancerUsername + " deleted.");
        } else {
            System.out.println("Freelancer with username " + freelancerUsername + " is not found.");
            return;
        }
    }

    // Function for delete a client
    public static void deleteClient() {
        Main.showAllClient();
        String clientUsername;
        System.out.println("0. Cancel");
        System.out.println("\nDeleting Client");
        System.out.print("Input Client username: ");
        clientUsername = Main.scanner.nextLine();
        if (clientUsername.equals("0"))
            return;
        Client clientDelete = Main.client.get(clientUsername);
        if (clientDelete != null) {
            // If the client is founded, loop to all the project
            // if the client on the project, remove the project because
            // there cant be project without the client

            // Because we cant delete the object inside the hashmap when we looping the
            // hashmap instead we make new arraylist to store the key of the hashmap that we
            // gonna delete
            ArrayList<Integer> keyDelete = new ArrayList<Integer>();
            Main.project.forEach((key, project) -> {
                if (project.getClient() == clientDelete) {
                    keyDelete.add(key);
                }
            });

            // Then we iterate over the arraylist and delete every value in hashmap that has
            // the key
            for (int key : keyDelete) {
                Main.project.remove(key);
            }

            // Then we delete the client from the hashmap
            Main.client.remove(clientUsername);
            System.out.println("Client with username " + clientUsername + " deleted.");
        } else {
            System.out.println("Client with username " + clientUsername + " is not found.");
            return;
        }
    }

    // Function for delete a project
    public static void deleteProject() {
        Main.showAllProject();
        System.out.println("0. Cancel");
        System.out.println("\nDeleting Project");
        int projectId = Main.inputInteger("Input ID Project: ");
        if (projectId == 0)
            return;
        Project projectDelete = Main.project.get(projectId);
        if (projectDelete != null) {
            // Delete project from the hashmap based on index the ID Project.
            Main.project.remove(projectId);
            System.out.println("Project with ID " + projectId + " deleted.");
        } else {
            System.out.println("Project with ID " + projectId + " is not found.");
            return;
        }
    }
}
