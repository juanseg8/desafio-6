package Desafio6.Models.Mappers;

import Desafio6.Models.Dtos.AutomobileAddDTO;
import Desafio6.Models.Dtos.AutomobileEditDTO;
import Desafio6.Models.Dtos.AutomobileReadDTO;
import Desafio6.Models.Entities.AutomobileEntity;
import Desafio6.Models.Repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AutomobileMapper {
    private final UserRepository userRepository;

    public AutomobileMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AutomobileReadDTO automobileEntityToAutomobileReadDTO(AutomobileEntity automobileEntity) {
        AutomobileReadDTO automobileReadDTO = new AutomobileReadDTO();
        automobileReadDTO.setBrand(automobileEntity.getBrand());
        automobileReadDTO.setPatent(automobileEntity.getPatent());
        automobileReadDTO.setModel(automobileEntity.getModel());
        return automobileReadDTO;
    }

    public AutomobileEntity automobileAddDTToAutomobileEntity(AutomobileAddDTO automobileAddDTO) {
        AutomobileEntity automobileEntity = new AutomobileEntity();
        automobileEntity.setBrand(automobileAddDTO.getBrand());
        automobileEntity.setModel(automobileAddDTO.getModel());
        automobileEntity.setPatent(automobileAddDTO.getPatent());
        automobileEntity.setUser(userRepository.findById(automobileAddDTO.getUserId()).orElse(null));
        return automobileEntity;
    }

    public AutomobileEntity automobileEditDTOToAutomobileEntity(AutomobileEntity automobileEntity, AutomobileEditDTO automobileEditDTO) {
        automobileEntity.setPatent(automobileEditDTO.getPatent());
        automobileEntity.setModel(automobileEditDTO.getModel());
        automobileEntity.setBrand(automobileEditDTO.getBrand());
        return automobileEntity;
    }
}
