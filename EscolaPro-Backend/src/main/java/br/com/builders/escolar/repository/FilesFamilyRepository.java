package br.com.builders.escolar.repository;

import org.springframework.stereotype.Repository;

import br.com.builders.escolar.model.files.BasicFilesFamily;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FilesFamilyRepository extends JpaRepository<BasicFilesFamily, Long>{
     Optional<BasicFilesFamily> findByReference(String reference);
}
