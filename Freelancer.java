import java.util.concurrent.atomic.AtomicInteger;

public class Freelancer extends User {
    Freelancer(String name, String username, String password) {
        super(name, username, password);
    }

    public static void signProject() {
        Main.showAllAvailableProject();
        System.out.println("0. Cancel");
        int input = Main.inputInteger("Input ID Project: ");

        Project getProject = Main.project.get(input);
        if (getProject == null) {
            System.out.println("Invalid ID Project, please try again!");
            return;
        } else {
            if (getProject.getFreelancer() != null) {
                System.out.println("You cant take this project, another freelancer already taken this project!");
                Main.enterToContinue();
                return;
            } else {
                getProject.setFreelancer(Main.loggedFreelancer);
                System.out.println("Berhasil");
                Main.enterToContinue();
            }
        }
    }

    // Function menu for user freelancer
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
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
                    Main.loggedFreelancer = null;
                    return;
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
        System.out.println("List all Freelance for freelancer " + Main.loggedFreelancer.getName() + " ("
                + Main.loggedFreelancer.getUsername() + ")");
        if (Main.project.size() != 0) {
            Main.project.forEach((key, project) -> {
                if (project.getFreelancer() == Main.loggedFreelancer) {
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

}
