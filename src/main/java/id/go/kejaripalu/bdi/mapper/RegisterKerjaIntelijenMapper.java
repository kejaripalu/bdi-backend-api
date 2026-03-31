package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKerjaIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterKerjaIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKerjaIntelijenMapper {

    RegisterKerjaIntelijenMapper INSTANCE = Mappers.getMapper(RegisterKerjaIntelijenMapper.class);

    RegisterKerjaIntelijenDTO toDTO(RegisterKerjaIntelijen entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterKerjaIntelijen toEntity(RegisterKerjaIntelijenDTO dto);
}
