package com.tanzhou.tzms.product.dao;

import java.util.List;

import com.tanzhou.tzms.product.domain.Attachment;

public interface AttachmentDao {

	/**
	 * 附件上传把相关信息添加到数据库
	 * @param a
	 * @return
	 */
	public Integer insertAttachment(Attachment a);
	
	public Integer getRowCountByDigest(String filedigest);
	
	/**
	 * 查询所有附件
	 * @return
	 */
	public List<Attachment> findAttachment();
	/**
	 * 根据id查询附件对象
	 * @param id
	 * @return
	 */
	public Attachment findAttachmentById(Integer id);
}
