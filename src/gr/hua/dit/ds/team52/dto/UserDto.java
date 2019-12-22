package gr.hua.dit.ds.team52.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

        @NotNull
        @NotEmpty
        private String firstName;

        @NotNull
        @NotEmpty
        private String lastName;

        @NotNull
        @NotEmpty
        private String username;

        @NotNull
        @NotEmpty
        private String password;

        @NotNull
        @NotEmpty
        private String role;


}
