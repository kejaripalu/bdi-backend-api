package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKegiatanIntelijenMapper {

    RegisterKegiatanIntelijenMapper INSTANCE = Mappers.getMapper(RegisterKegiatanIntelijenMapper.class);

    RegisterKegiatanIntelijenDTO toDTO(RegisterKegiatanIntelijen entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterKegiatanIntelijen toEntity(RegisterKegiatanIntelijenDTO dto);
}
