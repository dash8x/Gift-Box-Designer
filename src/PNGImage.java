import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

import java.util.*;

/**
 * <p>
 * This class provides basic image manupulation routines. This class is
 * essentially a wrapper around a <tt>BufferedImage</tt>, and provides simple
 * methods for reading and writing images in JPEG format, accessing pixel
 * values, and creating new images.
 * </p>
 * <p>
 * There is no range checking either on image coordinates or on pixel colour
 * values. Entering values outside of the allowed ranges may generate runtime
 * exceptions, or result in garbled images.
 * </p>
 * 
 * @author <a href="mailto:smx@cs.nott.ac.uk">Steven Mills</a>
 * @version 1.0 (Jan 8 2003)
 */
public class PNGImage {
	
	BufferedImage img;
	
	/**
	 * Blank  constructor.
	 */
	public PNGImage() {
		
		
	}
	
	/**
	 * Blank image constructor.
	 * 
	 * Creates a new image with the given dimensions, and all pixels in the
	 * image are set to be black. The dimensions should be positive integers.
	 * 
	 * @param width
	 *            the width of the image in pixels
	 * @param height
	 *            the height of the image in pixels
	 */
	public PNGImage(int width, int height) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				img.setRGB(x, y, 0x00ffffff);
			}
		}
	}

	/**
	 * Reads a PNG Image from file
	 */
	public void read(String filename) throws IOException {
		InputStream istream = new FileInputStream(filename);
		img = ImageIO.read(istream);		
	}

	/**
	 * Writes an image to a file in PNG format.
	 */
	public void write(String filename) throws IOException {
		OutputStream ostream = new FileOutputStream(filename);
		ImageIO.write(img, "png", ostream);
	}

	/**
	 * Returns the alpha value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The red value is returned as an integer in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @return the red value at the given coordinates
	 */
	public int getAlpha(int x, int y) {
		return (img.getRGB(x, y) >> 24) & 0xFF;
	}
	
	public int getHeight() {
		return img.getHeight();
	}
	
	public int getWidth() {
		return img.getWidth();
	}
	
	/**
	 * Returns the red value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The red value is returned as an integer in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @return the red value at the given coordinates
	 */
	public int getRed(int x, int y) {
		return (img.getRGB(x, y) & 0x00ff0000) >> 16;
	}

	/**
	 * Returns the green value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The green value is returned as an integer in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @return the green value at the given coordinates
	 */
	public int getGreen(int x, int y) {
		return (img.getRGB(x, y) & 0x0000ff00) >> 8;
	}

	/**
	 * Returns the blue value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The blue value is returned as an integer in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @return the blue value at the given coordinates
	 */
	public int getBlue(int x, int y) {
		return (img.getRGB(x, y) & 0x000000ff);
	}

	/**
	 * Sets the red value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The red value should be an integer in the range
	 * [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @param value
	 *            the new red value at the given coordinates
	 */
	public void setRed(int x, int y, int value) {
		img.setRGB(x, y, (img.getRGB(x, y) & 0xff00ffff) | (value << 16));
	}

	/**
	 * Sets the green value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The green value should be an integer in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @param value
	 *            the new green value at the given coordinates
	 */
	public void setGreen(int x, int y, int value) {
		img.setRGB(x, y, (img.getRGB(x, y) & 0xffff00ff) | (value << 8));
	}

	/**
	 * Sets the blue value of the image at the given coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The blue value should be an integer in the range
	 * [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @param value
	 *            the new blue value at the given coordinates
	 */
	public void setBlue(int x, int y, int value) {
		img.setRGB(x, y, (img.getRGB(x, y) & 0xffffff00) | value);
	}

	/**
	 * Sets the red, green, and blue values of the image at the given
	 * coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The colour values should be integers in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @param r
	 *            the new red value at the given coordinates
	 * @param g
	 *            the new green value at the given coordinates
	 * @param b
	 *            the new blue value at the given coordinates
	 */
	public void setRGB(int x, int y, int r, int g, int b) {
		this.setRGB(x, y, r, g, b, 255);
	}

	/**
	 * Sets the alpha, red, green, and blue values of the image at the given
	 * coordinates.
	 * 
	 * The coordinates should be non-negative and less than the width (x) or
	 * height (y) of the image. The colour values should be integers in the
	 * range [0,255].
	 * 
	 * @param x
	 *            the horizontal coordinate of the pixel
	 * @param y
	 *            the vertical coordinate of the pixel
	 * @param r
	 *            the new red value at the given coordinates
	 * @param g
	 *            the new green value at the given coordinates
	 * @param b
	 *            the new blue value at the given coordinates
	 */
	public void setRGB(int x, int y, int r, int g, int b, int a) {
		img.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
	}
	
	/**
	 * Directly sets RGB
	 */
	public void setRGB(int x, int y, int rgb) {
		img.setRGB(x, y, rgb);
	}
		
	/**
	 * Directly gets RGB
	 */
	public int getRGB(int x, int y) {
		return img.getRGB(x, y);
	}
	
	/**
	 * Get buffered image
	 */
	public BufferedImage getImage() {
		return img;
	}

}
