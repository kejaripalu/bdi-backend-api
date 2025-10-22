package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.DataPeta;
import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DataPetaMapper {

    DataPetaMapper INSTANCE = Mappers.getMapper(DataPetaMapper.class);

    DataPetaDTO toDTO(DataPeta entity);

    DataPeta toEntity(DataPetaDTO dto);
}
