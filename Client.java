import java.util.concurrent.atomic.AtomicInteger;

public class Client extends User {
    // Constructor
    Client(String name, String username, String password) {
        super(name, username, password);
    }

    // Function menu for user client
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
            System.out.println("Welcome " + Main.loggedClient.getName());
            System.out.println("Fortune Freelance - Client Menu");
            System.out.println("1. Create New Project");
            System.out.println("2. See all Your Project");
            System.out.println("0. Logout");
            input = Main.inputInteger("Choose your menu: ");
            switch (input) {
                case 1:
                    Main.clearScr();
                    createProject();
                    break;
                case 2:
                    Main.clearScr();
                    getProjectFromClient();
                    break;
                // Logout
                case 0:
                    Main.loggedIn = false;
                    Main.loggedClient = null;
                    return;
                default:
                    System.out.println("Error! Invalid Choice");
                    break;
            }
            Main.enterToContinue();
        }
    }

    // Function for creating a new project
    public static void createProject() {
        String title;
        String description;
        String deadline;
        long fee;

        System.out.print("Enter project title: ");
        title = Main.scanner.nextLine();
        System.out.print("Enter project description: ");
        description = Main.scanner.nextLine();
        fee = Main.inputLong("Enter project fee: ");
        System.out.print("Enter project deadline (DD/MM/YYYY): ");
        deadline = Main.scanner.nextLine();
        Project newProject = new Project(title, description, fee, deadline);
        // Set the client object in newProject with the loggedClient (the client created
        // the project)
        newProject.setClient(Main.loggedClient);
        // Add to the hashmap, key by unique id auto increment because we dont want two
        // project with same id, and the value is the newProject object.
        Main.project.put(Main.uniqueId.getAndIncrement(),
                newProject);
        System.out.println("Project added! Waiting for freelancer take your project!");
    }

    // Function for showing all this client's project
    public static void getProjectFromClient() {
        // Because we cant simply iterate integer in for each because integers are
        // immutable and for each is a lambda function so we use AtomicInteger object,
        // to numbering the list of the project from this client for better user
        // experience.
        AtomicInteger iterate = new AtomicInteger(1);
        AtomicInteger count = new AtomicInteger(0);

        // We iterate through hashmap project, if the freelancer is the same
        // as logged in freelancer, we increment the count
        Main.project.forEach((key, project) -> {
            if (project.getClient() == Main.loggedClient) {
                count.getAndIncrement();
            }
        });

        System.out.println("List all Project for client " + Main.loggedClient.getName() + " ("
                + Main.loggedClient.getUsername() + ")");
        if (count.get() != 0) {
            Main.project.forEach((key, project) -> {
                // Because of less cardinality in the making of this program, we set the value
                // client and freelancer in the object project, not the freelancer and client
                // has the project. Its because when to deleting, we need to do a lot of
                // operations, deleting in the hashmap project, then we need to find one by one
                // and delete the same ID from the project through the freelancer and client
                // object, so i decided to place the freelancer and client object in the
                // project. So if we want to see this client's project, we just iterate through
                // all the hashmap and see if the Object client is the same with the
                // loggedClient one.
                if (project.getClient() == Main.loggedClient) {
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
