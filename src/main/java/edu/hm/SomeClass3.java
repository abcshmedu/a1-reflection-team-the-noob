package edu.hm;

import edu.hm.renderer.RenderMe;

import java.util.Date;

/**
 * Test class for reflection renderer.
 * 
 * @author Dmitry Dorodnov
 *
 */
public class SomeClass3 {

  @RenderMe
  private int foo;

  @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
  String[] array = { "Horst", "Walter", "Tobin", };

  @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
  int[] array2 = { 1, 2, 3, };

  @RenderMe
  private Date date = new Date(987654321);

  @RenderMe
  private int foo2 = 345;

  public SomeClass3(int foo) {
    this.foo = foo;
  }
}
