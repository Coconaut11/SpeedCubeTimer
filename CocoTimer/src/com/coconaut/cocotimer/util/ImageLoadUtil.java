package com.coconaut.cocotimer.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageLoadUtil {
	public static BufferedImage load(String name) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("res/" + name));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can't load image (" + name + ")", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		
		return img;
	}
}
