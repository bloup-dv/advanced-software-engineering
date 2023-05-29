package ase.types;

public enum Status {
    TODO("Todo"), IN_PROGRESS("In Progress"), DONE("Done");
    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
