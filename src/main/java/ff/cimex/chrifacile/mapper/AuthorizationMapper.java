package ff.cimex.chrifacile.mapper;

import ff.cimex.chrifacile.dto.AuthorizationDto;
import ff.cimex.chrifacile.entity.Authorization;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorizationMapper {

    // Convert Entity to DTO
    public static AuthorizationDto mapToDto(Authorization authorization) {
        AuthorizationDto dto = new AuthorizationDto();
        dto.setNameAutorisation(authorization.getNameAutorisation());
        return dto;
    }

    // Convert DTO to Entity
    public static Authorization mapToEntity(AuthorizationDto dto) {
        Authorization authorization = new Authorization();
        authorization.setNameAutorisation(dto.getNameAutorisation());
        return authorization;
    }
}