package Desafio6.Models.Mappers;

import Desafio6.Models.Dtos.AddressReadDTO;
import Desafio6.Models.Entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressEntity addressReadDTOToAddressEntity(AddressReadDTO addressReadDTO) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setNumber(addressReadDTO.getNumber());
        addressEntity.setStreet(addressReadDTO.getStreet());
        return addressEntity;
    }
}
