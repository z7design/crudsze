package com.tr.domain.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCustomRepository {

  @Autowired JdbcTemplate jdbcTemplate = null;
  /*
  public static List<User> listUser() {
    return jdbcTemplate.query(
        "Select * from users",
        (rs, rowNum) ->
            new User(rs.getInt("user_id"),
                rs.getString("name"),
                rs.getString("email")));
  }
  */

}
