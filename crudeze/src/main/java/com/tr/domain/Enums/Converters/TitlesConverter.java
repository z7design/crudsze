package com.tr.domain.Enums.Converters;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;
import com.tr.domain.Enums.TypeTitle;

public class TitlesConverter implements AttributeConverter<TypeTitle, String> {
@Override
  public String convertToDatabaseColumn(TypeTitle typeTitle) {
    if (typeTitle == null) {
      return null;
    }
    return typeTitle.getValue();
  }

  @Override
  public TypeTitle convertToEntityAttribute(String value) {
    if (value == null) {
      return null;
    }
    return Stream.of(TypeTitle.values())
        .filter(n -> n.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalAccessError::new);
  }
}


