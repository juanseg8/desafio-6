package Desafio6.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileEditDTO {
    private String brand;
    private String model;
    private String patent;
}
