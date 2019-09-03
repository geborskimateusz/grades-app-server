package com.mateuszgeborski.gradesbackend.domain.user;

import com.mateuszgeborski.gradesbackend.domain.BaseEntity;
import com.mateuszgeborski.gradesbackend.domain.user.Role;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "User")
@Table(name = "user")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String motherName;
    private String fatherName;
    private String personalIdentityNum;
    private LocalDate dateOfBirth;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Contact contact;

    @OneToOne(mappedBy = "user")
    private ProfileImage profileImage;

    @Builder(builderMethodName = "defaultBuilder")
    public User(String firstName, String lastName, String motherName, String fatherName, String personalIdentityNum, LocalDate dateOfBirth, String username, String password, List<Role> roles, Address address, Contact contact, ProfileImage profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.personalIdentityNum = personalIdentityNum;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.address = address;
        this.contact = contact;
        this.profileImage = profileImage;
    }


    @Builder(builderMethodName = "testBuilder", builderClassName = "UserTestBuilder")
    public User(Long id, String firstName, String lastName, String motherName, String fatherName, String personalIdentityNum, LocalDate dateOfBirth, String username, String password, List<Role> roles, Address address, Contact contact, ProfileImage profileImage) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.personalIdentityNum = personalIdentityNum;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.address = address;
        this.contact = contact;
        this.profileImage = profileImage;
    }

}
