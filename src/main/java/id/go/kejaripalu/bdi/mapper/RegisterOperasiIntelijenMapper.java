package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterOperasiIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterOperasiIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterOperasiIntelijenMapper {

    RegisterOperasiIntelijenMapper INSTANCE = Mappers.getMapper(RegisterOperasiIntelijenMapper.class);

    RegisterOperasiIntelijenDTO toDTO(RegisterOperasiIntelijen entity);

    RegisterOperasiIntelijen toEntity(RegisterOperasiIntelijenDTO dto);
}
