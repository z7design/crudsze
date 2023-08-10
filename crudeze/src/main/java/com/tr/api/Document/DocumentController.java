package com.tr.api.Document;

import com.tr.domain.Document.DocumentEntity;
import com.tr.domain.Document.DocumentService;
import com.tr.domain.exception.EntityNotFoundException;
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
  public List<DocumentEntity> findAllByDocument() {
    return service.findAllByDocument();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{documentId}")
  public DocumentEntity getBydocumentId(@PathVariable Long documentId) {
    return service.findDocumentById(documentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{documentId}")
  public DocumentEntity update(@PathVariable Long documentId, @RequestBody DocumentEntity document) {
    DocumentEntity documentCurrent = service.findDocumentById(documentId);
    BeanUtils.copyProperties(document, documentCurrent, "documentId");
    return service.updateDocument(documentCurrent);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping
  public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document) {
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
