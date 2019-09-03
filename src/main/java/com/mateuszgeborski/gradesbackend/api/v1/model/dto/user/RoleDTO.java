package com.mateuszgeborski.gradesbackend.api.v1.model.dto.user;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.BaseEntityDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BaseEntityDTO {
    private String name;
}
