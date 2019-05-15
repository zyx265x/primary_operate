package com.zt.operate.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * HttpServletResponse帮助类
 * 
 * @author liufang
 * 
 */
public final class ResponseUtils {
	public static final Logger log = LoggerFactory
			.getLogger(ResponseUtils.class);

	/**
	 * 发送文本。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 发送json。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json;charset=UTF-8", text);
	}

	/**
	 * 发送xml。使用UTF-8编码。
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/xml;charset=UTF-8", text);
	}
	
	
	public static void renderHtml(HttpServletResponse response, String text) {
		render(response, "text/html;charset=UTF-8", text);
	}

	/**
	 * 发送内容。使用UTF-8编码。
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 下载文件
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	public static void download(File file, HttpServletResponse response) throws Exception {
		FileInputStream in = null;
		OutputStream out = null;
		String fileName = file.getName();
		// 设置response的编码方式
		response.setContentType("application/octet-stream");
		// 写明要下载的文件的大小
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int) file.length());
		// 解决中文乱码
		fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
		// 设置响应头为attachment(附件)
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		// 输出文件供用户下载
		in = new FileInputStream(file);
		try {
			out = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);

		} catch (Exception e) {
			throw new Exception("下载资源失败！" + e.getMessage(), e);
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (file != null)
					file = null;
			} catch (Exception ex) {
			}
		}
	}

}
