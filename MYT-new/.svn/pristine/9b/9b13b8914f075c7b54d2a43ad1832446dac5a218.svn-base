package com.yihu.myt.mgr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
/*
import com.common.util.DateOper;*/

import com.coreframework.util.DateOper;

public class ImageHepler {

//	public static String smbPrefix = "smb://administrator:Password123@10.0.100.123";
//	public static String httpPrefix = "http://10.0.100.123";
//	public static String doctorDir = "/doctor/";
//	public static String hospitalDir = "/hospital/";
	
	public static String smbPrefix = "smb://UpImg:www.yihu.com@10.0.0.75";
//	public static String httpPrefix = "http://3g.yihu.com";
	public static String httpPrefix = "http://61.131.3.201";
	public static String doctorDir = "/upfile/doctor/";
	public static String hospitalDir = "/upfile/hospital/";
	public static String tjtDir = "/upfile/tjt/";
	public static String userUpFile = "/upfilea_ccessory/ZiXun";
	
	/**
	 * @Description: 将srcImageFile裁剪后生成destImageFile
	 * @param srcImageFile
	 *            原始图
	 * @param destImageFile
	 *            目标图
	 * @param width
	 *            原始图 放大缩小后width
	 * @param height
	 *            原始图放大缩小后height
	 * @param rect
	 *            目标图输出的格式(rect.x, rect.y, rect.width, rect.height)
	 * @throws IOException
	 * @author Sun Yanjun
	 */
	public static void cut(String srcImageFile, String destImageFile,
			int width, int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(new File(srcImageFile));
		BufferedImage bImage = makeThumbnail(image, width, height);
		System.out.println("缩放后图片宽："+bImage.getWidth()+" 高："+bImage.getHeight());
		String fileType = srcImageFile.substring(srcImageFile.lastIndexOf(".")+1).toLowerCase();
		String tempFile = srcImageFile.substring(0,srcImageFile.lastIndexOf("/")+1)+"temp"+srcImageFile.substring(srcImageFile.lastIndexOf("/")+1);

		// 把原始图片输出
		ImageIO.write(bImage, fileType, new File(tempFile));
		ReadSmb.removeFile(tempFile);

		saveSubImage(bImage, rect, new File(destImageFile));
	}

	/**
	 * @Description: 将srcImageFile裁剪后生成destImageFile
	 * @param srcImageFile
	 *            原始图
	 * @param destImageFile
	 *            目标图
	 * @param width
	 *            原始图预处理后width
	 * @param height
	 *            原始图预处理后height
	 * @param rect
	 *            目标图输出的格式(rect.x, rect.y, rect.width, rect.height)
	 * @throws IOException
	 * @author Sun Yanjun
	 */
	public static void cut(File srcImageFile, File destImageFile, int width,
			int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(srcImageFile);
		BufferedImage bImage = makeThumbnail(image, width, height);

		saveSubImage(bImage, rect, destImageFile);
	}

	/**
	 * @Description: 对原始图片根据(x, y, width, height) = (0, 0, width,
	 *               height)进行缩放，生成BufferImage
	 * @param img
	 * @param width
	 *            预处理后图片的宽度
	 * @param height
	 *            预处理后图片高度
	 * @return
	 * @author Sun Yanjun
	 * @throws IOException
	 */
	private static BufferedImage makeThumbnail(Image img, int width, int height)
			throws IOException {
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics g = tag.getGraphics();
		g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);

		g.dispose();
		return tag;
	}

	/**
	 * @Description: 对BufferImage按照(x, y, width, height) = (subImageBounds.x,
	 *               subImageBounds.y, subImageBounds.width,
	 *               subImageBounds.height)进行裁剪
	 *               如果subImageBounds范围过大，则用空白填充周围的区域。
	 * 
	 * @param image
	 * @param subImageBounds
	 * @param destImageFile
	 * @throws IOException
	 * @author Sun Yanjun
	 */
	private static void saveSubImage(BufferedImage image,
			Rectangle subImageBounds, File destImageFile) throws IOException {
		String fileName = destImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		BufferedImage subImage = new BufferedImage(subImageBounds.width,
				subImageBounds.height, 1);
		Graphics g = subImage.getGraphics();

		if ((subImageBounds.width > image.getWidth())
				|| (subImageBounds.height > image.getHeight())) {
			int left = subImageBounds.x;
			int top = subImageBounds.y;
			if (image.getWidth() < subImageBounds.width)
				left = (subImageBounds.width - image.getWidth()) / 2;
			if (image.getHeight() < subImageBounds.height)
				top = (subImageBounds.height - image.getHeight()) / 2;
			g.setColor(Color.white);
			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
			g.drawImage(image, left, top, null);
			ImageIO.write(image, formatName, destImageFile);
		} else {
			g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
					subImageBounds.width, subImageBounds.height), 0, 0, null);
		}
		g.dispose();
		ImageIO.write(subImage, formatName, destImageFile);
	}
	
	/**
	 * 裁剪图片（无法缩放）
	 * @param srcImageFile 原始文件,http打头的图片地址
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 生成图片宽度
	 * @param height 生成图片高度
	 * @param fileName 最终文件名
	 * @return
	 * @throws IOException
	 */
	public static String cut(String srcImageFile, int x, int y, int width,
			int height, String fileName) throws IOException {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
            String imageFile = readRemoteFile(srcImageFile);
            String tempFile = imageFile.substring(0,imageFile.lastIndexOf("/")+1)+"temp"+imageFile.substring(imageFile.lastIndexOf("/")+1);
			// 读取图片文件
			is = new FileInputStream(imageFile);
			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 .(例如 "jpeg" 或 "tiff")等 。
			 */
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName(imageFile.substring(imageFile.lastIndexOf(".")+1));
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:读取源。true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);
			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
			 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();
			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
			 */
			Rectangle rect = new Rectangle(x, y, width, height);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);
			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			ImageIO.write(bi, imageFile.substring(imageFile.lastIndexOf(".")+1), new File(tempFile));
			
            String smbPath = srcImageFile.substring(0,srcImageFile.lastIndexOf("/")+1).replace(ImageHepler.httpPrefix, ImageHepler.smbPrefix);
            ReadSmb.readLocalToSmb(tempFile, smbPath, fileName);
			if (is != null)
				is.close();
            System.out.println(ReadSmb.removeFile(imageFile));
            System.out.println(ReadSmb.removeFile(tempFile));
            
            return srcImageFile.substring(0,srcImageFile.lastIndexOf("/")+1).replace(ImageHepler.httpPrefix, "")+fileName;
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}
	}
	
	/**
	 * 下载图片到本地
	 * @param srcImageFile http打头的图片地址
	 * @return
	 */
	private static String readRemoteFile(String srcImageFile) {
		try {
			String savePath = "c:/"+DateOper.formatDate(new Date(), "yyyyMMddHHmmssSSS")+srcImageFile.substring(srcImageFile.lastIndexOf("."));
            URL url = new URL(srcImageFile);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, count);
            }
            out.close();
            in.close();
            connection.disconnect();
            return savePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 裁剪缩放移动后的图片
	 * @param src 原始文件,http打头的图片地址
	 * @param x x坐标
	 * @param y y坐标
	 * @param finalWidth 生成图片宽度
	 * @param finalHeight 生成图片高度
	 * @param orgWidth 原始图片宽度
	 * @param orgHeight 原始图片高度
	 * @param zoom 缩放比率
	 * @return
	 * @throws IOException
	 */
	public static String cut(String src, int x, int y, int finalWidth,
			int finalHeight, int orgWidth, int orgHeight, float zoom)
			throws IOException {
		String imageFile = null;
		String bigFile = null;
		String smallFile = null;
		try {
			//下载图片后的完整路径
			imageFile = readRemoteFile(src);
			//大图
			String bigFilename = "big" + imageFile.substring(imageFile.lastIndexOf("/") + 1);
			String smallFilename = "small" + imageFile.substring(imageFile.lastIndexOf("/") + 1);
			//裁剪后的大图完整路径
			bigFile = imageFile.substring(0, imageFile.lastIndexOf("/") + 1) + bigFilename;
			
			//缩略图
			smallFile = imageFile.substring(0, imageFile.lastIndexOf("/") + 1) + smallFilename;
	
			Rectangle rect = new Rectangle(x, y, finalWidth, finalHeight);
			// 宽度 * 缩放略
			ImageHepler.cut(imageFile, bigFile, (int) (orgWidth * zoom),
					(int) (orgHeight * zoom), rect);
			
	        String smbPath = src.substring(0,src.lastIndexOf("/")+1).replace(ImageHepler.httpPrefix, ImageHepler.smbPrefix);
	        ReadSmb.readLocalToSmb(bigFile, smbPath, bigFilename);
	        

			rect = new Rectangle(0, 0, 120, 150);
			
			float smallZoom = 120f/finalWidth;
			// 宽度 * 缩放略
			ImageHepler.cut(bigFile, smallFile, (int) (finalWidth * smallZoom),
					(int) (finalHeight * smallZoom), rect);
	        ReadSmb.readLocalToSmb(smallFile, smbPath, smallFilename);
	        
			return src.substring(0, src.lastIndexOf("/") + 1) + bigFilename;
		} finally {
			//删除本地临时文件
	        System.out.println(ReadSmb.removeFile(imageFile));
	        System.out.println(ReadSmb.removeFile(bigFile));
	        System.out.println(ReadSmb.removeFile(smallFile));
		}
	}
	
	public static void main(String[] args) {
		try {
			String picPath = "c:/1.png";
			String destPath = "c:/11.png";
//			//四个参数分别是x坐标、y坐标、生成图片宽度、生成图片高度
//			Rectangle rect = new Rectangle(1223, 923, 120, 150);
//			//宽度  * 缩放略
//			ImageHepler.cut(picPath, destPath, (int)(1600*1.8), (int)(1200*1.8), rect);
			

//			Rectangle rect = new Rectangle(0, 0, 120, 150);
			//宽度  * 缩放略
//			ImageHepler.cut(picPath, destPath, (int)(1366*0.2), (int)(768*0.2), rect);
			
			
//			ImageHepler.cut(ImageHepler.httpPrefix+"/upfile/doctor/121/48514.jpg",
//					167, 5, 120, 150, 439, 300, 1, "m48514.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
