package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.go.kejaripalu.bdi.domain.util.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_telaahan_intelijen")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTelaahanIntelijen extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -340151013982819753L;

	@Column(name = "tanggal", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT+8")
	private Date tanggal;

	@Column(name = "nomor", nullable = false, unique = true)
	private String nomor;
	
	@Column(name = "pembuat", nullable = false)
	private String pembuat;
	
	@Column(name = "perihal", nullable = false)
	private String perihal;
	
	@Column(name = "lampiran")
	private String lampiran;
	
	@Column(name = "tindak_lanjut", columnDefinition="TEXT")
	private String tindakLanjut;
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_file", columnDefinition="TEXT")
	private String urlFile;
	
}
