package com.example.homework18.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPassDto implements Serializable {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
