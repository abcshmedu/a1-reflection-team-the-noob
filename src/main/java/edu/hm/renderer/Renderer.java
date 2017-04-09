package edu.hm.renderer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Renderer class. Renders object's fields and methods, which are marked with
 * RenderMe annotation.
 * 
 * @author Dmitry Dorodnov
 *
 */
public class Renderer {

  private Object elementToRender;

  public Renderer(Object o) {
    this.elementToRender = o;
  }

  /**
   * Renders objects fields and methods.
   * @return String representation of object's fields and methods
   */
  public String render() {
    StringBuilder result = new StringBuilder("Instance of ");

    Class<?> cut = this.elementToRender.getClass();

    result.append(cut.getName());
    result.append(":\n");

    Field[] fields = cut.getDeclaredFields();

    try {
      for (Field f : fields) {
        RenderMe a = f.getAnnotation(edu.hm.renderer.RenderMe.class);
        if (a != null) {
          f.setAccessible(true);

          String className = a.with();

          if (!"".equals(className)) {

            Class<?> tuc = Class.forName(a.with());
            if (tuc != null) {
              Method method = tuc.getDeclaredMethod("render", Field.class, Object.class);
              if (method != null) {
                result.append(method.invoke(tuc, f, this.elementToRender));
              }
            }
          } else {
            result.append(f.getName()).append(" (Type ")
            .append((f.getType()).getName()).append("): ")
                .append((f.get(this.elementToRender)).toString()).append("\n");
          }

        }
      }

      Method[] methods = cut.getDeclaredMethods();
      for (Method m : methods) {
        RenderMe rm = m.getAnnotation(edu.hm.renderer.RenderMe.class);
        if (rm != null) {
          m.setAccessible(true);
          try {
            result.append(m.getName()).append(" (Type method): ");
            Object ob = m.invoke(this.elementToRender, (Object[]) null);

            if (m.getReturnType().isArray()) {
              result.append("[");
              int length = Array.getLength(ob);
              for (int i = 0; i < length; i++) {
                result.append(Array.get(ob, i)).append(", ");
              }
              result.append("]");
            } else {
              result.append(ob);
            }

            result.append("\n");

          } catch (InvocationTargetException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (ClassNotFoundException
        | NoSuchMethodException | SecurityException | InvocationTargetException
        | IllegalAccessException | IllegalArgumentException e) {
      e.printStackTrace();
    }

    return result.toString();
  }
}
