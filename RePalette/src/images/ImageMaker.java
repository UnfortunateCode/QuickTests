package images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageMaker {  
	  
    public static BufferedImage createImage() {  
        BufferedImage img = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB); 
        //FakeColorModel fcm = new FakeColorModel(32);
        //FakeWritableRaster fwr = new FakeWritableRaster(img2.getSampleModel(), new Point(0,0));
        //BufferedImage img = new BufferedImage(img2.getColorModel(), fwr, false, null);
        img.createGraphics();  
        Graphics2D g = (Graphics2D)img.getGraphics();  
        g.setColor(Color.YELLOW);  
        g.fillRect(0, 0, 5, 5);  
          /*
        for(int i = 1; i < 14; i++) {  
            g.setColor(new Color(5*i, 5*i, 4+1*2+i*3));  
            g.fillRect(2*i, 2*i, 3*i, 3*1);  
        }  */
        
        img.setRGB(0, 2, 0xF0FF0000);
        img.setRGB(0, 0, 0xFF111111);
        
        return img;  
    }  
      
    public static void main(String[] args) {  
    	int in;
    	long l;
    	byte by;
    	in = 0x8AFEDC23;
    	l = 0x7FFFFFFF;
    	int a = (in >> 24) & 0xFF;
    	int r = (in >> 16) & 0xFF;
    	int g = (in >> 8) & 0xFF;
    	int b = in & 0xFF;
    	System.out.println(a + " " + r + " " + g + " " + b);
    		System.out.println((in & 0xFF000000) + " " + ((in >> 24)&0xFF));
        JFrame frame = new JFrame("Image Maker");  
        frame.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent event) {  
                System.exit(0);  
            }  
        });  
        frame.setBounds(0, 0, 200, 200);  
        BufferedImage img = createImage();
        JImagePanel panel = new JImagePanel(img, 50, 45);  
        frame.add(panel);   
        
        int p;
        for (int i = 0; i < img.getHeight(); ++i) {
        	for (int j = 0; j < img.getWidth(); ++j) {
        		p = i*img.getWidth()+j;
        		
        	}
        }
        System.out.println(img.getColorModel());
        System.out.println(img.getRaster());
        System.out.println(img.getRaster().getSampleModel());
        System.out.println(img.getRaster().getDataBuffer());
        
        for (int i = 0; i < img.getHeight(); ++i) {
        	for (int j = 0; j < img.getWidth(); ++j) {
        		in = img.getRGB(j,i);
        		System.out.print("[" + ((in >> 24) & 0xFF)
        				+ "," + ((in >> 16) & 0xFF)
        				+ "," + ((in >> 8) & 0xFF)
        				+ "," + (in & 0xFF) + "]\t");
        	}
        	System.out.println();
        }
        
        try {
        	img = ImageIO.read(new File("H:\\MMO\\RO-Server\\SPR\\FenKnight-01.bmp"));
        } catch (Exception e) {
        	System.err.println("read error");
        }
        JImagePanel jip = new JImagePanel(img,0,0);
        frame.add(jip);
        frame.setVisible(true); 

        System.out.println(img.getColorModel());
        System.out.println(img.getRaster());
        System.out.println(img.getRaster().getSampleModel());
        System.out.println(img.getRaster().getDataBuffer());
    }  
  
}