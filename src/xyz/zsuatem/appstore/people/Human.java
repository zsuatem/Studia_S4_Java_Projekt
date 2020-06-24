package xyz.zsuatem.appstore.people;

public abstract class Human {
    private String fullName;

    public Human(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
