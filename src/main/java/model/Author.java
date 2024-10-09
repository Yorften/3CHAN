package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import model.enums.Role;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "First name should not be null")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "Last name should not be null")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Email should not be null")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "Email should not be null")
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @NotNull(message = "Role should not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", birthDay=" + birthDay +
                ", role=" + role +
                '}';
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public LocalDate getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(LocalDate value) {
        this.birthDay = value;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role value) {
        this.role = value;
    }
}
