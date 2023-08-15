package unl.soc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A JUnit test suite for methods in the {@link ColorUtils} class. 
 * 
 * In a JUnit test class there is no <code>main</code> method.
 * Instead, *annotations* (the <code>@Test</code> for
 * example) are used to designate which method(s) are JUnit
 * tests.  Annotations do not affect code, but instead allow
 * tools (such as JUnit) to recognize and affect the code.
 * This is part of "Aspect Oriented Programming"
 * 
 * JUnit will automatically make this a runnable file in most
 * IDEs, just hit the "play" button in Eclipse for example and
 * JUnit will execute all the tests and produce a report.
 * 
 */
public class ColorUtilsTests {

	/**
	 * Default tolerance for differences in floating point values.
	 */
	public static final double DELTA = 0.0001;

	/**
	 * Tests the {@link ColorUtils#rgbIntToFloat()} method using
	 * a {@link #DELTA} tolerance.
	 */
	@Test
	public void testRgbIntToFloat001() {

		int c = 123;
		double expected = 0.4823;
		double actual = ColorUtils.rgbIntToFloat(c);
		assertEquals(expected, actual, DELTA);
	}

	/**
	 * Tests the {@link ColorUtils#rgbIntToFloat()} method using
	 * a {@link #DELTA} tolerance.
	 */
	@Test
	public void testRgbIntToFloat002() {

		int c = 53;
		double expected = 0.2078;
		double actual = ColorUtils.rgbIntToFloat(c);
		assertEquals(expected, actual, DELTA);
	}

	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * all permutations of 10, 20, 30 on the {@link ColorUtils#max()}
	 * method as well as repeated values.
	 * 
	 * @param argumentsAccessor
	 */
	@ParameterizedTest
	@CsvSource({"10,20,30,30", 
		        "10,30,20,30",
		        "20,10,30,30",
		        "20,30,10,30",
		        "30,10,20,30",
		        "30,20,10,30",
		        "10,10,30,30",
		        "10,30,10,30",
		        "30,10,10,30",
		        "30,30,30,30"})
	public void testMax(ArgumentsAccessor argumentsAccessor) {
		int a = argumentsAccessor.get(0, Integer.class);
		int b = argumentsAccessor.get(1, Integer.class);
		int c = argumentsAccessor.get(2, Integer.class);
		int expected = argumentsAccessor.get(3, Integer.class);
		int actual = ColorUtils.max(a, b, c);
	    assertEquals(expected, actual);
	}
	
	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * all permutations of 10, 20, 30 on the {@link ColorUtils#max()}
	 * method as well as repeated values.
	 * 
	 * @param argumentsAccessor
	 */
	@ParameterizedTest
	@CsvSource({"10,20,30,10", 
		        "10,30,20,10",
		        "20,10,30,10",
		        "20,30,10,10",
		        "30,10,20,10",
		        "30,20,10,10",
		        "10,10,30,10",
		        "10,30,10,10",
		        "30,10,10,10",
		        "30,30,30,30"})
	public void testMin(ArgumentsAccessor argumentsAccessor) {
		int a = argumentsAccessor.get(0, Integer.class);
		int b = argumentsAccessor.get(1, Integer.class);
		int c = argumentsAccessor.get(2, Integer.class);
		int expected = argumentsAccessor.get(3, Integer.class);
		int actual = ColorUtils.min(a, b, c);
	    assertEquals(expected, actual);
	}
	
	//SEPIA TESTS

	/**
	 * JUnit test for the {@link ColorUtils#toSepia(RGB)} method using a single,
	 * hardcoded {@link RGB} value: Steel Blue (70, 130, 180)
	 */
	@Test
	public void testToSepia001() {
		// steel blue
		RGB steelBlue = new RGB(70, 130, 180);
		RGB expected = new RGB(162, 144, 112);
		RGB actual = ColorUtils.toSepia(steelBlue);
		assertEquals(expected, actual);
	}
	
	/**
	 * JUnit test for the {@link ColorUtils#toSepia(RGB)} method using an
	 * invalid {@link RGB} value to test that it properly throws an 
	 * {@link IllegalArgumentException}
	 */
	@Test
	public void testToSepiaOutOfBounds() {
		RGB input = new RGB(-1, 0, 0);
		assertThrows(IllegalArgumentException.class, () -> {
	        ColorUtils.toSepia(input);
	    });
	}
	
	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * the {@link ColorUtils#toSepia(RGB)} method using a series of
	 * invalid {@link RGB} values to test that it properly throws an 
	 * {@link IllegalArgumentException}
	 */
	@ParameterizedTest
	@CsvSource({"-1,0,0", 
		        "0,-1,0",
		        "0,0,-1",
		        "256,0,0",
		        "0,256,0",
		        "0,0,256"})
	public void testToSepiaOutOfBoundsParam(ArgumentsAccessor argumentsAccessor) {
		int r = argumentsAccessor.get(0, Integer.class);
		int g = argumentsAccessor.get(1, Integer.class);
		int b = argumentsAccessor.get(2, Integer.class);
		RGB input = new RGB(r,g,b);
		assertThrows(IllegalArgumentException.class, () -> {
	        ColorUtils.toSepia(input);
	    });
	}
	
	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * the {@link ColorUtils#toSepia(RGB)} method using a series of
	 * valid {@link RGB} values.
	 */
	@ParameterizedTest
	@CsvSource({"123, 100,  20, 129, 115,  89", 
		        "200, 128,  64, 189, 168, 131",
		        " 16,  32,  64,  43,  38,  30",
		        " 90, 190, 228, 225, 200, 156",
		        " 12,  36,  75,  47,  41,  32",
		        " 47,  41,  32,  56,  50,  39",
		        " 56, 187, 132, 191, 170, 132",
		        " 43,  23,  43,  43,  38,  30",
		        "125, 125, 125, 169, 150, 117"
	})
	public void testToSepiaParam(ArgumentsAccessor argumentsAccessor) {
		int r = argumentsAccessor.get(0, Integer.class);
		int g = argumentsAccessor.get(1, Integer.class);
		int b = argumentsAccessor.get(2, Integer.class);
		RGB input = new RGB(r,g,b);
		int expectedR = argumentsAccessor.get(3, Integer.class);
		int expectedG = argumentsAccessor.get(4, Integer.class);
		int expectedB = argumentsAccessor.get(5, Integer.class);
		RGB expected = new RGB(expectedR, expectedG, expectedB);
		RGB actual = ColorUtils.toSepia(input);
	    assertEquals(expected, actual);
	}
	
	//GRAY SCALE TESTS
	
	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * the {@link ColorUtils#toGrayScale(RGB, GrayScaleMode)} method using a series of
	 * invalid {@link RGB} value to test that it properly throws an 
	 * {@link IllegalArgumentException}
	 */
	@ParameterizedTest
	@CsvSource({"-1,0,0", 
		        "0,-1,0",
		        "0,0,-1",
		        "256,0,0",
		        "0,256,0",
		        "0,0,256"})
	public void testToGrayScaleOutOfBoundsParam(ArgumentsAccessor argumentsAccessor) {
		int r = argumentsAccessor.get(0, Integer.class);
		int g = argumentsAccessor.get(1, Integer.class);
		int b = argumentsAccessor.get(2, Integer.class);
		RGB input = new RGB(r,g,b);
		assertThrows(IllegalArgumentException.class, () -> {
	        ColorUtils.toGrayScale(input, GrayScaleMode.AVERAGE);
	    });
	}
		
	/**
	 * JUnit test for the {@link ColorUtils#toGrayScale(RGB, GrayScaleMode)} method using a single,
	 * hardcoded {@link RGB} value: Steel Blue (70, 130, 180) and the {@link GrayScaleMode#AVERAGE}
	 * method.
	 */
	@Test
	public void testToGrayScale001() {
		// steel blue
		RGB steelBlue = new RGB(70, 130, 180);
		RGB expected = new RGB(127,127,127);
		RGB actual = ColorUtils.toGrayScale(steelBlue, GrayScaleMode.AVERAGE);
		assertEquals(expected, actual);
	}

	/**
	 * A JUnit parameterized test with CSV source arguments testing
	 * multiple {@link RGB} values and {@link GrayScaleMode}s on
	 * the {@link ColorUtils#toGrayScale(RGB, GrayScaleMode)} method.
	 * 
	 * @param argumentsAccessor
	 */
	@ParameterizedTest
	@CsvSource({"255, 0, 0,    AVERAGE,  85,  85,  85", 
		        "255, 0, 0,  LIGHTNESS, 128, 128, 128",
		        "255, 0, 0, LUMINOSITY,  54,  54,  54",
		        "0, 255, 0,    AVERAGE,  85,  85,  85",
		        "0, 255, 0,  LIGHTNESS, 128, 128, 128",
		        "0, 255, 0, LUMINOSITY, 184, 184, 184",
		        "0, 0, 255,    AVERAGE,  85,  85,  85",
		        "0, 0, 255,  LIGHTNESS, 128, 128, 128",
		        "0, 0, 255, LUMINOSITY,  18,  18,  18",
		        " 42,  17,   9,    AVERAGE,  23,  23,  23",
		        "250, 128, 212,    AVERAGE, 197, 197, 197",
		        " 11, 204, 160,    AVERAGE, 125, 125, 125",
		        "218, 232, 249,    AVERAGE, 233, 233, 233",
		        "130, 248,   0,    AVERAGE, 126, 126, 126",
		        " 35, 102, 157,    AVERAGE,  98,  98,  98",
		        "  4,  10, 180,    AVERAGE,  65,  65,  65",
		        " 71,  77, 227,    AVERAGE, 125, 125, 125",
		        "233, 119, 131,    AVERAGE, 161, 161, 161",
		        "219,  47, 219,    AVERAGE, 162, 162, 162",
		        "103, 177, 202,    AVERAGE, 161, 161, 161",
		        "123, 177, 125,    AVERAGE, 142, 142, 142",
		        " 12, 105, 219,    AVERAGE, 112, 112, 112",
		        "115, 106,  80,    AVERAGE, 100, 100, 100",
		        "253, 231,  22,    AVERAGE, 169, 169, 169",
		        " 36, 118, 102,  LIGHTNESS,  77,  77,  77",
		        "244,  75, 177,  LIGHTNESS, 160, 160, 160",
		        "150,  83, 249,  LIGHTNESS, 166, 166, 166",
		        "  9,  45,  49,  LIGHTNESS,  29,  29,  29",
		        " 56, 145, 123,  LIGHTNESS, 101, 101, 101",
		        "157,  15,  61,  LIGHTNESS,  86,  86,  86",
		        "148,  37,   9,  LIGHTNESS,  79,  79,  79",
		        " 28, 196, 130,  LIGHTNESS, 112, 112, 112",
		        " 22, 219,  87,  LIGHTNESS, 121, 121, 121",
		        "183, 202,  22,  LIGHTNESS, 112, 112, 112",
		        "115,  86, 176,  LIGHTNESS, 131, 131, 131",
		        " 24, 168,   2,  LIGHTNESS,  85,  85,  85",
		        "252, 191,   3,  LIGHTNESS, 128, 128, 128",
		        "163,   7, 150,  LIGHTNESS,  85,  85,  85",
		        "105, 217, 235,  LIGHTNESS, 170, 170, 170",
		        "240,  84, 127, LUMINOSITY, 120, 120, 120",
		        "109,  30, 130, LUMINOSITY,  54,  54,  54",
		        "121,   1, 118, LUMINOSITY,  34,  34,  34",
		        "144,  24, 177, LUMINOSITY,  60,  60,  60",
		        "183,  38,  96, LUMINOSITY,  73,  73,  73",
		        "167,  20,  73, LUMINOSITY,  55,  55,  55",
		        "252, 200,  75, LUMINOSITY, 202, 202, 202",
		        " 98, 140, 164, LUMINOSITY, 133, 133, 133",
		        " 21, 207,  91, LUMINOSITY, 160, 160, 160",
		        " 52, 203, 128, LUMINOSITY, 166, 166, 166",
		        " 39, 181, 125, LUMINOSITY, 147, 147, 147",
		        " 27, 230, 245, LUMINOSITY, 188, 188, 188",
		        "132, 182,  77, LUMINOSITY, 164, 164, 164",
		        "194, 182, 246, LUMINOSITY, 189, 189, 189",
		        " 21,  10,   4, LUMINOSITY,  12,  12,  12"
		        })
	public void testToGrayScale(ArgumentsAccessor argumentsAccessor) {
		int r = argumentsAccessor.get(0, Integer.class);
		int g = argumentsAccessor.get(1, Integer.class);
		int b = argumentsAccessor.get(2, Integer.class);
		RGB input = new RGB(r,g,b);
		GrayScaleMode m = argumentsAccessor.get(3, GrayScaleMode.class);
		int expectedR = argumentsAccessor.get(4, Integer.class);
		int expectedG = argumentsAccessor.get(5, Integer.class);
		int expectedB = argumentsAccessor.get(6, Integer.class);
		RGB expected = new RGB(expectedR, expectedG, expectedB);
		RGB actual = ColorUtils.toGrayScale(input, m);
	    assertEquals(expected, actual);
	}

}
