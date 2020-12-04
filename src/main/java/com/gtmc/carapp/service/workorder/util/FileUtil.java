package com.gtmc.carapp.service.workorder.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;


public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);

	public static byte[] getByte(Object obj){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream( bos);
			oos.writeObject(obj);
			oos.close();
			bos.close();
		} catch (IOException e) {
			logger.info("----------FileUtil>getByte 异常", e);
		}

		byte[] bs = bos.toByteArray();

		return bs;
	}

	public static String url2Base64(String fileUrl) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(fileUrl);
		client.executeMethod(get);
		File storeFile = File.createTempFile("temp",null);
		FileOutputStream output = new FileOutputStream(storeFile);
		output.write(get.getResponseBody());
		output.close();
		String result = fileToBase64(storeFile);
		storeFile.delete();
		return result;
	}

	public static String fileToBase64(File file) throws Exception {
		byte[] data = new byte[0];
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			in.close();
			data = out.toByteArray();
		}
		return new Base64().encodeToString(data);
	}

	/**
	 * 将图片转换成pdf文件
	 * imgFilePath 需要被转换的img所存放的位置。 例如imgFilePath="D:\\projectPath\\55555.jpg";
	 * pdfFilePath 转换后的pdf所存放的位置 例如pdfFilePath="D:\\projectPath\\test.pdf";
	 * @return
	 * @throws IOException
	 */
	public static boolean imgToPdf(String imgFilePath, String pdfFilePath)throws IOException {
		File file=new File(imgFilePath);
		if(file.exists()){
			Document document = new Document();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(pdfFilePath);
				PdfWriter.getInstance(document, fos);

				// 添加PDF文档的某些信息，比如作者，主题等等
				document.addAuthor("arui");
				document.addSubject("test pdf.");
				// 设置文档的大小
				document.setPageSize(PageSize.A4);
				// 打开文档
				document.open();
				// 写入一段文字
				//document.add(new Paragraph("JUST TEST ..."));
				// 读取一个图片
				Image image = Image.getInstance(imgFilePath);
				float imageHeight=image.getScaledHeight();
				float imageWidth=image.getScaledWidth();
				int i=0;
				while(imageHeight>500||imageWidth>500){
					image.scalePercent(100-i);
					i++;
					imageHeight=image.getScaledHeight();
					imageWidth=image.getScaledWidth();
					System.out.println("imageHeight->"+imageHeight);
					System.out.println("imageWidth->"+imageWidth);
				}

				image.setAlignment(Image.ALIGN_CENTER);
				//     //设置图片的绝对位置
				// image.setAbsolutePosition(0, 0);
				// image.scaleAbsolute(500, 400);
				// 插入一个图片
				document.add(image);
			} catch (Exception e) {

			}
			document.close();
			fos.flush();
			fos.close();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 将图片转换成pdf文件
	 * imgFileUrl 需要被转换的img的url
	 * pdfFileUrl 转换后的pdf的url
	 * @return
	 * @throws IOException
	 */
	public boolean imgUrlToPdf(String imgFileUrl, String pdfFileUrl)throws IOException {
//		File file=new File(imgFilePath);
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(imgFileUrl);
		client.executeMethod(get);
		File storeFile = File.createTempFile("temp",null);

		if(storeFile.exists()){
			Document document = new Document();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(pdfFileUrl);
				PdfWriter.getInstance(document, fos);

				// 添加PDF文档的某些信息，比如作者，主题等等
				document.addAuthor("arui");
				document.addSubject("test pdf.");
				// 设置文档的大小
				document.setPageSize(PageSize.A4);
				// 打开文档
				document.open();
				// 写入一段文字
				//document.add(new Paragraph("JUST TEST ..."));
				// 读取一个图片
				Image image = Image.getInstance(imgFileUrl);
				float imageHeight=image.getScaledHeight();
				float imageWidth=image.getScaledWidth();
				int i=0;
				while(imageHeight>500||imageWidth>500){
					image.scalePercent(100-i);
					i++;
					imageHeight=image.getScaledHeight();
					imageWidth=image.getScaledWidth();
					System.out.println("imageHeight->"+imageHeight);
					System.out.println("imageWidth->"+imageWidth);
				}

				image.setAlignment(Image.ALIGN_CENTER);
				//     //设置图片的绝对位置
				// image.setAbsolutePosition(0, 0);
				// image.scaleAbsolute(500, 400);
				// 插入一个图片
				document.add(image);
			} catch (Exception e) {

			}
			document.close();
			fos.flush();
			fos.close();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 将BufferedImage转换为InputStream
	 * @param image
	 * @return
	 */
	public static InputStream bufferedImageToInputStream(BufferedImage image){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, FileFtpUtil.FILE_SUFFIX_PNG, os);//透明背景需要转png格式
			InputStream input = new ByteArrayInputStream(os.toByteArray());
			return input;
		} catch (IOException e) {
			logger.error("将BufferedImage转换为InputStream失败:",e);
		}
		return null;
	}

	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 将file转换为inputStream
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream file2InputStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}

	/**
	 * 将inputStream转化为file
	 * @param is
	 * @param file 要输出的文件目录
	 */
	public static void inputStream2File(InputStream is, File file) throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int len = 0;
			byte[] buffer = new byte[8192];

			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} finally {
			os.close();
			is.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Date now = new Date();
		String timestamp = String.valueOf(now.getTime());
//		imgToPdf("E:\\sign_test.jpg","E:\\sign_test_" + timestamp+ ".pdf");

		File infile = new File("E:\\sign_test.jpg");
		InputStream in = FileUtil.file2InputStream(infile);
		BufferedImage bufferedImage = ImageIO.read(in);
		// 调用图片旋转工具类，旋转图片
		BufferedImage rotateImage = ImageUtil.rotateImage(bufferedImage, 270);//顺时针转270度：270 或者逆时针旋转90度：-90
		InputStream inputStream = FileUtil.bufferedImageToInputStream(rotateImage);
		File outfile = new File("E:\\sign_test_"+timestamp+".jpg");

		FileUtil.inputStream2File(inputStream,outfile);

	}
	
}