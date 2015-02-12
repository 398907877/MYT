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
 * 通过SMB协议传输文件
 * @author Administrator
 *
 */
public class ReadSmb {
	
	static Logger log = Logger.getLogger("ReadSmb");

	/**
	 * 读取本地文件存储到共享路径smbPath下
	 * 
	 * @param file
	 *            本地绝对路径文件名
	 * @param smbPath
	 *            共享机器的文件夹,如smb://xxx:xxx@10.0.0.1/myDocument/,
	 *            xxx:xxx是共享机器的用户名密码
	 * @return
	 */
	public static ReturnValue readLocalToSmb(String file, String smbPath, String fileName) {
		File localFile = null;
		InputStream in = null;
		OutputStream out = null;
		SmbFile remoteFile = null;
		try {
			log.info("准备传输" + file);
			localFile = new File(file);
			if (!localFile.exists()) {
				log.info(file + " 不存在");
				return new ReturnValue(-1, file + "文件不存在");
			}
			in = new BufferedInputStream(new FileInputStream(localFile));

			SmbFile smbDir = new SmbFile(smbPath);
			if (!smbDir.exists()) {
				smbDir.mkdirs();
				log.info("创建新目录：" + smbPath);
			}
			remoteFile = new SmbFile(smbPath + fileName);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

			int length = 0;
			//1M缓冲
			byte[] buffer = new byte[1024 * 1 * 1024];
			Date date = new Date();
			log.info("开始保存文件");
//			if (in.read(buffer) != -1)
//				out.write(buffer);
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				length = length + len;
			}
			log.info("完成保存文件:" + localFile.getAbsolutePath());
			Date end = new Date();
			int time = (int) ((end.getTime() - date.getTime()) / 1000);
			if (time > 0)
				log.info("用时:" + time + "秒 " + "速度:" + length / time
						/ 1024 + "kb/秒");
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
		return new ReturnValue(1, "上传成功");
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
				log.info("创建新目录：" + smbPath);
			}
			remoteFile = new SmbFile(smbPath + smbFilename);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

			int length = 0;
			//1M缓冲
			byte[] buffer = new byte[1024 * 1 * 1024];
			Date date = new Date();
			log.info("开始保存文件");
//			if (in.read(buffer) != -1)
//				out.write(buffer);
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				length = length + len;
			}
			log.info("完成保存文件:" + item.getName());
			Date end = new Date();
			int time = (int) ((end.getTime() - date.getTime()) / 1000);
			if (time > 0)
				log.info("用时:" + time + "秒 " + "速度:" + length / time
						/ 1024 + "kb/秒");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnValue(-1, "上传失败");
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
				return new ReturnValue(-1, "关闭文件异常");
			}
		}
		return new ReturnValue(1, "上传成功");
	}

	
	
	/**
	 * 删除文件
	 * 
	 * @param smbFile
	 *            共享机器的文件,如smb://xxx:xxx@10.0.0.1/myDocument/测试文本.txt,
	 *            xxx:xxx是共享机器的用户名密码
	 * @return
	 */
	public static boolean deleteFile(String smbFile) {
		try {
			System.out.println("准备删除"+smbFile);
			SmbFile remoteFile = new SmbFile(smbFile);
			if (!remoteFile.exists()) {
				log.info(smbFile + " 不存在");
				return false;
			}
			remoteFile.delete();
			return true;
		} catch (SmbException e) {
			e.printStackTrace();

			// return new MessageHelper(-100,"传输异常(网络不可达等)");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	/**
	 * 获取文件大小，单位字节
	 * 
	 * @param smbFile
	 *            共享机器的文件,如smb://xxx:xxx@10.0.0.1/myDocument/测试文本.txt,
	 *            xxx:xxx是共享机器的用户名密码
	 * @return
	 */
	public static int readFileSize(String smbFile) {
		try {
			SmbFile remoteFile = new SmbFile(smbFile);
			if (!remoteFile.exists()) {
				log.info(smbFile + " 不存在");
				return -1;
			}
			return remoteFile.getContentLength();
		} catch (SmbException e) {
			e.printStackTrace();

			// return new MessageHelper(-100,"传输异常(网络不可达等)");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return -1;
	}
	
	public static boolean removeFile(String filepath) {
		try {
			System.out.println("删除文件："+filepath);
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
