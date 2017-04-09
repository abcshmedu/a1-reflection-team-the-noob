package edu.hm.renderer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for renderer class.
 * 
 * @author Dmitry Dorodnov
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {
  
  /**
   * Renderer name for non primitive data types.
   * 
   * @return String
   */
  String with() default "";
}
