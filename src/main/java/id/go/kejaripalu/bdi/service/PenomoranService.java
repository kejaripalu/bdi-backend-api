package id.go.kejaripalu.bdi.service;

import java.util.List;

import id.go.kejaripalu.bdi.domain.Penomoran;

public interface PenomoranService {

	List<Penomoran> findAlList();
	Penomoran getByKodeSurat(String kodeSurat);
	void createNewNomor(Penomoran nomor);
	void updateNomor(String kode, Penomoran nomor);
	void deleteNomor(String kode);
 	
}
