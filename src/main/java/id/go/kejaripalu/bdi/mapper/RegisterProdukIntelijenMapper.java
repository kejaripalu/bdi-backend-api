package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterProdukIntelijenMapper {

    RegisterProdukIntelijenMapper INSTANCE = Mappers.getMapper(RegisterProdukIntelijenMapper.class);

    RegisterProdukIntelijenDTO toDTO(RegisterProdukIntelijen entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterProdukIntelijen toEntity(RegisterProdukIntelijenDTO dto);
}
