package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterSuratMasukMapper {

    RegisterSuratMasukMapper INSTANCE = Mappers.getMapper(RegisterSuratMasukMapper.class);

    RegisterSuratMasukDTO toDTO(RegisterSuratMasuk entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterSuratMasuk toEntity(RegisterSuratMasukDTO dto);
}
