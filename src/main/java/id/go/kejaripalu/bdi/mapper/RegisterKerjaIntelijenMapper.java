package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKerjaIntelijenMapper {

    RegisterKerjaIntelijenMapper INSTANCE = Mappers.getMapper(RegisterKerjaIntelijenMapper.class);

    RegisterKerjaIntelijenDTO toDTO(RegisterKerjaIntelijen entity);

    RegisterKerjaIntelijen toEntity(RegisterKerjaIntelijenDTO dto);
}
