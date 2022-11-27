import java.util.concurrent.atomic.AtomicInteger;

public class Client extends User {
    Client(String name, String username, String password) {
        super(name, username, password);
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
        newProject.setClient(Main.loggedClient);
        Main.project.put(Main.project.size() + 1,
                newProject);
    }

    // Function for showing all this client's project
    public static void getProjectFromClient() {
        // Because we cant simply iterate integer in for each because integers are
        // immutable and for each is a lambda function so we use AtomicInteger object,
        // to numbering the list of the project from this client for better user
        // experience.
        AtomicInteger iterate = new AtomicInteger(1);
        System.out.println("List all Freelance for client " + Main.loggedClient.getName() + " ("
                + Main.loggedClient.getUsername() + ")");
        if (Main.project.size() != 0) {
            Main.project.forEach((key, project) -> {
                if (project.getClient() == Main.loggedClient) {
                    System.out.println(
                            iterate.getAndIncrement() + ". " + project.getTitle() + " (" + project.getDescription()
                                    + ")");
                    System.out.println("   Fee: Rp" + project.getFee() + " (" + project.getDeadline() + ")");
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
            System.out.println("You didnt have any project");
        }
    }

    // Function menu for user client
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
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
                case 3:
                    System.out.println("test");
                    Main.project.forEach((key, project) -> {
                        System.out
                                .println(project.getTitle() + " nama client " + project.getClient().getName()
                                        + " username client "
                                        + project.getClient().getUsername() + " nama freelancer "
                                        + project.getFreelancer().getName() + " "
                                        + project.getFreelancer().getUsername());
                        if (Main.loggedClient != null) {
                            System.out.println(project.getClient() == Main.loggedClient);
                        }
                        if (Main.loggedFreelancer != null) {
                            System.out.println(project.getFreelancer() == Main.loggedFreelancer);
                        }
                    });
                    Main.enterToContinue();
                    break;
                case 0:
                    Main.loggedIn = false;
                    Main.loggedClient = null;
                    return;
            }
            Main.enterToContinue();
        }
    }

}
