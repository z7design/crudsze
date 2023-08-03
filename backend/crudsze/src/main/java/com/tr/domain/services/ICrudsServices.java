package com.tr.domain.services;

import java.util.List;

public interface ICrudsServices<Req, Res>{
  List<Res> findAll();
  Res findById(Long id);
  Res save(Req DTO);
  Res update(Long id, Req DTO);
  void delete(Long id);
}
