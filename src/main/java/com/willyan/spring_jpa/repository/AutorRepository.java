package com.willyan.spring_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.willyan.spring_jpa.entity.Autor;
import com.willyan.spring_jpa.projection.AutorInfoProjection;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	@Query("select a from Autor a where a.infoAutor.cargo like :cargo order by a.nome asc")
	List<Autor> findByCargo(String cargo);

	@Query("select a from Autor a where a.nome like :termo OR a.sobrenome like :termo")
	List<Autor> findAllByNomeOrSobrenome(String string);

	@Query("""
			select a.nome as nome, a.sobrenome as sobrenome, a.infoAutor.cargo as cargo, a.infoAutor.bio as bio 
			from Autor a
			where a.id = :id
			""")
	AutorInfoProjection findAutorInfoById(@Param("id") Long id);
}