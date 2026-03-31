package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterPenkumLuhkumMapper {

    RegisterPenkumLuhkumMapper INSTANCE = Mappers.getMapper(RegisterPenkumLuhkumMapper.class);

    RegisterPenkumLuhkumDTO toDTO(RegisterPenkumLuhkum entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterPenkumLuhkum toEntity(RegisterPenkumLuhkumDTO dto);
}
