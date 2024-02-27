package nz.ac.canterbury.seng302.gardenersgrove.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity class reflecting an entry of firstName, optional lastName, date of birth, email, and password
 * Note the @link{Entity} annotation required for declaring this as a persistence entity
 */
@Entity
public class Gardener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String lastName;

    @Column(nullable = false)
    private Date DoB;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int password;

    /**
     * JPA required no-args constructor
     */
    protected Gardener() {}

    /**
     * Creates a new Gardener object
     * @param firstName firstName of user
     * @param DoB user's date of birth
     * @param email user's email
     * @param password user's password
     */
    public Gardener(String firstName, String lastName, Date DoB, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DoB = DoB;
        this.email = email;
        this.password = password.hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDoB() {
        return DoB;
    }

    public String getEmail() {
        return email;
    }

    public int getPassword() {return password; }


    @Override
    public String toString() {
        String gardenerString = "Gardener{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' ;
        if (getLastName() != null) {
            gardenerString += ", lastName='" + lastName + '\'';
        }

        gardenerString += ", DoB='" + DoB + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "}";

        return gardenerString;
    }
}
