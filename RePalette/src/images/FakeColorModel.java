package images;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;

public class FakeColorModel extends ColorModel {
	ColorModel cm;

	public FakeColorModel(int bits) {
		super(bits);
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		cm = img.getColorModel();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getAlpha(int pixel) {
		// TODO Auto-generated method stub
		System.err.println("Request to getAlpha: " + pixel + "\t" + cm.getAlpha(pixel));
		return cm.getAlpha(pixel);
	}

	@Override
	public int getBlue(int pixel) {
		// TODO Auto-generated method stub
		System.err.println("Request to getBlue: " + pixel + "\t" + cm.getBlue(pixel));
		return cm.getBlue(pixel);
	}

	@Override
	public int getGreen(int pixel) {
		// TODO Auto-generated method stub
		System.err.println("Request to getGreen: " + pixel + "\t" + cm.getGreen(pixel));
		return cm.getGreen(pixel);
	}

	@Override
	public int getRed(int pixel) {
		// TODO Auto-generated method stub
		System.err.println("Request to getRed: " + pixel + "\t" + cm.getRed(pixel));
		return cm.getRed(pixel);
	}
	
	public boolean isCompatibleRaster(Raster raster) {
		System.err.println("Request to isCompatibleRaster: " + raster);
		return cm.isCompatibleRaster(raster);
	}
	
	public Object getDataElements(int rgb, Object pixel) {
		System.err.println("Request to getDataElements(int,Obj): " + rgb + ", " + pixel + "\t" + cm.getDataElements(rgb,pixel));
		return cm.getDataElements(rgb, pixel);
	}

}
