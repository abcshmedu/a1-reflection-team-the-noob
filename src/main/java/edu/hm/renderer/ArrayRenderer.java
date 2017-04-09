package edu.hm.renderer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * ArrayRenderer class. Renders object's fields of type array.
 * 
 * @author Dmitry Dorodnov
 *
 */
public class ArrayRenderer {

  static String render(final Field f, final Object o) {
    StringBuilder result = new StringBuilder();

    if (f.getType().isArray()) {
      result.append(f.getName())
            .append(" (Type ")
            .append(f.getType().getSimpleName())
            .append("): [");

      Object array;
      try {
        array = f.get(o);
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
          result.append(Array.get(array, i)).append(", ");
        }
      } catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
      result.append("]\n");
    }

    return result.toString();
  }

}
