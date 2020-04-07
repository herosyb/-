package com.tanzhou.tzms.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tanzhou.tzms.product.domain.Attachment;

public interface AttachmentService {

	public void save(String title,MultipartFile mFile);
	
	public List<Attachment> findAttachment();

	public Attachment findAttachmentById(Integer id);
}
