package com.pgoliveira.minhasfinancas.service;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pgoliveira.minhasfinancas.exception.RegraNegocioException;
import com.pgoliveira.minhasfinancas.model.entity.Usuario;
import com.pgoliveira.minhasfinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveValidarEmail() {
		// cenário
		repository.deleteAll();

		// ação
		assertDoesNotThrow(()-> {
			service.validarEmail("email@email.com");
		});
	}

	@Test()
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		// cenário
		Usuario usuario = Usuario.builder().nome("usuaruio").email("usuario@email.com").build();
		repository.save(usuario);

		// ação
		assertThrows(RegraNegocioException.class, () -> {
			service.validarEmail("usuario@email.com");
		});
	}
}
