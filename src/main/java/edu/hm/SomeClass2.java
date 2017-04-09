package edu.hm;

import java.util.Date;

import edu.hm.renderer.RenderMe;

/**
 * 
 * @author Dmitry Dorodnov
 *
 */
public class SomeClass2 {
	
	@RenderMe private int foo;
		
	@RenderMe(with="edu.hm.renderer.ArrayRenderer") String[] array = {"Horst", "Walter", "Tobin", };
	
	@RenderMe(with="edu.hm.renderer.ArrayRenderer") int[] array2 = {1, 2, 3, };
	
	@RenderMe private Date date = new Date(123456789);
	
	
	public SomeClass2(int foo/*, int[] array, Date date*/) {
		this.foo = foo;
//		this.array = array;
//		this.date = date;
	}
}
