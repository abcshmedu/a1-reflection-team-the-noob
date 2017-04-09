package edu.hm.renderer;

import java.lang.annotation.*;

/**
 * 
 * @author Dmitry Dorodnov
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {
	String with() default "";
}
