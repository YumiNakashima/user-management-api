package br.com.usermanagementapi.mapper;

import br.com.usermanagementapi.dto.UserDTO;
import br.com.usermanagementapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User entity);
}
