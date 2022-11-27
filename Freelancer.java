import java.util.concurrent.atomic.AtomicInteger;

public class Freelancer extends User {
    // Constructor
    Freelancer(String name, String username, String password) {
        super(name, username, password);
    }

    // Function menu for user freelancer
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
            System.out.println("Welcome " + Main.loggedFreelancer.getName());
            System.out.println("Fortune Freelance - Freelancer Menu");
            System.out.println("1. Apply for available Project");
            System.out.println("2. See all Your Project");
            System.out.println("0. Logout");
            input = Main.inputInteger("Choose your menu: ");
            switch (input) {
                case 1:
                    Main.clearScr();
                    signProject();
                    break;
                case 2:
                    Main.clearScr();
                    getProjectFromFreelancer();
                    break;
                // Logout
                case 0:
                    Main.loggedIn = false;
                    Main.loggedFreelancer = null;
                    return;
                default:
                    System.out.println("Invalid Menu! Please try again!");
                    break;
            }
            Main.enterToContinue();
        }
    }

    // Function for freelancer take a project
    public static void signProject() {
        Main.showAllAvailableProject();
        System.out.println("0. Cancel");
        int input = Main.inputInteger("Input ID Project: ");
        if (input == 0)
            return;
        // Find the project in the hashmap based on the key value project ID
        Project getProject = Main.project.get(input);
        // If project ID is incorrect, the project is null
        // tell the user the project ID is invalid
        if (getProject == null) {
            System.out.println("Invalid ID Project, please try again!");
            return;
        } else {
            // If the project is found, but there is already a freelancer taken the project
            // tell the user they cant take this project
            if (getProject.getFreelancer() != null) {
                System.out.println("You cant take this project, another freelancer already taken this project!");
                return;
            } else {
                // If the projcet found and there is no freelancer
                // because its pass by reference, we can set the freelancer object
                // at the getProject object with the loggedFreelancer object (the freelancer
                // that logged in and take the project)
                getProject.setFreelancer(Main.loggedFreelancer);
                System.out.println("You take the project!");
            }
        }
    }

    // Function for showing all this freelancer's project
    public static void getProjectFromFreelancer() {
        // Because we cant simply iterate integer in for each because integers are
        // immutable and for each is a lambda function so we use AtomicInteger object,
        // to numbering the list of the project from this freelancer for better user
        // experience.
        AtomicInteger iterate = new AtomicInteger(1);
        AtomicInteger count = new AtomicInteger(0);

        // We iterate through hashmap project, if the freelancer is the same
        // as logged in freelancer, we increment the count
        Main.project.forEach((key, project) -> {
            if (project.getFreelancer() == Main.loggedFreelancer) {
                count.getAndIncrement();
            }
        });

        System.out.println("List all Project for freelancer " + Main.loggedFreelancer.getName() + " ("
                + Main.loggedFreelancer.getUsername() + ")");
        if (count.get() != 0) {
            // Because of less cardinality in the making of this program, we set the value
            // client and freelancer in the object project, not the freelancer and client
            // has the project. Its because when to deleting, we need to do a lot of
            // operations, deleting in the hashmap project, then we need to find one by one
            // and delete the same ID from the project through the freelancer and client
            // object, so i decided to place the freelancer and client object in the
            // project. So if we want to see this freelancer's project, we just iterate
            // through all the hashmap and see if the Object freelancer is the same with the
            // loggedFreelancer one.
            Main.project.forEach((key, project) -> {
                if (project.getFreelancer() == Main.loggedFreelancer) {
                    System.out.println(
                            iterate.getAndIncrement() + ". " + project.getTitle() + " (" + project.getDescription()
                                    + ")");
                    System.out.println("   Fee: Rp" + project.getFee() + " (" + project.getDeadline() + ")");
                    System.out.println("   Deadline: " + project.getDeadline());
                    System.out.println("   ID Project = " + key);
                    if (project.getFreelancer() == null) {
                        System.out.println("   Freelancer status: No Freelancer taken this project yet.");
                    } else {
                        System.out.println("   Freelancer status: " + project.getFreelancer().getName() + " ("
                                + project.getFreelancer().getUsername() + ")");
                    }
                }
            });
        } else {
            System.out.println("You didnt have any project!");
        }
    }

}
