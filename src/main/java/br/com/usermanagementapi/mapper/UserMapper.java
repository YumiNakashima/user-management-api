package br.com.usermanagementapi.mapper;

import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.model.reponse.UserResponse;
import br.com.usermanagementapi.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDTO(User entity);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    User toEntity(Long id, UserRequest userRequest);
}
