package id.go.kejaripalu.bdi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.go.kejaripalu.bdi.domain.Penomoran;
import id.go.kejaripalu.bdi.exception.NotFoundException;
import id.go.kejaripalu.bdi.repository.PenomoranRepository;
import id.go.kejaripalu.bdi.service.PenomoranService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PenomoranServiceImpl implements PenomoranService {

	private PenomoranRepository penomoranRepository;
	
	@Override
	@Transactional
	public List<Penomoran> findAlList() {
		return penomoranRepository.findAll();
	}
	
	@Override
	@Transactional
	public Penomoran getByKodeSurat(String kodeSurat) {
		Penomoran nomor= penomoranRepository.findByKodeSurat(kodeSurat)
				.orElseThrow(() -> new NotFoundException("Kode Surat tidak ditemukan!!!"));
		return nomor;
	}

	@Override
	@Transactional
	public void createNewNomor(Penomoran nomor) {
		penomoranRepository.save(nomor);
	}

	@Override
	@Transactional
	public void updateNomor(String kode, Penomoran nomor) {
		Penomoran penomoran= penomoranRepository.findByKodeSurat(kode)
				.orElseThrow(() -> new NotFoundException("Kode Surat tidak ditemukan!!!"));
		penomoran.setKodeSurat(
				nomor.getKodeSurat() == null || nomor.getKodeSurat().isBlank() ?
						penomoran.getKodeSurat() : nomor.getKodeSurat());
		penomoran.setNomor(
				nomor.getNomor() == null ?
						penomoran.getNomor() : nomor.getNomor());
		penomoran.setJenis(
				nomor.getJenis()== null || nomor.getJenis().isBlank() ?
						penomoran.getJenis() : nomor.getJenis());
		penomoranRepository.save(penomoran);
	}

	@Override
	@Transactional
	public void deleteNomor(String kode) {
		Penomoran nomor= penomoranRepository.findByKodeSurat(kode)
				.orElseThrow(() -> new NotFoundException("Kode Surat tidak ditemukan!!!"));
		penomoranRepository.delete(nomor);
	}

}
