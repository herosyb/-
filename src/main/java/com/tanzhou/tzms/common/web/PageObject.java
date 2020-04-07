package com.tanzhou.tzms.common.web;

import java.io.Serializable;

/**
 * 封装具体的分页信息
 * @author Administrator
 *
 */
public class PageObject implements Serializable{
	private static final long serialVersionUID = -8753809986545361268L;
	/**当前页*/
	private int pageCurrent=1;
	/**每页最多能显示的记录数*/
	private int pageSize=3;
	/**总记录数*/
	private int rowCount;
	/**上一页的最后一条记录位置
	 * 对应:limit startIndex,pageSize;
	 */
	private int startIndex;
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
	  	int pages = rowCount/pageSize;//总条数除以每页显示的条数=总页数
    	if(0 != rowCount%pageSize) {//如果总条数除以每页显示条数有余数 
    		pages +=1;//则页数+1
    	}
        return pages;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	
	
	
	
	
	
}
