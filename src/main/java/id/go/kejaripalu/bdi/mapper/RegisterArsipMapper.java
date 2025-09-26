package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterArsip;
import id.go.kejaripalu.bdi.dto.RegisterArsipDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterArsipMapper {

    RegisterArsipMapper INSTANCE = Mappers.getMapper(RegisterArsipMapper.class);

    RegisterArsipDTO toDTO(RegisterArsip entity);

    RegisterArsip toEntity(RegisterArsipDTO dto);
}
