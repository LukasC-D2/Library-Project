package lib.project.backend.model.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String token;
    private String message;
}
