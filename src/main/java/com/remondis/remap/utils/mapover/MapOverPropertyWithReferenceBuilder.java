package com.remondis.remap.utils.mapover;

import java.util.function.BiConsumer;
import java.util.function.Function;

import com.remondis.remap.utils.property.visitor.MapOverReferenceVisitor;

public class MapOverPropertyWithReferenceBuilder<M extends MapOverWithReference<R, T>, R, T, TT>
    extends MapOverPropertyWithoutReferenceBuilder<M, R, T, TT> {

  public MapOverPropertyWithReferenceBuilder(M mapOver, Function<T, TT> propertyExtractor,
      BiConsumer<T, TT> propertyWriter) {
    super(mapOver, propertyExtractor, propertyWriter);
  }

  public <ID> M byReference(Function<TT, ID> idExtractor) {
    mapOver.getWalker()
        .addProperty(propertyExtractor, propertyWriter,
            new MapOverReferenceVisitor<>(mapOver.getEntityManager(), idExtractor));
    return mapOver;
  }
}
