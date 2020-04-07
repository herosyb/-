package com.tanzhou.tzms.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tanzhou.tzms.common.web.JsonResult;
import com.tanzhou.tzms.product.domain.Attachment;
import com.tanzhou.tzms.product.service.AttachmentService;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	@Autowired
	private AttachmentService service;
	
	@RequestMapping("/listUI")
	public String listUI(){
		return "attachment/attachment";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(String title,MultipartFile mFile) throws IllegalStateException, IOException{
		service.save(title, mFile);
		return new JsonResult("上传成功");
	}
	@RequestMapping("/findAttachments")
	@ResponseBody
	public JsonResult findAttachment(){
		List<Attachment> list = service.findAttachment();
		return new JsonResult(list);
	}
	@RequestMapping("/download")
	@ResponseBody
	//下载：将服务器端的文件以流的形式写到客户端
	//ResponseEntity
	public ResponseEntity<byte[]> testDownload(Integer id) throws Exception{
		//根据id去数据库查询数据
		//
		Attachment ac = service.findAttachmentById(id);
		//将要下载的文件读取成一个字节数组
		byte[] buf ;
		//读取文件数据
		FileInputStream in = new FileInputStream(ac.getFilePath());
		//创建实际的字节数组
		buf = new byte[in.available()];
		in.read(buf);
		//将响应数据和响应信息全部都封装到ResponseEntity
		HttpHeaders httpHeaders = new HttpHeaders();
		//编码: 将看得懂的转成看不懂的
		//解码：将看不懂的转成看的懂的
		String fileName = new String(ac.getFileName().getBytes("UTF-8"),"ISO8859-1");
		httpHeaders.add("Content-Disposition", "attachment;filename="+fileName);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(buf, httpHeaders, HttpStatus.OK);
		return responseEntity;
	}
}
