package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterTelaahanIntelijen;
import id.go.kejaripalu.bdi.dto.RegisterTelaahanIntelijenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterTelaahanIntelijenMapper {

    RegisterTelaahanIntelijenMapper INSTANCE = Mappers.getMapper(RegisterTelaahanIntelijenMapper.class);

    RegisterTelaahanIntelijenDTO toDTO(RegisterTelaahanIntelijen entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterTelaahanIntelijen toEntity(RegisterTelaahanIntelijenDTO dto);
}
