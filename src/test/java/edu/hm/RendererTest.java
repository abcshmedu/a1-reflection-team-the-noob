package edu.hm;

import static org.junit.Assert.assertEquals;

import edu.hm.renderer.Renderer;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Test class for renderer.
 * 
 * @author Dmitry Dorodnov
 *
 */
@RunWith(Parameterized.class)
public class RendererTest {

  private Renderer renderer;
  private String expected;

  /**
   * Test data and expected data.
   * 
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {     
      { 
          "Instance of edu.hm.SomeClass:\n"
          + "foo (Type int): 5\n"
          + "array (Type int[]): [1, 2, 3, ]\n"
          + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
          new Renderer(new SomeClass(5))
      },
      { 
          "Instance of edu.hm.SomeClass2:\n"
          + "foo (Type int): 2\n"
          + "array (Type String[]): [Horst, Walter, Tobin, ]\n"
          + "array2 (Type int[]): [1, 2, 3, ]\n"
          + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
          new Renderer(new SomeClass2(2))
      },
      { 
          "Instance of edu.hm.SomeClass3:\n"
          + "foo (Type int): 297\n"
          + "array (Type String[]): [Horst, Walter, Tobin, ]\n"
          + "array2 (Type int[]): [1, 2, 3, ]\n"
          + "date (Type java.util.Date): Mon Jan 12 11:20:54 CET 1970\n"
          + "foo2 (Type int): 345\n",
          new Renderer(new SomeClass3(297))
      },
      { 
          "Instance of edu.hm.SomeClass4:\n"
          + "foo (Type int): 297\n"
          + "array (Type int[]): [1, 2, 3, ]\n"
          + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
          + "getInt (Type method): 777\n"
          + "getArray (Type method): [53, 17, 35, ]\n"
          + "getString (Type method): Jack Richer\n",
          new Renderer(new SomeClass4(297))
        },
    });
  }

  public RendererTest(String expected, Renderer renderer) {
    this.renderer = renderer;
    this.expected = expected;
  }

  @Test
  public void testRendering() throws Exception {
    assertEquals(this.expected, this.renderer.render());
  }
}
