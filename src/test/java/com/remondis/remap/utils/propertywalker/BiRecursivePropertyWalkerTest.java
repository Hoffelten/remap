package com.remondis.remap.utils.propertywalker;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.remondis.remap.basic.A;
import com.remondis.remap.basic.B;
import com.remondis.remap.utils.property.walker.BiRecursivePropertyWalker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BiRecursivePropertyWalkerTest {

  @Test
  void shouldTraverseProperties() {
    Function<A, String> getter = A::getString;
    BiConsumer<A, String> setter = A::setString;

    BiRecursivePropertyWalker<A, A> walker = BiRecursivePropertyWalker.create(A.class)
        .addProperty(A::getString, A::setString, access -> {
          access.sourceProperty()
              .set("changed1");
          access.targetProperty()
              .set("changed2");
        })
        .goInto(A::getB, A::setB, B.class)
        .addProperty(B::getString, B::setString, access -> {
          access.sourceProperty()
              .set("changed3");
          access.targetProperty()
              .set("changed4");
        })
        .build();
    A a1 = new A("moreInA1", "stringA1", 1, 1, 1L, new B("stringB1", 1, 1));
    A a2 = new A("moreInA2", "stringA2", 2, 2, 2L, new B("stringB2", 2, 2));
    walker.execute(a1, a2);

    assertEquals("changed1", a1.getString());
    assertEquals("changed2", a2.getString());
    assertEquals("changed3", a1.getB()
        .getString());
    assertEquals("changed4", a2.getB()
        .getString());
  }
}
