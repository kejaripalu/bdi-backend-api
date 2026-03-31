package id.go.kejaripalu.bdi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class Siabidibam extends BaseEntity {

    @Column(name = "siapa", nullable = false)
    private String siapa;

    @Column(name = "apa", columnDefinition="TEXT", nullable = false)
    private String apa;

    @Column(name = "mengapa", columnDefinition="TEXT", nullable = false)
    private String mengapa;

    @Column(name = "bagaimana", columnDefinition="TEXT", nullable = false)
    private String bagaimana;

}
