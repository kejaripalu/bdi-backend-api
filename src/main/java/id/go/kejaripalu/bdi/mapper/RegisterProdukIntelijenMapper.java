package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterProdukIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterProdukIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterProdukIntelijenMapper {

    RegisterProdukIntelijenMapper INSTANCE = Mappers.getMapper(RegisterProdukIntelijenMapper.class);

    RegisterProdukIntelijenDTO toDTO(RegisterProdukIntelijen entity);

    RegisterProdukIntelijen toEntity(RegisterProdukIntelijenDTO dto);
}
