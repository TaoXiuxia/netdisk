package com.itheima.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.domain.Resource;
import com.itheima.util.DaoUtils;
import com.itheima.util.IOUtils;

public class DownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		String sql = "select * from netdisk where id=?";
		Resource r = null;
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		try {
			r = runner.query(sql, new BeanHandler<Resource>(Resource.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//资源不存在，提示
		if (r == null) {
			response.getWriter().write("找不到该资源");
			return;
		}else{
			//资源存在
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(r.getRealname(),"utf-8"));
			response.setContentType(this.getServletContext().getMimeType(r.getRealname()));
			String filepath=this.getServletContext().getRealPath(r.getSavepath()+"/"+r.getUuidname());
			InputStream in=new FileInputStream(new File(filepath));
			OutputStream out=response.getOutputStream();
			
			IOUtils.In2Out(in, out);
			IOUtils.close(in, null);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
