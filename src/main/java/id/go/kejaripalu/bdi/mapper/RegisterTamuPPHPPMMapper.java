package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterTamuPPHPPM;
import id.go.kejaripalu.bdi.dto.RegisterTamuPPHPPMDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterTamuPPHPPMMapper {

    RegisterTamuPPHPPMMapper INSTANCE = Mappers.getMapper(RegisterTamuPPHPPMMapper.class);

    RegisterTamuPPHPPMDTO toDTO(RegisterTamuPPHPPM entity);

    RegisterTamuPPHPPM toEntity(RegisterTamuPPHPPMDTO dto);
}
