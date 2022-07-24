package id.go.kejaripalu.bdi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "penomoran")
@Data
public class Penomoran {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "kode_surat", nullable = false, unique = true)
	@NotBlank
	private String kodeSurat;
	
	@Column(name = "nomor", nullable = false)
	private Integer nomor = 1;
	
	@Column(name = "jenis", nullable = false)
	@NotBlank
	private String jenis;
	
}
