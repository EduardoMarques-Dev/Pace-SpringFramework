package com.agu.gestaoescalabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscalaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String vara;
	private String dataInicial;
	private String dataFinal;

}
