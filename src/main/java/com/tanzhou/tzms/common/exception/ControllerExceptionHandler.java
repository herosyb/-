package com.tanzhou.tzms.common.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tanzhou.tzms.common.web.JsonResult;
/**通过此注解声明此类为一个全局异常处理类型*/
@ControllerAdvice
public class ControllerExceptionHandler {
	/**当spring发现系统出现异常了,且异常的类型为ServiceException类型的话，就会回调此方法,并将异常值传递给这个方法,
	 * 这时我们就可以在此方法中对业务异常进行
	 * 统一处理,例如封装到jsonResult,然后写到客户端告诉用户
	 * */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handleServiceException(ServiceException e){
		e.printStackTrace();
		//将异常封装到JsonResult
		return new JsonResult(e);
		//this.state=ERROR;
		//this.message=e.getMessage();
	}
//	@ExceptionHandler(RuntimeException.class)
	public ModelAndView  handleRuntimeException(RuntimeException e) {
		System.out.println("handleRuntimeException");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exp", e.getMessage());
		return mv;
	}
}
