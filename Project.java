public class Project {
    private String title;
    private String description;
    private long fee;
    private String deadline;
    private Client client;
    private Freelancer freelancer;

    // Constructor
    Project(String title, String description, long fee, String deadline) {
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;
        this.client = null;
        this.freelancer = null;
    }

    // Getter for project title
    public String getTitle() {
        return this.title;
    }

    // Getter for project description
    public String getDescription() {
        return this.description;
    }

    // Getter for project fee
    public long getFee() {
        return this.fee;
    }

    // Getter for project deadline
    public String getDeadline() {
        return this.deadline;
    }

    // Getter for object Client
    public Client getClient() {
        return this.client;
    }

    // Getter for object Freelancer
    public Freelancer getFreelancer() {
        return this.freelancer;
    }

    // Setter for object Client
    public void setClient(Client client) {
        this.client = client;
    }

    // Setter for object Freelancer
    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

}
