package com.tr.domain.Enums.Converters;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;
import com.tr.domain.Enums.Nickname;

public class NicknameConverter implements AttributeConverter<Nickname, String> {

  @Override
  public String convertToDatabaseColumn(Nickname nickname) {
    if (nickname == null) {
      return null;
    }
    return nickname.getValue();
  }

  @Override
  public Nickname convertToEntityAttribute(String value) {
    if (value == null) {
      return null;
    }
    return Stream.of(Nickname.values())
        .filter(n -> n.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalAccessError::new);
  }
}

