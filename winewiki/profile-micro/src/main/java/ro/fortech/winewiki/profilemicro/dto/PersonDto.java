package ro.fortech.winewiki.profilemicro.dto;

import java.util.Arrays;

public class PersonDto extends NewPersonDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String description;
    private byte[] picture;
    private WineDtoList wines;
    private String mail;
    private String resettoken;

    private PersonDto() {
    }

    public PersonDto(Long id,
                     String username,
                     String password,
                     String location,
                     String firstname,
                     String lastname,
                     String description,
                     byte[] picture,
                     WineDtoList wines) {
        super(username, password, location, "");
        this.picture = picture;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.id = id;
        this.wines = wines;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public WineDtoList getWines() {
        return wines;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", wines=" + wines +
                '}';
    }
}

