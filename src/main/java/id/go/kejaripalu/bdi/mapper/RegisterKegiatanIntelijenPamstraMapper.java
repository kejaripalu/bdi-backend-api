package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterKegiatanIntelijenPamstra;
import id.go.kejaripalu.bdi.dto.RegisterKegiatanIntelijenPamstraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterKegiatanIntelijenPamstraMapper {

    RegisterKegiatanIntelijenPamstraMapper INSTANCE =
            Mappers.getMapper(RegisterKegiatanIntelijenPamstraMapper.class);

    RegisterKegiatanIntelijenPamstraDTO toDTO(RegisterKegiatanIntelijenPamstra entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterKegiatanIntelijenPamstra toEntity(RegisterKegiatanIntelijenPamstraDTO dto);
}
