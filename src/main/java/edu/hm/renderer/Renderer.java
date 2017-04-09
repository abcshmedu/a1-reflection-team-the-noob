package edu.hm.renderer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Dmitry Dorodnov
 *
 */
public class Renderer {

	private Object o;

	public Renderer(Object o) {
		this.o = o;
	}

	public String render() throws IllegalArgumentException, IllegalAccessException {
		StringBuilder result = new StringBuilder("Instance of ");
		

		Class<?> cut = this.o.getClass();
		
		result.append(cut.getName());
		result.append(":\n");
		
		Field[] fields = cut.getDeclaredFields();

		for (Field f : fields) {
			RenderMe a = f.getAnnotation(edu.hm.renderer.RenderMe.class);
			if (a != null) {				
				f.setAccessible(true);
				
				String className = a.with();
				if (!"".equals(className)) {
					try {
						Class<?> tuc = Class.forName(a.with());
						if (tuc != null) {
							Method method = tuc.getDeclaredMethod("render", Field.class, Object.class);
							if (method != null) {
								result.append(method.invoke(tuc, f, this.o));
							}
						}
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} else {
					result.append(f.getName())
					.append(" (Type ")
					.append((f.getType()).getName())
					.append("): ")
					.append((f.get(this.o)).toString())
					.append("\n");
				}				
			}
		}
		
		Method[] methods = cut.getDeclaredMethods();
		for (Method m : methods) {
			RenderMe rm = m.getAnnotation(edu.hm.renderer.RenderMe.class);
			if (rm != null) {				
				m.setAccessible(true);
				try {					
					result.append(m.getName())
					.append(" (Type method): ");
					Object ob = m.invoke(this.o, null);
					
					if(m.getReturnType().isArray()){
						result.append("[");
						int length = Array.getLength(ob);
						for (int i = 0; i < length; i++)
						{
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

		return result.toString();
	}	
}
