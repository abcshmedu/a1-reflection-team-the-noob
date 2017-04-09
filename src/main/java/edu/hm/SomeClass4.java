package edu.hm;

import java.util.Date;

import edu.hm.renderer.RenderMe;

/**
 * 
 * @author Dmitry Dorodnov
 *
 */
public class SomeClass4 {

	@RenderMe
	private int foo;

	@RenderMe(with = "edu.hm.renderer.ArrayRenderer")
	int[] array = { 1, 2, 3, };

	@RenderMe
	private Date date = new Date(123456789);

	public SomeClass4(int foo) {
		this.foo = foo;
	}

	@RenderMe
	int getInt() {
		return 777;
	}

	@RenderMe(with = "edu.hm.renderer.ArrayRenderer")
	int[] getArray() {
		return new int[] { 53, 17, 35, };
	}
	
	@RenderMe
	String getString() {
		return "Jack Richer";
	}

	
}
