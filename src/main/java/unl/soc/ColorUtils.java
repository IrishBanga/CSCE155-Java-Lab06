package unl.soc;


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
		RGB gray=null;
		if((color.getRed() <0 || color.getGreen() <0 || color.getBlue() <0) || (color.getRed() >255 || color.getGreen() >255 || color.getBlue() >255 ))
		{
			//throw new IllegalArgumentException("RGB values out of bound!");
			throw new IllegalRgbException("RGB values out of bound!");
		}
		switch (mode) {
			case AVERAGE: {
				int result = (int) Math.round((double) (color.getRed() + color.getGreen() + color.getBlue()) / 3);
				result = (result > 255) ? (255) : (result);
				gray = new RGB(result, result, result);
				break;
			}
			case LIGHTNESS: {
				int result = (int) Math.round((double) (max(color.getRed(), color.getGreen(), color.getBlue())
						+ min(color.getRed(), color.getGreen(), color.getBlue())) / 2);
				result = (result > 255) ? (255) : (result);
				gray = new RGB(result, result, result);
				break;
			}
			case LUMINOSITY: {
				int result = (int) Math
						.round((double) (0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue()));
				result = (result > 255) ? (255) : (result);
				gray = new RGB(result, result, result);
				break;
			}
			default: {
				break;
			}
		}
		return gray;
	}
	
	/**
	 * Returns a sepia-toned {@link RGB} value of the given {@link RGB}.
	 *
	 * @param color
	 * @return
	 */
	public static RGB toSepia(RGB color) {
		if((color.getRed() <0 || color.getGreen() <0 || color.getBlue() <0) || (color.getRed() >255 || color.getGreen() >255 || color.getBlue() >255 ))
		{
			//throw new IllegalArgumentException("RGB values out of bound!");
			throw new IllegalRgbException("RGB values out of bound!");
		}
		int R = (int)Math.round((double)(0.393*color.getRed() + 0.769*color.getGreen() + 0.189*color.getBlue()));
		int G= (int)Math.round((double)(0.349*color.getRed() + 0.686*color.getGreen() + 0.168*color.getBlue()));
		int B= (int)Math.round((double)(0.272*color.getRed() + 0.534*color.getGreen() + 0.131*color.getBlue()));
		R=(R>255)?(255):(R);
		G=(G>255)?(255):(G);
		B=(B>255)?(255):(B);
		RGB sepia = new RGB(R,G,B);
		return sepia;
	}

}
