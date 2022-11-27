import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    // Hashmap to store all the object based on the object
    // example, admin hashmap storing Admin Object with key username
    // client and freelancer too, the key is username
    // different on project, the project key is integer.
    static HashMap<String, Admin> admin = new HashMap<String, Admin>();
    static HashMap<String, Client> client = new HashMap<String, Client>();
    static HashMap<String, Freelancer> freelancer = new HashMap<String, Freelancer>();
    static HashMap<Integer, Project> project = new HashMap<Integer, Project>();

    // Array list of string username, so user cant register if username is already
    // registered.
    static ArrayList<String> listUsername = new ArrayList<String>();

    // Check if the user is logged in
    static boolean loggedIn = false;
    // 0 = admin, 1 = client, 2 = freelancer
    static int loggedInType = 0;

    // Object if the current state log in is admin
    static Admin loggedAdmin = null;
    // Object if the current state log in is client
    static Client loggedClient = null;
    // Object if the current state log in is freelance
    static Freelancer loggedFreelancer = null;
    // Object scanner for input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearScr();
        initializeAdmin();
        menuAuth();
    }

    // Procedur prompt for user, press enter to continue the program
    public static void enterToContinue() {
        System.out.println("Press Enter key to continue");
        scanner.nextLine();
    }

    // Procedur for clear screen
    public static void clearScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Function for handle input user if integer, if user input not integer then
    // giving error and try again.
    public static int inputInteger(String notice) {
        int value = 0;
        boolean exit = false;
        do {
            if (!notice.equals("")) {
                System.out.print(notice);
            }
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                exit = true;
            } catch (NumberFormatException exp) {
                System.out.println("Wrong input! Input must be number!");
            }
        } while (!exit);
        return value;
    }

    // Function for handle input user if big integer, if user input not big integer
    // then giving error and try again.
    public static long inputLong(String notice) {
        long value = 0;
        boolean exit = false;
        do {
            if (!notice.equals("")) {
                System.out.print(notice);
            }
            String input = scanner.nextLine();
            try {
                value = Long.parseLong(input);
                exit = true;
            } catch (NumberFormatException exp) {
                System.out.println("Wrong input! Input must be number!");
            }
        } while (!exit);
        return value;
    }

    // This function is called when the program run, because we want to create user
    // admin
    public static void initializeAdmin() {
        admin.put("fabianhabil", new Admin("Fabian Habil Ramdhan", "fabianhabil", "123"));
        listUsername.add("fabianhabil");
        admin.put("habilr", new Admin("Habil Ramdhan", "habilr", "123"));
        listUsername.add("habilr");
    }

    // The first menu for auth
    public static void menuAuth() {
        while (true) {
            clearScr();
            // Check if the current state is logged in or no.
            // If logged in, show the main menu
            // If didnt logged in, show the menu auth
            if (loggedIn) {
                Menu();
            } else {
                System.out.println("Welcome to Fortune Freelance");
                System.out.println("1. Register");
                System.out.println("2. Log in");
                System.out.println("3. Debug");
                System.out.println("0. Exit");
                int input = inputInteger("Choice: ");
                System.out.println(input);
                switch (input) {
                    case 0:
                        System.out.println("Thank you, bye!");
                        return;
                    case 1:
                        Register();
                        break;
                    case 2:
                        Login();
                        break;
                    case 3:
                        System.out.println("test");
                        project.forEach((key, project) -> {
                            System.out
                                    .println(project.getTitle() + " nama client " + project.getClient().getName()
                                            + " username client "
                                            + project.getClient().getUsername() + " nama freelancer "
                                            + project.getFreelancer().getName() + " "
                                            + project.getFreelancer().getUsername());
                            if (loggedClient != null) {
                                System.out.println(project.getClient() == loggedClient);
                            }
                            if (loggedFreelancer != null) {
                                System.out.println(project.getFreelancer() == loggedFreelancer);
                            }
                        });
                        enterToContinue();
                        break;
                    default:
                        System.out.println("Error! Invalid Choice");
                        enterToContinue();
                        break;
                }
            }
        }
    }

    // Function for register user
    public static void Register() {
        while (true) {
            clearScr();
            String name;
            String username;
            String password;
            System.out.println("Welcome to Fortune Freelance");
            System.out.println("1. Client");
            System.out.println("2. Freelancer");
            System.out.println("0. Exit");
            // 1 = client, 2 = freelance
            int typeUser = inputInteger("Choose your account type: ");
            clearScr();
            switch (typeUser) {
                case 0:
                    return;
                // Register as a client and then
                case 1:
                    System.out.println("Register as Client");
                    System.out.print("Name: ");
                    name = scanner.nextLine();
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    // Notice user if the username is registered.
                    if (listUsername.contains(username)) {
                        System.out.println("Username is registered! Use another username!");
                        enterToContinue();
                        return;
                    }
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    // Add to hashmap client
                    client.put(username, new Client(name, username, password));
                    listUsername.add(username);
                    System.out.println("Register as client completed!");
                    enterToContinue();
                    return;
                case 2:
                    System.out.println("Register as Freelancer");
                    System.out.print("Name: ");
                    name = scanner.nextLine();
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    // Notice user if the username is registered.
                    if (listUsername.contains(username)) {
                        System.out.println("Username is registered! Use another username!");
                        enterToContinue();
                        return;
                    }
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    // Add to hashmap freelancer
                    freelancer.put(username, new Freelancer(name, username, password));
                    listUsername.add(username);
                    System.out.println("Register as freelancer completed!");
                    enterToContinue();
                    return;
            }
        }
    }

    // Function for login
    public static void Login() {
        clearScr();
        String username;
        String password;
        System.out.println("Welcome to Fortune Freelance");
        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();

        // Get from admin hashmap based on key username, if not found its null.
        Admin adminSearch = admin.get(username);
        // Get from client hashmap based on key username, if not found its null.
        Client clientSearch = client.get(username);
        // Get from freelancer hashmap based on key username, if not found its null.
        Freelancer freelancerSearch = freelancer.get(username);

        // If object from hashmap admin is founded, do verify login.
        if (adminSearch != null) {
            int status = adminSearch.login(username, password);
            // If status is 1, then login is completed.
            // set the variable loggedIn is true, account type 0
            // and set object admin to the user credentials that user logged in.
            if (status == 1) {
                loggedAdmin = adminSearch;
                System.out.println("Welcome " + loggedAdmin.getName() + ", you are a admin!");
                enterToContinue();
                loggedIn = true;
                loggedInType = 0;
                // Ke menu
                return;
            }
            // if the status is -1, the password is invalid.
            else if (status == -1) {
                System.out.println("Invalid Password!");
                enterToContinue();
                return;
            }
        }

        // If object from hashmap client is founded, do verify login.
        if (clientSearch != null) {
            int status = clientSearch.login(username, password);
            // If status is 1, then login is completed.
            // set the variable loggedIn is true, account type 1
            // and set object client to the user credentials that user logged in.
            if (status == 1) {
                loggedClient = clientSearch;
                System.out.println("Welcome " + loggedClient.getName() + ", you are a client!");
                enterToContinue();
                loggedIn = true;
                loggedInType = 1;
                // Ke menu
                return;
            }
            // if the status is -1, the password is invalid.
            else if (status == -1) {
                System.out.println("Invalid Password!");
                enterToContinue();
                return;
            }
        }

        // If object from hashmap freelancer is founded, do verify login.
        if (freelancerSearch != null) {
            int status = freelancerSearch.login(username, password);
            // If status is 1, then login is completed.
            // set the variable loggedIn is true, account type 2
            // and set object freelancer to the user credentials that user logged in.
            if (status == 1) {
                loggedFreelancer = freelancerSearch;
                System.out.println("Welcome " + loggedFreelancer.getName() + ", you are a freelancer!");
                enterToContinue();
                loggedIn = true;
                loggedInType = 2;
                // Ke menu
                return;
            }
            // if the status is -1, the password is invalid.
            else if (status == -1) {
                System.out.println("Invalid Password!");
                enterToContinue();
                return;
            }
        }

        // Because in all our search is return if user is found and logged in or invalid
        // password
        // so this block of code will reached if no user is found in all of the three
        // array of objects.
        System.out.println("User not found!");
        enterToContinue();
        return;
    }

    // Function for the main menu
    public static void Menu() {
        // check what type of user is logged in
        switch (loggedInType) {
            case 0:
                Admin.menu();
                break;
            case 1:
                Client.menu();
                break;
            case 2:
                Freelancer.menu();
                break;
        }
    }

    // Function for showing all project
    public static void showAllProject() {
        System.out.println("List all Project in Fortune Freelance");
        if (project.size() != 0) {
            // Because we cant simply iterate integer in for each because integers are
            // immutable and for each is a lambda function so we use AtomicInteger object,
            // to numbering the list of the project for better user experience.
            AtomicInteger iterate = new AtomicInteger(1);
            project.forEach((key, project) -> {
                System.out.println(
                        iterate.getAndIncrement() + ". " + project.getTitle() + " (" + project.getDescription() + ")");
                System.out.println("   Fee: Rp" + project.getFee() + " (" + project.getDeadline() + ")");
                System.out.println("   ID Project = " + key);
                if (project.getFreelancer() == null) {
                    System.out.println("   Freelancer status: No Freelancer taken this project yet.");
                } else {
                    System.out.println("   Freelancer status: " + project.getFreelancer().getName() + " ("
                            + project.getFreelancer().getUsername() + ")");
                }
            });
        } else {
            System.out.println("There is no Project in Fortune Freelance");
        }
    }

    // Function for showing all project
    public static void showAllAvailableProject() {
        System.out.println("List all Project in Fortune Freelance");
        // Because we cant simply iterate integer in for each because integers are
        // immutable and for each is a lambda function so we use AtomicInteger object,
        // we iterate if the project
        // didnt have client, so we iterate by 1. So user can see if there is no project
        // available. In the for each, we get value first and then increment by 1
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger iterate = new AtomicInteger(1);

        // We iterate through hashmap project, if the client is null or no client yet
        // we increment the count
        project.forEach((key, project) -> {
            if (project.getFreelancer() == null) {
                count.getAndIncrement();
            }
        });

        if (count.get() != 0) {
            project.forEach((key, project) -> {
                if (project.getFreelancer() == null) {
                    System.out
                            .println(iterate.getAndIncrement() + ". " + project.getTitle() + " ("
                                    + project.getDescription() + ")");
                    System.out.println("   Fee: Rp" + project.getFee() + " (" + project.getDeadline() + ")");
                    System.out.println("   ID Project = " + key);
                }
            });
        } else {
            System.out.println("There is no Available Project in Fortune Freelance");
        }
    }

    // Function for showing all client
    public static void showAllClient() {
        System.out.println("List all of Client in Fortune Freelance");
        if (client.size() != 0) {
            // Because we cant simply iterate integer in for each because integers are
            // immutable and for each is a lambda function so we use AtomicInteger object,
            // to numbering the list of the client for better user experience.
            AtomicInteger iterate = new AtomicInteger(1);
            client.forEach((key, client) -> {
                System.out.println(
                        iterate.getAndIncrement() + ". " + client.getName() + " (" + client.getUsername() + ")");
            });
        } else {
            System.out.println("There is no Client in Fortune Freelance");
        }
        enterToContinue();
    }

    // Function for showing all freelancer
    public static void showAllFreelancer() {
        System.out.println("List all of Freelancer in Fortune Freelance");
        if (freelancer.size() != 0) {
            // Because we cant simply iterate integer in for each because integers are
            // immutable and for each is a lambda function so we use AtomicInteger object,
            // to numbering the list of the freelancer for better user experience.
            AtomicInteger iterate = new AtomicInteger(1);
            freelancer.forEach((key, freelancer) -> {
                System.out.println(iterate.getAndIncrement() + ". " + freelancer.getName() + " ("
                        + freelancer.getUsername() + ")");
            });
        } else {
            System.out.println("There is no Freelancer in Fortune Freelance");
        }
    }

}
