package com.mateuszgeborski.gradesbackend.api.v1.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"username"})
public class UserDTO extends BaseEntityDTO {
    private String firstName;
    private String lastName;
    private String motherName;
    private String fatherName;
    private String personalIdentityNum;
    private LocalDate dateOfBirth;
    private AddressDTO address;
    private ContactDTO contact;
    private ProfileImageDTO profileImage;

    private String username;
    private List<RoleDTO> roles = new ArrayList<>();

    @Builder(builderMethodName = "defaultBuilder")
    public UserDTO(String firstName, String lastName, String motherName, String fatherName, String personalIdentityNum, LocalDate dateOfBirth, UserDTO user, AddressDTO address, ContactDTO contact, ProfileImageDTO profileImage, String username,
                   List<RoleDTO> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.personalIdentityNum = personalIdentityNum;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contact = contact;
        this.profileImage = profileImage;
        this.username = username;
        this.roles = roles;
    }


    @Builder(builderMethodName = "testBuilder", builderClassName = "UserTestBuilder")
    public UserDTO(Long id, String firstName, String lastName, String motherName, String fatherName, String personalIdentityNum, LocalDate dateOfBirth, UserDTO user, AddressDTO address, ContactDTO contact, ProfileImageDTO profileImage, String username,
                   List<RoleDTO> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.personalIdentityNum = personalIdentityNum;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contact = contact;
        this.profileImage = profileImage;
        this.username = username;
        this.roles = roles;
    }
}
