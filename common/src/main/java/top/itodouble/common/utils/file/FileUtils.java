package top.itodouble.common.utils.file;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileUtils {
	public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";


	/**
	 * 获取文件前缀
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}
		return null;
	}

	/**
	 * 获取文件后缀 例如html
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		}
		return null;
	}

	/**
	 * 删除
	 *
	 * @param sourceFilePath
	 * @return
	 */
	public static void deleteFiles(String sourceFilePath) {
		try {
			File file = new File(sourceFilePath);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过url下载文件
	 *
	 * @param fileName
	 * @return
	 */
	public static void downloadFile(String urlPath, String savePath, String fileName) {
		try {
			// 统一资源
			URL url = new URL(urlPath);
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			//设置超时
			httpURLConnection.setConnectTimeout(1000 * 5);
			//设置请求方式
			httpURLConnection.setRequestMethod("GET");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此URL引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();

			// 建立链接从请求中获取数据
			URLConnection con = url.openConnection();
			InputStream bin = httpURLConnection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(bin);
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}

			FileOutputStream out = new FileOutputStream(savePath + fileName);
			int size = 0;
			int len = 0;
			byte[] buf = new byte[2048];
			while ((size = bin.read(buf)) != -1) {
				len += size;
				out.write(buf, 0, size);
			}
			// 关闭资源
			bin.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取远程文件并返回file（临时文件 使用完毕后勿忘删除）
	 *
	 * @param remoteFilePath 远程文件地址
	 * @param prefix         文件名
	 * @param suffix         文件后缀
	 * @return
	 */
	public static File getFile(String remoteFilePath, String prefix, String suffix) {
		URL website = null;
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			file = File.createTempFile(prefix, "." + suffix);
			website = new URL(remoteFilePath);
			rbc = Channels.newChannel(website.openStream());
			//fos = new FileOutputStream(localFilePath);//本地要存储的文件地址 例如：test.txt
			fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (rbc != null) {
				try {
					rbc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 获取网络文件的输入流
	 *
	 * @param remoteFilePath
	 * @return
	 */
	public static InputStream getInputStream(String remoteFilePath) {
		DataInputStream in = null;
		try {
			URL url = new URL(remoteFilePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(conn.getInputStream());
		} catch (IOException e) {
			System.err.println("url转换输入流失败,错误信息{}" + e.getMessage());
		}
		return in;
	}

	public static byte[] streamToByte(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = 0;
		byte[] buffer = new byte[8 * 1024];
		try {
			while ((c = is.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 输出指定文件的byte数组
	 *
	 * @param filePath 文件路径
	 * @param os       输出流
	 * @return
	 */
	public static void writeBytes(String filePath, OutputStream os) throws IOException {
		FileInputStream fis = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException(filePath);
			}
			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int length;
			while ((length = fis.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 输出指定文件的byte数组
	 *
	 * @param file 文件
	 * @param os       输出流
	 * @return
	 */
	public static void writeBytes(File file, OutputStream os) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int length;
			while ((length = fis.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 删除文件
	 *
	 * @param filePath 文件
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除文件
	 *
	 * @param file 文件
	 * @return
	 */
	public static boolean deleteFile(File file) {
		boolean flag = false;
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 下载文件名重新编码
	 *
	 * @param request  请求对象
	 * @param fileName 文件名
	 * @return 编码后的文件名
	 */
	public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
			throws UnsupportedEncodingException {
		final String agent = request.getHeader("USER-AGENT");
		String filename = fileName;
		if (agent.contains("MSIE")) {
			// IE浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			filename = new String(fileName.getBytes(), "ISO8859-1");
		} else if (agent.contains("Chrome")) {
			// google浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			// 其它浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}

	/**
	 * 文件名称验证
	 *
	 * @param filename 文件名称
	 * @return true 正常 false 非法
	 */
	public static boolean isValidFilename(String filename) {
		return filename.matches(FILENAME_PATTERN);
	}

}
