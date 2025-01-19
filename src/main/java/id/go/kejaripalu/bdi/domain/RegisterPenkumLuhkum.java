package id.go.kejaripalu.bdi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.kejaripalu.bdi.domain.util.JenisPenkumLuhkum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_penkum_luhkum")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class RegisterPenkumLuhkum implements Serializable {
	
	private static final long serialVersionUID = -7493255284311796254L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@UuidGenerator
	@Column(name = "id_secure", nullable = false, updatable = false, unique = true)
	private String ids;
	
	@Column(name = "nomor_surat_perintah", nullable = false, unique = true)
	private String nomorSuratPerintah;
	
	@Column(name = "sasaran_kegiatan", nullable = false)
	private String sasaranKegiatan;
	
	@Column(name = "tanggal", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date tanggalKegiatan;
	
	@Column(name = "materi", nullable = false)
	private String materi;
	
	@Column(name = "jumlahPeserta", nullable = false)
	private int jumlahPeserta = 0;	
	
	@Column(name = "keterangan")
	private String keterangan;
	
	@Column(name = "url_foto_1", columnDefinition="TEXT")
	private String urlFoto1;	

	@Column(name = "url_foto_2", columnDefinition="TEXT")
	private String urlFoto2;
	
	@Column(name = "url_foto_3", columnDefinition="TEXT")
	private String urlFoto3;
	
	@Column(name = "url_foto_4", columnDefinition="TEXT")
	private String urlFoto4;

	@Column(name = "jenis_penkum_luhkum")
	@Enumerated(EnumType.STRING)
	private JenisPenkumLuhkum jenisPenkumLuhkum = JenisPenkumLuhkum.PENERANGAN_HUKUM;
	
	@JsonIgnore
	private boolean deleted = false;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(updatable = false, name = "create_time")
	private LocalDateTime createAt;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateAt;

}
