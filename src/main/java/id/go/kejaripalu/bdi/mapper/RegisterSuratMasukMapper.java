package id.go.kejaripalu.bdi.mapper;

import id.go.kejaripalu.bdi.domain.RegisterSuratMasuk;
import id.go.kejaripalu.bdi.dto.RegisterSuratMasukDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterSuratMasukMapper {

    RegisterSuratMasukMapper INSTANCE = Mappers.getMapper(RegisterSuratMasukMapper.class);

    RegisterSuratMasukDTO toDTO(RegisterSuratMasuk entity);

    RegisterSuratMasuk toEntity(RegisterSuratMasukDTO dto);
}
