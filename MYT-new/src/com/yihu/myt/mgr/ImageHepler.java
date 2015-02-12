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
	 * @Description: ��srcImageFile�ü�������destImageFile
	 * @param srcImageFile
	 *            ԭʼͼ
	 * @param destImageFile
	 *            Ŀ��ͼ
	 * @param width
	 *            ԭʼͼ �Ŵ���С��width
	 * @param height
	 *            ԭʼͼ�Ŵ���С��height
	 * @param rect
	 *            Ŀ��ͼ����ĸ�ʽ(rect.x, rect.y, rect.width, rect.height)
	 * @throws IOException
	 * @author Sun Yanjun
	 */
	public static void cut(String srcImageFile, String destImageFile,
			int width, int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(new File(srcImageFile));
		BufferedImage bImage = makeThumbnail(image, width, height);
		System.out.println("���ź�ͼƬ��"+bImage.getWidth()+" �ߣ�"+bImage.getHeight());
		String fileType = srcImageFile.substring(srcImageFile.lastIndexOf(".")+1).toLowerCase();
		String tempFile = srcImageFile.substring(0,srcImageFile.lastIndexOf("/")+1)+"temp"+srcImageFile.substring(srcImageFile.lastIndexOf("/")+1);

		// ��ԭʼͼƬ���
		ImageIO.write(bImage, fileType, new File(tempFile));
		ReadSmb.removeFile(tempFile);

		saveSubImage(bImage, rect, new File(destImageFile));
	}

	/**
	 * @Description: ��srcImageFile�ü�������destImageFile
	 * @param srcImageFile
	 *            ԭʼͼ
	 * @param destImageFile
	 *            Ŀ��ͼ
	 * @param width
	 *            ԭʼͼԤ�����width
	 * @param height
	 *            ԭʼͼԤ�����height
	 * @param rect
	 *            Ŀ��ͼ����ĸ�ʽ(rect.x, rect.y, rect.width, rect.height)
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
	 * @Description: ��ԭʼͼƬ����(x, y, width, height) = (0, 0, width,
	 *               height)�������ţ�����BufferImage
	 * @param img
	 * @param width
	 *            Ԥ�����ͼƬ�Ŀ��
	 * @param height
	 *            Ԥ�����ͼƬ�߶�
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
	 * @Description: ��BufferImage����(x, y, width, height) = (subImageBounds.x,
	 *               subImageBounds.y, subImageBounds.width,
	 *               subImageBounds.height)���вü�
	 *               ���subImageBounds��Χ�������ÿհ������Χ������
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
	 * �ü�ͼƬ���޷����ţ�
	 * @param srcImageFile ԭʼ�ļ�,http��ͷ��ͼƬ��ַ
	 * @param x x����
	 * @param y y����
	 * @param width ����ͼƬ���
	 * @param height ����ͼƬ�߶�
	 * @param fileName �����ļ���
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
			// ��ȡͼƬ�ļ�
			is = new FileInputStream(imageFile);
			/*
			 * ���ذ������е�ǰ��ע�� ImageReader �� Iterator����Щ ImageReader �����ܹ�����ָ����ʽ��
			 * ������formatName - ��������ʽ��ʽ���� .(���� "jpeg" �� "tiff")�� ��
			 */
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName(imageFile.substring(imageFile.lastIndexOf(".")+1));
			ImageReader reader = it.next();
			// ��ȡͼƬ��
			iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:��ȡԴ��true:ֻ��ǰ���� </p>.�������Ϊ ��ֻ��ǰ��������
			 * ��������ζ�Ű���������Դ�е�ͼ��ֻ��˳���ȡ���������� reader ���⻺���������ǰ�Ѿ���ȡ��ͼ����������ݵ���Щ���벿�֡�
			 */
			reader.setInput(iis, true);
			/*
			 * <p>������ζ������н������<p>.����ָ�����������ʱ�� Java Image I/O
			 * ��ܵ��������е���ת��һ��ͼ���һ��ͼ�������ض�ͼ���ʽ�Ĳ�� ������ ImageReader ʵ�ֵ�
			 * getDefaultReadParam �����з��� ImageReadParam ��ʵ����
			 */
			ImageReadParam param = reader.getDefaultReadParam();
			/*
			 * ͼƬ�ü�����Rectangle ָ��������ռ��е�һ������ͨ�� Rectangle ����
			 * �����϶��������(x��y)����Ⱥ͸߶ȿ��Զ����������
			 */
			Rectangle rect = new Rectangle(x, y, width, height);
			// �ṩһ�� BufferedImage���������������������ݵ�Ŀ�ꡣ
			param.setSourceRegion(rect);
			/*
			 * ʹ�����ṩ�� ImageReadParam ��ȡͨ������ imageIndex ָ���Ķ��󣬲��� ����Ϊһ��������
			 * BufferedImage ���ء�
			 */
			BufferedImage bi = reader.read(0, param);
			// ������ͼƬ
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
	 * ����ͼƬ������
	 * @param srcImageFile http��ͷ��ͼƬ��ַ
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
	 * �ü������ƶ����ͼƬ
	 * @param src ԭʼ�ļ�,http��ͷ��ͼƬ��ַ
	 * @param x x����
	 * @param y y����
	 * @param finalWidth ����ͼƬ���
	 * @param finalHeight ����ͼƬ�߶�
	 * @param orgWidth ԭʼͼƬ���
	 * @param orgHeight ԭʼͼƬ�߶�
	 * @param zoom ���ű���
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
			//����ͼƬ�������·��
			imageFile = readRemoteFile(src);
			//��ͼ
			String bigFilename = "big" + imageFile.substring(imageFile.lastIndexOf("/") + 1);
			String smallFilename = "small" + imageFile.substring(imageFile.lastIndexOf("/") + 1);
			//�ü���Ĵ�ͼ����·��
			bigFile = imageFile.substring(0, imageFile.lastIndexOf("/") + 1) + bigFilename;
			
			//����ͼ
			smallFile = imageFile.substring(0, imageFile.lastIndexOf("/") + 1) + smallFilename;
	
			Rectangle rect = new Rectangle(x, y, finalWidth, finalHeight);
			// ��� * ������
			ImageHepler.cut(imageFile, bigFile, (int) (orgWidth * zoom),
					(int) (orgHeight * zoom), rect);
			
	        String smbPath = src.substring(0,src.lastIndexOf("/")+1).replace(ImageHepler.httpPrefix, ImageHepler.smbPrefix);
	        ReadSmb.readLocalToSmb(bigFile, smbPath, bigFilename);
	        

			rect = new Rectangle(0, 0, 120, 150);
			
			float smallZoom = 120f/finalWidth;
			// ��� * ������
			ImageHepler.cut(bigFile, smallFile, (int) (finalWidth * smallZoom),
					(int) (finalHeight * smallZoom), rect);
	        ReadSmb.readLocalToSmb(smallFile, smbPath, smallFilename);
	        
			return src.substring(0, src.lastIndexOf("/") + 1) + bigFilename;
		} finally {
			//ɾ��������ʱ�ļ�
	        System.out.println(ReadSmb.removeFile(imageFile));
	        System.out.println(ReadSmb.removeFile(bigFile));
	        System.out.println(ReadSmb.removeFile(smallFile));
		}
	}
	
	public static void main(String[] args) {
		try {
			String picPath = "c:/1.png";
			String destPath = "c:/11.png";
//			//�ĸ������ֱ���x���ꡢy���ꡢ����ͼƬ��ȡ�����ͼƬ�߶�
//			Rectangle rect = new Rectangle(1223, 923, 120, 150);
//			//���  * ������
//			ImageHepler.cut(picPath, destPath, (int)(1600*1.8), (int)(1200*1.8), rect);
			

//			Rectangle rect = new Rectangle(0, 0, 120, 150);
			//���  * ������
//			ImageHepler.cut(picPath, destPath, (int)(1366*0.2), (int)(768*0.2), rect);
			
			
//			ImageHepler.cut(ImageHepler.httpPrefix+"/upfile/doctor/121/48514.jpg",
//					167, 5, 120, 150, 439, 300, 1, "m48514.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
