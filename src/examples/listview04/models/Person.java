package examples.listview04.models;

public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }

}
