public class Project {
    private String title;
    private String description;
    private long fee;
    private String deadline;
    private Client client;
    private Freelancer freelancer;

    Project(String title, String description, long fee, String deadline) {
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.deadline = deadline;
        this.client = null;
        this.freelancer = null;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public long getFee() {
        return this.fee;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Freelancer getFreelancer() {
        return this.freelancer;
    }

}
