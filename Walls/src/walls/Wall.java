package walls;

import javax.swing.*;
import java.awt.*;

/**
 * Test of making a background of a decrepit brick wall.
 * 
 * @author UnfortunateCode
 */
public class Wall extends JApplet {
	
	private class Painter extends JPanel {
		

		public Painter() {
			super(new BorderLayout());
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for (int i = 0; i < 10; ++i) {
				int startX = 0;
				int segmentLength,segmentHeight=0;
				double r;
				while (startX < 600) {
					segmentLength = 3+(int)(Math.random()*30);
					
					r = Math.random(); 
					if (r < 0.1) {
						startX += 2; // advance ahead, creating 1px gap
						segmentHeight = 1 - segmentHeight; 
					} else if (r < 0.4) {
						startX += 2; 
					} else {
						segmentHeight = 1 - segmentHeight; 
					}
					
					g.drawRect(startX, 10*i+segmentHeight, segmentLength, 0);
					startX+=segmentLength; // advance to the end of the wall segment, same pixel as last pixel of segment
				}
			}
		}
	}
	
	public void init() {
		Container cp = getContentPane();
		
		cp.add(new Painter());
	}
}
