package com.agu.gestaoescalabackend.entities;

import com.agu.gestaoescalabackend.dto.MutiraoDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_mutirao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mutirao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidaDePautas;
	private String vara;

	@Column(name = "data_inicial")
	private LocalDate dataInicial;

	@Column(name = "data_final")
	private LocalDate dataFinal;

	@Enumerated(value = EnumType.STRING)
	private TipoStatus status;

/////////////////  CONSTRUTOR  //////////////////

	// FRONT para BACK (Salvar)
	public Mutirao(MutiraoDTO dto) {
		this.id = dto.getId();
		this.vara = dto.getVara();
		this.dataInicial = dto.getDataInicial();
		this.dataFinal = dto.getDataFinal();
		this.status = dto.getStatus();
		this.quantidaDePautas = dto.getQuantidaDePautas();

	}

	// FRONT para BACK com ID (Editar)
	public Mutirao(Long id, MutiraoDTO dto) {
		this.id = id;
		this.vara = dto.getVara();
		this.dataInicial = dto.getDataInicial();
		this.dataFinal = dto.getDataFinal();
		this.status = dto.getStatus();
		this.quantidaDePautas = dto.getQuantidaDePautas();
	}

}
