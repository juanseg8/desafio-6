package Desafio6.Models.Mappers;

import Desafio6.Models.Dtos.AddressReadDTO;
import Desafio6.Models.Dtos.UserAddDTO;
import Desafio6.Models.Dtos.UserEditDTO;
import Desafio6.Models.Dtos.UserReadDTO;
import Desafio6.Models.Entities.AutomobileEntity;
import Desafio6.Models.Entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final AddressMapper addressMapper;
    private final AutomobileMapper automobileMapper;

    public UserMapper(AddressMapper addressMapper, AutomobileMapper automobileMapper) {
        this.addressMapper = addressMapper;
        this.automobileMapper = automobileMapper;
    }

    public UserReadDTO userEntityToUserReadDTO(UserEntity userEntity, List<AutomobileEntity> automobileEntities) {
        UserReadDTO userReadDTO = new UserReadDTO();
        userReadDTO.setId(userEntity.getId());
        userReadDTO.setName(userEntity.getName());
        userReadDTO.setSurname(userEntity.getSurname());
        userReadDTO.setEmail(userEntity.getEmail());
        AddressReadDTO addressReadDTO = new AddressReadDTO();
        addressReadDTO.setStreet(userEntity.getAddress().getStreet());
        addressReadDTO.setNumber(userEntity.getAddress().getNumber());
        userReadDTO.setAddress(addressReadDTO);
        if(Objects.nonNull(automobileEntities)){
            userReadDTO.setAutomobiles(
                    automobileEntities
                            .stream()
                            .map(automobileMapper::automobileEntityToAutomobileReadDTO)
                            .collect(Collectors.toList())
            );
        }
        return userReadDTO;
    }

    public UserEntity userAddDTOToUserEntity(UserAddDTO userAddDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userAddDTO.getName());
        userEntity.setSurname(userAddDTO.getSurname());
        userEntity.setEmail(userAddDTO.getEmail());
        userEntity.setPassword(userAddDTO.getPassword());
        userEntity.setAddress(addressMapper.addressReadDTOToAddressEntity(userAddDTO.getAddress()));
        return userEntity;
    }

    public UserEntity userEditDTOToUserEntity(UserEntity userEntity, UserEditDTO userEditDTO) {
        userEntity.setName(userEditDTO.getName());
        userEntity.setSurname(userEditDTO.getSurname());
        userEntity.getAddress().setStreet(userEditDTO.getAddress().getStreet());
        userEntity.getAddress().setNumber(userEditDTO.getAddress().getNumber());
        return userEntity;
    }
}
