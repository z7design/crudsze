package com.tr.api.controllers;

import com.tr.domain.entities.Document;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.DocumentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

  @Autowired private DocumentService service;

  @GetMapping
  public List<Document> findAllByDocument() {
    return service.findAllByDocument();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{documentId}")
  public Document getBydocumentId(@PathVariable Long documentId) {
    return service.findDocumentById(documentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{documentId}")
  public Document update(@PathVariable Long documentId, @RequestBody Document document) {
    Document documentCurrent = service.findDocumentById(documentId);
    BeanUtils.copyProperties(document, documentCurrent, "documentId");
    return service.updateDocument(documentCurrent);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping
  public ResponseEntity<Document> createDocument(@RequestBody Document document) {
    try {
      document = service.createDocument(document);
      return ResponseEntity.status(HttpStatus.CREATED).body(document);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{documentId}")
  public void deleteDocument(@PathVariable Long documentId) {
    service.deleteDocument(documentId);
  }
}
