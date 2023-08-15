package unl.soc;

public class ImageDriver {
	
	public static final String PATH = "images/";

	public static void main(String args[]) {

		String inputFileName = null, outputFileName = null;
		int mode = 1;
		if (args.length == 2 || args.length == 3) {
			inputFileName = args[0];
			outputFileName = args[1];
			if (args.length == 3) {
				mode = Integer.parseInt(args[2]);
			}
		} else {
			System.err.printf("Usage: inputFileName outputFileName [mode]\n");
			System.err.printf("       mode: 1 = Average (default), 2 = Lightness, 3 = Luminosity, 4 = Sepia]\n");
			System.exit(1);
		}

		RGB image[][] = ImageUtils.loadImage(PATH + inputFileName);
		if (mode == 1) {
			ImageUtils.imageToGrayScaleAverage(image);
		} else if (mode == 2) {
			ImageUtils.imageToGrayScaleLightness(image);
		} else if (mode == 3) {
			ImageUtils.imageToGrayScaleLuminosity(image);
		} else if (mode == 4) {
			ImageUtils.imageToSepia(image);
		} else {
			System.err.println("Invalid mode");
		}
		ImageUtils.saveImage(PATH + outputFileName, image);

	}
}
