package ro.fortech.winewiki.profilemicro.model;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "person",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person extends BaseEntity<Long> {
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    @Lob
    private byte[] picture;


    @Column(name = "phonenumber")
    private String phonenumber;

    @OneToMany(mappedBy="owner", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Wine> wineSet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!username.equals(person.username)) return false;
        if (!password.equals(person.password)) return false;
        if (!location.equals(person.location)) return false;
        if (firstname != null ? !firstname.equals(person.firstname) : person.firstname != null) return false;
        if (lastname != null ? !lastname.equals(person.lastname) : person.lastname != null) return false;
        if (description != null ? !description.equals(person.description) : person.description != null) return false;
        return Arrays.equals(picture, person.picture);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }

    @Column(name = "mail")
    private String mail;

    @Column(name = "resettoken")
    private String resettoken;
}
