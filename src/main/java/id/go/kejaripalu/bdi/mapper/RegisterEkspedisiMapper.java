package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterEkspedisi;
import id.go.kejaripalu.bdi.dto.RegisterEkspedisiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterEkspedisiMapper {

    RegisterEkspedisiMapper INSTANCE = Mappers.getMapper(RegisterEkspedisiMapper.class);

    RegisterEkspedisiDTO toDTO(RegisterEkspedisi entity);

    RegisterEkspedisi toEntity(RegisterEkspedisiDTO dto);
}
