// Create new file: ChangePasswordRequestDTO.java
package com.autobid.autobid.DTO;

import lombok.Data;

@Data
public class ChangePasswordRequestDTO {
    private Integer id;
    private String current_password;
    private String new_password;
}
