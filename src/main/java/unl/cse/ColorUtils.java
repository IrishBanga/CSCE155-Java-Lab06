package unl.cse;


/**
 * A collection of utility methods for converting {@link RGB} values 
 *
 */
public class ColorUtils {

	/**
	 * Scales the given rgb integer component (on the scale [0, 255]) to a floating
	 * point number on the scale [0, 1].
	 *
	 * Note: this function is only included to demonstrate how to unit test floating
	 * point values. See the {@link ColorUtilsTester} for more.
	 */
	public static double rgbIntToFloat(int c) {
		return (double) c / 255;
	}

	/**
	 * Returns the maximum value among the three given integer values.
	 */
	public static int max(int x, int y, int z) {
		return Math.max(Math.max(x, y), z);
	}

	/**
	 * Returns the minimum value among the three given integer values.
	 */
	public static int min(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}

	/**
	 * Returns a gray scaled {@link RGB} value of the given {@link RGB} 
	 * value using the technique specified by <code>mode</code>.
	 *
	 * @param color
	 * @return
	 */
	public static RGB toGrayScale(RGB color, GrayScaleMode mode) {
		//TODO: implement
		return null;
	}
	
	/**
	 * Returns a sepia-toned {@link RGB} value of the given {@link RGB}.
	 *
	 * @param color
	 * @return
	 */
	public static RGB toSepia(RGB color) {
		//TODO: implement
		return null;
	}

}
