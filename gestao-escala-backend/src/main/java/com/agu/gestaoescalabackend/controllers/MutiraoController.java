package com.agu.gestaoescalabackend.controllers;

import com.agu.gestaoescalabackend.dto.MutiraoDTO;
import com.agu.gestaoescalabackend.dto.PautaDto;
import com.agu.gestaoescalabackend.enums.GrupoPautista;
import com.agu.gestaoescalabackend.repositories.MutiraoRepository;
import com.agu.gestaoescalabackend.services.MutiraoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mutirao")
@AllArgsConstructor
public class MutiraoController {

	private MutiraoService mutiraoService;
	private MutiraoRepository mutiraoRepository;

	@GetMapping
	public ResponseEntity<List<MutiraoDTO>> pesquisarTodos() {
		List<MutiraoDTO> list = mutiraoService.findAll();
		return ResponseEntity.ok(list);
	}
	
//	@GetMapping("/{mutiraoId}")
//	public ResponseEntity<MutiraoDTO> pesquisarEspecifico (@PathVariable Long mutiraoId) {
//		MutiraoDTO mutiraoDto = service.pesquisarEspecifico(mutiraoId);
//		return ResponseEntity.ok(mutiraoDto);
//	}
	
	@GetMapping("/{mutiraoId}/pautas")
	public ResponseEntity<List<PautaDto>> pesquisarPautasDoMutirao(@PathVariable Long mutiraoId) {
		List<PautaDto> list = mutiraoService.findPautas(mutiraoId);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<MutiraoDTO> salvar(@RequestBody MutiraoDTO mutiraoDto) {
		mutiraoDto = mutiraoService.save(mutiraoDto);
		if (mutiraoDto != null)
			return ResponseEntity.ok().body(mutiraoDto);
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/{mutiraoId}")
	public ResponseEntity<MutiraoDTO> editar(@PathVariable Long mutiraoId, @RequestBody MutiraoDTO mutiraoDto) {
		mutiraoDto = mutiraoService.update(mutiraoId, mutiraoDto);
		if (mutiraoDto != null)
			return ResponseEntity.ok().body(mutiraoDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{mutiraoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long mutiraoId) {
		mutiraoService.excluir(mutiraoId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{pautaDeAudienciaId}/{procuradorId}")
	public ResponseEntity<List<PautaDto>> atualizarProcurador(@PathVariable Long pautaDeAudienciaId,
														@PathVariable Long procuradorId) {
		List<PautaDto> pautaDtoList = mutiraoService.atualizarProcurador(pautaDeAudienciaId, procuradorId);
		if (pautaDtoList != null)
			return ResponseEntity.ok().body(pautaDtoList);
		else
			return ResponseEntity.notFound().build();
	}


	@PostMapping("/{mutiraoId}/{grupoPautista}")
	public List<PautaDto> gerarEscala(@PathVariable Long mutiraoId, @PathVariable GrupoPautista grupoPautista) {
		return mutiraoService.gerarEscala(mutiraoId, grupoPautista);
	}

	/*------------------------------------------------
    ACTIONS DE DESENVOLVIMENTO
    ------------------------------------------------*/

	@PutMapping("/truncate")
	@Transactional
	public ResponseEntity<Void> truncateMutirao() {
		mutiraoRepository.truncateTable();
		return ResponseEntity.noContent().build();
	}

}
