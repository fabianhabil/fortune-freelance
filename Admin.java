public class Admin extends User {
    Admin(String name, String username, String password) {
        super(name, username, password);
    }

    // Function menu for user admin
    public static void menu() {
        int input;
        while (true) {
            Main.clearScr();
            System.out.println("Fortune Freelance - Admin Menu");
            System.out.println("1. See all Freelancer");
            System.out.println("2. See all Client");
            System.out.println("3. See all Project");
            System.out.println("0. Logout");
            input = Main.inputInteger("Choose your menu: ");
            switch (input) {
                case 1:
                    Main.clearScr();
                    Main.showAllFreelancer();
                    break;
                case 2:
                    Main.clearScr();
                    Main.showAllClient();
                    break;
                case 3:
                    Main.clearScr();
                    Main.showAllProject();
                    break;
                // Logout
                case 0:
                    Main.loggedIn = false;
                    Main.loggedAdmin = null;
                    return;
            }
            Main.enterToContinue();
        }
    }
}
