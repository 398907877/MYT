package com.yihu.myt.mgr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.fileupload.FileItem;

import com.coreframework.vo.ReturnValue;

/*import com.common.core.ReturnValue;*/

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * ͨ��SMBЭ�鴫���ļ�
 * @author Administrator
 *
 */
public class ReadSmb {
	
	static Logger log = Logger.getLogger("ReadSmb");

	/**
	 * ��ȡ�����ļ��洢������·��smbPath��
	 * 
	 * @param file
	 *            ���ؾ���·���ļ���
	 * @param smbPath
	 *            ����������ļ���,��smb://xxx:xxx@10.0.0.1/myDocument/,
	 *            xxx:xxx�ǹ���������û�������
	 * @return
	 */
	public static ReturnValue readLocalToSmb(String file, String smbPath, String fileName) {
		File localFile = null;
		InputStream in = null;
		OutputStream out = null;
		SmbFile remoteFile = null;
		try {
			log.info("׼������" + file);
			localFile = new File(file);
			if (!localFile.exists()) {
				log.info(file + " ������");
				return new ReturnValue(-1, file + "�ļ�������");
			}
			in = new BufferedInputStream(new FileInputStream(localFile));

			SmbFile smbDir = new SmbFile(smbPath);
			if (!smbDir.exists()) {
				smbDir.mkdirs();
				log.info("������Ŀ¼��" + smbPath);
			}
			remoteFile = new SmbFile(smbPath + fileName);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

			int length = 0;
			//1M����
			byte[] buffer = new byte[1024 * 1 * 1024];
			Date date = new Date();
			log.info("��ʼ�����ļ�");
//			if (in.read(buffer) != -1)
//				out.write(buffer);
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				length = length + len;
			}
			log.info("��ɱ����ļ�:" + localFile.getAbsolutePath());
			Date end = new Date();
			int time = (int) ((end.getTime() - date.getTime()) / 1000);
			if (time > 0)
				log.info("��ʱ:" + time + "�� " + "�ٶ�:" + length / time
						/ 1024 + "kb/��");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ReturnValue(1, "�ϴ��ɹ�");
	}
	
	
	public static ReturnValue readUpfileToSmb(FileItem item, String smbPath, String smbFilename) {
		InputStream in = null;
		OutputStream out = null;
		SmbFile remoteFile = null;
		try {
			in = new BufferedInputStream(item.getInputStream());

			SmbFile smbDir = new SmbFile(smbPath);
			if (!smbDir.exists()) {
				smbDir.mkdirs();
				log.info("������Ŀ¼��" + smbPath);
			}
			remoteFile = new SmbFile(smbPath + smbFilename);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

			int length = 0;
			//1M����
			byte[] buffer = new byte[1024 * 1 * 1024];
			Date date = new Date();
			log.info("��ʼ�����ļ�");
//			if (in.read(buffer) != -1)
//				out.write(buffer);
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				length = length + len;
			}
			log.info("��ɱ����ļ�:" + item.getName());
			Date end = new Date();
			int time = (int) ((end.getTime() - date.getTime()) / 1000);
			if (time > 0)
				log.info("��ʱ:" + time + "�� " + "�ٶ�:" + length / time
						/ 1024 + "kb/��");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "�ϴ�ʧ��");
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return new ReturnValue(-1, "�ر��ļ��쳣");
			}
		}
		return new ReturnValue(1, "�ϴ��ɹ�");
	}

	
	
	/**
	 * ɾ���ļ�
	 * 
	 * @param smbFile
	 *            ����������ļ�,��smb://xxx:xxx@10.0.0.1/myDocument/�����ı�.txt,
	 *            xxx:xxx�ǹ���������û�������
	 * @return
	 */
	public static boolean deleteFile(String smbFile) {
		try {
			System.out.println("׼��ɾ��"+smbFile);
			SmbFile remoteFile = new SmbFile(smbFile);
			if (!remoteFile.exists()) {
				log.info(smbFile + " ������");
				return false;
			}
			remoteFile.delete();
			return true;
		} catch (SmbException e) {
			e.printStackTrace();

			// return new MessageHelper(-100,"�����쳣(���粻�ɴ��)");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	/**
	 * ��ȡ�ļ���С����λ�ֽ�
	 * 
	 * @param smbFile
	 *            ����������ļ�,��smb://xxx:xxx@10.0.0.1/myDocument/�����ı�.txt,
	 *            xxx:xxx�ǹ���������û�������
	 * @return
	 */
	public static int readFileSize(String smbFile) {
		try {
			SmbFile remoteFile = new SmbFile(smbFile);
			if (!remoteFile.exists()) {
				log.info(smbFile + " ������");
				return -1;
			}
			return remoteFile.getContentLength();
		} catch (SmbException e) {
			e.printStackTrace();

			// return new MessageHelper(-100,"�����쳣(���粻�ɴ��)");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return -1;
	}
	
	public static boolean removeFile(String filepath) {
		try {
			System.out.println("ɾ���ļ���"+filepath);
			File file = new File(filepath);
			if(!file.exists())
				return false;
			return removeFile(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean removeFile(File file) {
		return file.delete();
	}
	
	public static boolean renameSmbFile(String oldFile, String newFile) {
		try {
			System.out.println("Rename <<"+oldFile+">> to <<"+newFile+">>");
			SmbFile oldSmbFile = new SmbFile(oldFile);
			SmbFile newSmbFile = new SmbFile(newFile);
			oldSmbFile.renameTo(newSmbFile);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		try {
			SmbFile sf = new SmbFile(ImageHepler.smbPrefix+ImageHepler.doctorDir + "710003542.jpg");
			System.out.println(sf.getURL());
			System.out.println(sf.exists());
//			ReadSmb.deleteFile(sf1.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
