package com.remondis.remap.interfaces;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTest {

  private static final String STRING = "aString";

  @Test
  void shouldSupportInterfaces() {
    Mapper<Source, DestImpl> mapper = Mapping.from(Source.class)
        .to(DestImpl.class)
        .reassign(Source::getString)
        .to(Destination::getStringField)
        .mapper();
    SrcImpl src = new SrcImpl(STRING);
    Destination dest = mapper.map(src);
    assertEquals(STRING, dest.getStringField());
  }

}
