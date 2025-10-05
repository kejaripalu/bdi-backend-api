package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKegiatanIntelijenPamstraMapper {

    RegisterKegiatanIntelijenPamstraMapper INSTANCE =
            Mappers.getMapper(RegisterKegiatanIntelijenPamstraMapper.class);

    RegisterKegiatanIntelijenPamstraDTO toDTO(RegisterKegiatanIntelijenPamstra entity);

    RegisterKegiatanIntelijenPamstra toEntity(RegisterKegiatanIntelijenPamstraDTO dto);
}
