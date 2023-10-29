package Desafio6.Services;

import Desafio6.Exceptions.ExceptionsKind.UserBadRequestException;
import Desafio6.Exceptions.ExceptionsKind.UserNotFoundException;
import Desafio6.Models.Dtos.UserAddDTO;
import Desafio6.Models.Dtos.UserEditDTO;
import Desafio6.Models.Dtos.UserReadDTO;
import Desafio6.Models.Entities.AutomobileEntity;
import Desafio6.Models.Entities.UserEntity;
import Desafio6.Models.Mappers.UserMapper;
import Desafio6.Models.Repositories.AutomobileRepository;
import Desafio6.Models.Repositories.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final AutomobileRepository automobileRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, AutomobileRepository automobileRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.automobileRepository = automobileRepository;
    }

    public Page<UserReadDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<UserEntity> userEntities = userRepository.findAll(pageable);

        List<UserReadDTO> users = userEntities
                .stream()
                .map(userEntity -> {
                    List<AutomobileEntity> automobileEntities = automobileRepository
                            .findAllByUserId(userEntity.getId());
                    return userMapper.userEntityToUserReadDTO(userEntity, automobileEntities);
                })
                .collect(Collectors.toList());

        return new PageImpl<>(users, pageable, userEntities.getTotalElements());
    }
    public UserReadDTO add(UserAddDTO userAddDTO){
        Boolean emailExist = userRepository.existsByEmail(userAddDTO.getEmail());
        if (emailExist) throw new UserBadRequestException("Ya existe usuario con ese email");

        UserEntity userEntity = userMapper.userAddDTOToUserEntity(userAddDTO);
        userRepository.save(userEntity);
        return userMapper.userEntityToUserReadDTO(userEntity, null);
    }
    public UserReadDTO findById(Integer userId){
        if (Objects.isNull(userId)) throw new UserBadRequestException("No esta definido el id de usuario");
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No existe ese usuario"));
        List<AutomobileEntity> automobileEntities = automobileRepository.findAllByUserId(userId);
        return userMapper.userEntityToUserReadDTO(userEntity, automobileEntities);
    }

    public UserReadDTO deleteById(Integer userId) {
        if (Objects.isNull(userId)) throw new UserBadRequestException("No esta definido el id de usuario");
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("No se encontro el usuario con el id especificado"));
        List<AutomobileEntity> automobileEntities = automobileRepository.findAllByUserId(userId);
        UserReadDTO user = userMapper.userEntityToUserReadDTO(userEntity, automobileEntities);
        userRepository.delete(userEntity);
        automobileRepository.deleteAll(automobileEntities);
        return user;
    }

    public UserReadDTO edit(Integer userId, UserEditDTO userEditDTO) {
        if (Objects.isNull(userId)) throw new UserBadRequestException("No esta definido el id de usuario");

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("No se encontro el usuario con el id especificado"));
        List<AutomobileEntity> automobileEntities = automobileRepository.findAllByUserId(userId);

        UserEntity newUser = userMapper.userEditDTOToUserEntity(userEntity, userEditDTO);
        userRepository.save(newUser);
        return userMapper.userEntityToUserReadDTO(newUser, automobileEntities);

    }
}
