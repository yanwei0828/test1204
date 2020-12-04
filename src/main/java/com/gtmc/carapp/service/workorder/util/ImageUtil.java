package com.gtmc.carapp.service.workorder.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片工具类
 *
 */
public class ImageUtil {

	private static Logger logger = Logger.getLogger(ImageUtil.class);

	/**
	 *
	 * @param bytes
	 *            图片
	 * @param angel
	 *            旋转角度
	 * @return
	 */
	public static byte[] rotateImage(byte[] bytes, int angel) {

		if (bytes == null) {
			return null;
		}
		BufferedImage bufferedImage = null;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			bufferedImage = ImageIO.read(in);
		} catch (IOException e) {
			logger.error("图片旋转失败",e);
		}
		if (bufferedImage == null) {
			return null;
		}
		if (angel < 0) {
			// 将负数角度，纠正为正数角度
			angel = angel + 360;
		}
		int imageWidth = bufferedImage.getWidth(null);
		int imageHeight = bufferedImage.getHeight(null);
		// 计算重新绘制图片的尺寸
		Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
		// 获取原始图片的透明度
		int type = bufferedImage.getColorModel().getTransparency();
		BufferedImage newImage = null;
		newImage = new BufferedImage(rectangle.width, rectangle.height, type);
		Graphics2D graphics = newImage.createGraphics();
		// 平移位置
		graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
		// 旋转角度
		graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
		// 绘图
		graphics.drawImage(bufferedImage, null, null);
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(newImage,"jpg",out);
			byte[] resultByte = out.toByteArray();
			return resultByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param bufferedImage
	 *            图片
	 * @param angel
	 *            旋转角度
	 * @return
	 */
	public static BufferedImage rotateImage(BufferedImage bufferedImage, int angel) {
		if (bufferedImage == null) {
			return null;
		}
		if (angel < 0) {
			// 将负数角度，纠正为正数角度
			angel = angel + 360;
		}
		int imageWidth = bufferedImage.getWidth(null);
		int imageHeight = bufferedImage.getHeight(null);
		// 计算重新绘制图片的尺寸
		Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
		// 获取原始图片的透明度
		int type = bufferedImage.getColorModel().getTransparency();
		BufferedImage newImage = null;
		newImage = new BufferedImage(rectangle.width, rectangle.height, type);
		Graphics2D graphics = newImage.createGraphics();
		// 平移位置
		graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
		// 旋转角度
		graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
		// 绘图
		graphics.drawImage(bufferedImage, null, null);
		return newImage;
	}

	/**
	 * 旋转图片
	 *
	 * @param image
	 *            图片
	 * @param angel
	 *            旋转角度
	 * @return
	 */
	public static BufferedImage rotateImage(Image image, int angel) {
		if (image == null) {
			return null;
		}
		if (angel < 0) {
			// 将负数角度，纠正为正数角度
			angel = angel + 360;
		}
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
		BufferedImage newImage = null;
		newImage = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = newImage.createGraphics();
		// transform
		graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
		graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
		graphics.drawImage(image, null, null);
		return newImage;
	}

	/**
	 * 计算旋转后的尺寸
	 *
	 * @param src
	 * @param angel
	 * @return
	 */
	private static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
		if (angel >= 90) {
			if (angel / 90 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}
		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
	/**
	 * 获取网络图片流（附加）
	 *
	 * @param url
	 * @return
	 */
	public static InputStream getImageStream(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将BufferedImage转换为InputStream
	 * @param image
	 * @return
	 */
	public InputStream bufferedImageToInputStream(BufferedImage image) throws IOException {
		ByteArrayOutputStream os = null;
		InputStream input = null;
		try {
			os = new ByteArrayOutputStream();
			ImageIO.write(image, FileFtpUtil.FILE_SUFFIX_PNG, os);
			input = new ByteArrayInputStream(os.toByteArray());
			os.close();
			return input;
		} finally {
			os.close();
			input.close();
			return null;
		}
	}

	/**
	 * 裁剪PNG图片工具类
	 *
	 * @param fromFile		源文件
	 * @param toFile		裁剪后的文件
	 * @param outputWidth	裁剪宽度
	 * @param outputHeight	裁剪高度
	 * @param proportion	是否是等比缩放
	 */
	public static void resizePng(File fromFile, File toFile, int outputWidth, int outputHeight,
								 boolean proportion) {
		try {
			BufferedImage bi2 = ImageIO.read(fromFile);
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			if (proportion) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
				double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 < rate2 ? rate1 : rate2;
				newWidth = (int) (((double) bi2.getWidth(null)) / rate);
				newHeight = (int) (((double) bi2.getHeight(null)) / rate);
			} else {
				newWidth = outputWidth; // 输出的图片宽度
				newHeight = outputHeight; // 输出的图片高度
			}
			BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = to.createGraphics();
			to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
			g2d.dispose();
			g2d = to.createGraphics();
			Image from = bi2.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_AREA_AVERAGING);
			g2d.drawImage(from, 0, 0, null);
			g2d.dispose();
			ImageIO.write(to, FileFtpUtil.FILE_SUFFIX_PNG, toFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String url = "http://wxt.sinaimg.cn/mw690/0076NW5Ngy1ge3yah6srvj307i0akq4a.jpg";
		// 将网络图片转为BufferedImage
		try {
			BufferedImage bufferedImage = ImageIO.read(ImageUtil.getImageStream(url));
			// 调用图片旋转工具类，旋转图片
			BufferedImage rotateImage = ImageUtil.rotateImage(bufferedImage, 45);
			// 截取URL中的图片名称和后缀
			String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
//			String fileName = "test";
			// 截取图片后缀名（.png）,以保持图片格式不变
			String imgSuffix = url.substring(url.lastIndexOf(".") + 1, url.length());
//			String imgSuffix = ".jpg";
			// 将旋转后的图片保存到D盘根目录下
			File file = new File("D:\\", fileName);
			ImageIO.write(rotateImage, imgSuffix, file);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}

