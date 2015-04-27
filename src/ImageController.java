
public class ImageController {
	
	/**
	 * Applies a color to an image
	 */
	public static PNGImage applyColor(PNGImage orig, int r, int g, int b) {
		int width = orig.getWidth();
		int height = orig.getHeight();
		PNGImage output = new PNGImage(width, height);
		//replace all white pixels with color
		for ( int y = 0; y < height; y++ ) {
			for ( int x = 0; x < width; x++ ) {
				int alpha = orig.getAlpha(x, y);
				int red = orig.getRed(x, y);
				int green = orig.getGreen(x, y);
				int blue = orig.getBlue(x, y);
				if ( alpha != 0 && red == 255 && green == 255 && blue == 255 ) {
					output.setRGB(x, y, r, g, b);
				} else {
					output.setRGB(x, y, red, green, blue, alpha);
				}
			}
		}
		return output;
	}
	
	/**
	 * Applies a pattern to an image
	 */
	public static PNGImage applyPattern(PNGImage orig, PNGImage pattern) {
		int width = orig.getWidth();
		int height = orig.getHeight();
		int p_width = pattern.getWidth();
		int p_height = pattern.getHeight();
		PNGImage output = new PNGImage(width, height);
		//replace all white pixels with color
		for ( int y = 0; y < height; y++ ) {
			for ( int x = 0; x < width; x++ ) {
				int rgb = orig.getRGB(x, y);				
				if ( rgb == 0xffffffff ) {
					int p_x = x % p_width;
					int p_y = y % p_height;
					output.setRGB(x, y, pattern.getRed(p_x, p_y), pattern.getGreen(p_x, p_y), pattern.getBlue(p_x, p_y), pattern.getAlpha(p_x, p_y));
				} else {
					output.setRGB(x, y, rgb);
				}
			}
		}
		return output;
	}
	
}
