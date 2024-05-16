package gr.aueb.cf.reviewapp.security.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogInFailureDTO {
    private String message = "Username not registered";
}
