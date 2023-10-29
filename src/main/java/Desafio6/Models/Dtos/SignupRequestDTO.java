package Desafio6.Models.Dtos;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private AddressReadDTO address;
}
