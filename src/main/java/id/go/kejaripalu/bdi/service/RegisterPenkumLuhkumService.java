package id.go.kejaripalu.bdi.service;

import org.springframework.data.domain.Page;

import id.go.kejaripalu.bdi.domain.RegisterPenkumLuhkum;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumRequest;
import id.go.kejaripalu.bdi.dto.RegisterPenkumLuhkumResponse;

public interface RegisterPenkumLuhkumService {

	void create(RegisterPenkumLuhkumRequest request);

	void update(String ids, RegisterPenkumLuhkumRequest request);

	Page<RegisterPenkumLuhkum> findAll(String start, String end, String stringJenisPenkumLuhkum, Integer pages, Integer sizes);

	Page<RegisterPenkumLuhkum> findBySearching(String start, String end, String stringJenisPenkumLuhkum, String value, Integer pages, Integer sizes);

	RegisterPenkumLuhkumResponse findByIds(String ids);

	void delete(String ids);

}
