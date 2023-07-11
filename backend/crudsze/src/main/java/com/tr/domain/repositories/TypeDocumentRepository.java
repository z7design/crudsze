package com.tr.domain.repositories;

import com.tr.domain.entities.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {}
