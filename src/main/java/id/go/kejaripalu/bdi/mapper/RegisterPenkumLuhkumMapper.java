package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterPenkumLuhkumMapper {

    RegisterPenkumLuhkumMapper INSTANCE = Mappers.getMapper(RegisterPenkumLuhkumMapper.class);

    RegisterPenkumLuhkumDTO toDTO(RegisterPenkumLuhkum entity);

    RegisterPenkumLuhkum toEntity(RegisterPenkumLuhkumDTO dto);
}
