package com.example.projetoescola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.models.Usuario;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;
import com.example.projetoescola.repositories.UsuarioRepository;
import com.example.projetoescola.security.JwtService;

import java.util.List;

@SpringBootApplication
public class ProjetoescolaApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired CursoRepository cursoRepository,
			@Autowired CategoriaCursoRepository categoriaCursoRepository) {
		return args -> {
			System.out.println("*** CRIANDO AS CATEGORIAS ***");
			CategoriaCurso c1 = categoriaCursoRepository.save(
					new CategoriaCurso(0, "Tecnólogo"));
			CategoriaCurso c2 = categoriaCursoRepository.save(
					new CategoriaCurso(0, "Bacharel"));
			System.out.println("*** LISTANDO AS CATEGORIAS ***");

			List<CategoriaCurso> listaCategorias = categoriaCursoRepository.findAll();
			listaCategorias.stream().map(c -> c.getNome()).forEach(System.out::println);

			System.out.println("*** CRIANDO OS CURSOS ***");
			cursoRepository.save(
					new Curso(0L, "ADS", 2000, listaCategorias.get(0)));
			cursoRepository.save(
					new Curso(0L, "SI", 2050, listaCategorias.get(1)));
			System.out.println("*** LISTANDO OS CURSOS ***");

			List<Curso> listaCursos = cursoRepository.findAll();
			listaCursos.stream().map(x -> x.getNome()).forEach(System.out::println);

			System.out.println("*** LISTAR POR NOME ***");
			listaCursos = cursoRepository.findByNome("ADS");
			listaCursos.stream().map(x -> x.getNome())
					.forEach(System.out::println);

			System.out.println("*** TESTANDO LAZY E EAGER ***");
			/*
			 * List<CategoriaCurso> categs = categoriaCursoRepository.findAll();
			 * for (CategoriaCurso ca : categs) {
			 * System.out.println(ca.getId() + " - " + ca.getNome() + "qtde cursos: " +
			 * ca.getCursos().size());
			 * }
			 */
			CategoriaCurso cc = categoriaCursoRepository
					.findCategoriaCursoFetchCursos((long) 1);
			System.out.println(cc.getCursos().size());

		};
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = SpringApplication.run(ProjetoescolaApplication.class);
		JwtService service = contexto.getBean(JwtService.class);
		UsuarioRepository usuarioRepository = contexto.getBean(UsuarioRepository.class);
		PasswordEncoder passwordEncoder = contexto.getBean(PasswordEncoder.class);

		Usuario usuario = new Usuario(0, "Rafael", "rafael.moreno@facens.br", passwordEncoder.encode("123"),
				"Administrador");
		String token = service.gerarToken(usuario);
		System.out.println(token);
		boolean isValid = service.validarToken(token);
		System.out.println("Token válido? " + isValid);
		System.out.println("Usuário: " + service.obterLoginUsuario(token));

		usuarioRepository.save(usuario);
	}

}
