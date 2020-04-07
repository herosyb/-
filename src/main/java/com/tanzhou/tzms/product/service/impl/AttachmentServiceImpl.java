package com.tanzhou.tzms.product.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tanzhou.tzms.common.exception.ServiceException;
import com.tanzhou.tzms.product.dao.AttachmentDao;
import com.tanzhou.tzms.product.domain.Attachment;
import com.tanzhou.tzms.product.service.AttachmentService;
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao dao;
	@Override
	public void save(String title,MultipartFile mFile) {
		//将文件存储到服务器
		//将文件信息存储到数据库
		//对参数做验证
		if(title==null||title.trim().length()==0){
			throw new ServiceException("标题不能为空");
		}
		if(mFile==null){
			throw new ServiceException("不允许上传空文件");
		}
		//根据mFile获取文件内容，通过md5生成摘要信息
		byte[] bytes;
		String digest = null;
		try {
			bytes = mFile.getBytes();
			digest = DigestUtils.md5DigestAsHex(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("文件上传失败");
		}
		//根据生成的摘要信息去数据库进行查询
		Integer count = dao.getRowCountByDigest(digest);
		//根据查询的结果判断文件是否上传
		if(count>0){
			//文件已经上传过滤
			throw new ServiceException("文件已上传，不能再次上传");
		}
		//如果文件不存在，则上传
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateDir = sdf.format(new Date());
		File fileDir = new File("E:"+File.separator+"uploads"+File.separator+dateDir);
		if(!fileDir.exists()){//如果文件夹不存在，则创建文件夹
			fileDir.mkdirs();
		}
		String filename = mFile.getOriginalFilename();//获取文件名字
		File file = new File(fileDir,filename);//e:/uploads/2019-11-01/xx.xml
		try {
			//上传文件
			mFile.transferTo(file);
		} catch (IOException e) {
			//上传失败
			e.printStackTrace();
			throw new ServiceException("文件上传失败");
		}
		//文件上传之后，需要将文件信息保存到数据库
		
		//保存操作
		Attachment a = new Attachment();
		a.setTitle(title);
		a.setFileName(mFile.getOriginalFilename());
		a.setContentType(mFile.getContentType());
		a.setFilePath(file.getAbsolutePath());//获取文件绝对路径
		a.setFileDisgest(digest);//摘要
		Integer row = dao.insertAttachment(a);
		if(row<1){
			throw new ServiceException("新增失败");
		}
	}
	
	@Override
	public List<Attachment> findAttachment() {
		List<Attachment> list = dao.findAttachment();
		return list;
	}

	@Override
	public Attachment findAttachmentById(Integer id) {
		Attachment ac = dao.findAttachmentById(id);
		return ac;
	}

}
