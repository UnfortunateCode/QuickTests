package images;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

public class FakeWritableRaster extends WritableRaster {
	private WritableRaster wr;
	
	protected FakeWritableRaster(SampleModel sampleModel,
			Point origin) {
		super(sampleModel, origin);
		
		BufferedImage img = new BufferedImage(10,10, BufferedImage.TYPE_INT_RGB);
		wr = img.getRaster();
		// TODO Auto-generated constructor stub
	}

	

}
