package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKegiatanIntelijenMapper {

    RegisterKegiatanIntelijenMapper INSTANCE = Mappers.getMapper(RegisterKegiatanIntelijenMapper.class);

    RegisterKegiatanIntelijenDTO toDTO(RegisterKegiatanIntelijen entity);

    RegisterKegiatanIntelijen toEntity(RegisterKegiatanIntelijenDTO dto);
}
