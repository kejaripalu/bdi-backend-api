package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterEkspedisiMapper {

    RegisterEkspedisiMapper INSTANCE = Mappers.getMapper(RegisterEkspedisiMapper.class);

    RegisterEkspedisiDTO toDTO(RegisterEkspedisi entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterEkspedisi toEntity(RegisterEkspedisiDTO dto);
}
