package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterSuratKeluar;
import id.go.kejaripalu.bdi.dto.RegisterSuratKeluarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterSuratKeluarMapper {

    RegisterSuratKeluarMapper INSTANCE = Mappers.getMapper(RegisterSuratKeluarMapper.class);

    RegisterSuratKeluarDTO toDTO(RegisterSuratKeluar entity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    RegisterSuratKeluar toEntity(RegisterSuratKeluarDTO dto);
}
