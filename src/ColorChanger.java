
import java.io.*;
import javax.imageio.*;

import java.awt.Color;
import java.awt.image.*;
public class ColorChanger{
	public static void main(String args[])throws IOException{
			BufferedImage raw,processed;
			raw = ImageIO.read(new File("flower.png"));
			int width = raw.getWidth();
			int height = raw.getHeight();
			processed = new BufferedImage(width,height,raw.getType());
			float hue = 90/360.0f;//hard coded hue value
			for(int y=0; y<height;y++){
				for(int x=0;x<width;x++){
					//this is how we grab the RGB value of a pixel at x,y coordinates in the image
					int rgb = raw.getRGB(x,y);
					
					//rgb= rgb & (0xFF);					
					//String rgbBinary = Integer.toBinaryString(rgb);
					//System.out.println(Integer.toBinaryString(rgb));
					//System.out.println(rgb);
					int alpha= (rgb >> 24) & 0xFF;
					int red= (rgb >> 16) & 0xFF;   //extract the red value
					int green= (rgb >> 8) & 0xFF;  //extract the green value
					int blue= (rgb) & 0xFF;        //extract the blue value
					float [] hsb= Color.RGBtoHSB(red, green, blue, null);      //user Color.RGBtoHSB() method to convert RGB values to HSB
					float saturation = hsb[1];
					float brightness = hsb[2];
									
					int newRGB = Color.HSBtoRGB(hue, saturation, brightness);  //then use Color.HSBtoRGB() method to convert the HSB value to a new RGB
					//value
					// set the new RGB value to a pixel at x,y coordinates in the image
					processed.setRGB(x,y,newRGB);
				}
			}
			ImageIO.write(processed,"PNG",new File("processed.png"));
	}
}