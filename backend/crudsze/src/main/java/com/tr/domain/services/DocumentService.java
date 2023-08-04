package com.tr.domain.services;

import com.tr.domain.entities.Document;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.DocumentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentService {

  private static final String MSG_DOCUMENT_NOT_FOUND =
      "There is no Document registration with the code %d";
  private static final String MSG_DOCUMENT_IN_USE =
      "Code Document %d cannot be removed as it is in use";

  @Autowired private DocumentRepository repository;

  @Transactional
  public Document createDocument(final Document document) {
    return repository.save(document);
  }

  @Transactional
  public Document findDocumentById(final Long documentId) {
    return repository
        .findById(documentId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_DOCUMENT_NOT_FOUND, documentId)));
  }

  @Transactional
  public Document updateDocument(final Document document) {
    Document entity =
        repository
            .findById(document.getDocumentId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setNumber(entity.getNumber());
    entity.setDescription(entity.getDescription());
    entity.setDueDate(entity.getDueDate());
    entity.setGovernmentAgency(entity.getGovernmentAgency());
    entity.setObligororiness(entity.getObligororiness());

    return repository.save(document);
  }

  public void deleteDocument(Long documentId) {
    try {
      repository.deleteById(documentId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_DOCUMENT_NOT_FOUND, documentId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_DOCUMENT_IN_USE, documentId));
    }
  }

  public List<Document> findAllByDocument() {
    return repository.findAll();
  }
}
