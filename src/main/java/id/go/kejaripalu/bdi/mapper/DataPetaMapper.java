package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.DataPeta;
import id.go.kejaripalu.bdi.dto.DataPetaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DataPetaMapper {

    DataPetaMapper INSTANCE = Mappers.getMapper(DataPetaMapper.class);

    DataPetaDTO toDTO(DataPeta entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    DataPeta toEntity(DataPetaDTO dto);
}
