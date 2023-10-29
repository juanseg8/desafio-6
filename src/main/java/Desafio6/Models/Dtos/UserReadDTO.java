package Desafio6.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private AddressReadDTO address;
    private List<AutomobileReadDTO> automobiles;
}
