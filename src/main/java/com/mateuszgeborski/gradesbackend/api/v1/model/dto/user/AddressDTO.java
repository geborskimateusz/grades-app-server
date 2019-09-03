package com.mateuszgeborski.gradesbackend.api.v1.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"user"})
public class AddressDTO extends BaseEntityDTO {
    private String city;
    private String street;
    private String homeNumber;
    private String postalCode;
    private UserDTO user;
}
