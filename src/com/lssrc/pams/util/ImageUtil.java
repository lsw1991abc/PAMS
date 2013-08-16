package com.lssrc.pams.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {

	private BufferedImage image;
	private Graphics2D graphics2d;
	private String validateString;
	private String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public BufferedImage getValidateImage(int width, int height, int length) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		graphics2d = (Graphics2D) image.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, width, height);
		graphics2d.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		graphics2d.setColor(getRandColor(160, 200));
		Random random = new Random();
		int x, y;
		for (int i = 0; i < 255; i++) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			graphics2d.drawLine(x, y, x, y);
		}

		StringBuffer randString = new StringBuffer();
		String rand = "";
		graphics2d.setColor(Color.BLACK);
		for (int i = 0; i < length; i++) {
			rand = String.valueOf(randomString.charAt(random.nextInt(randomString.length())));
			randString.append(rand);
			graphics2d.drawString(rand, 15 * i + 10, 25);
		}
		validateString = randString.toString();
		graphics2d.dispose();
		return image;
	}

	private Color getRandColor(int startColor, int endColor) {
		Random random = new Random();
		if (startColor > 255) {
			startColor = 255;
		}
		if (endColor > 255) {
			endColor = 255;
		}
		int r = startColor + random.nextInt(endColor - startColor);
		int g = startColor + random.nextInt(endColor - startColor);
		int b = startColor + random.nextInt(endColor - startColor);
		return new Color(r, g, b);
	}

	public String getValidateString() {
		return validateString;
	}
	
}
