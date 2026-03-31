package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterTamuPPHPPMMapper {

    RegisterTamuPPHPPMMapper INSTANCE = Mappers.getMapper(RegisterTamuPPHPPMMapper.class);

    RegisterTamuPPHPPMDTO toDTO(RegisterTamuPPHPPM entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterTamuPPHPPM toEntity(RegisterTamuPPHPPMDTO dto);
}
