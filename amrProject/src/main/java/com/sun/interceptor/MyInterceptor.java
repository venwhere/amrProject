package com.sun.interceptor;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.sun.util.MessageUtil;
import com.sun.util.MimeValidator;
import com.sun.util.ValidatorRules;
public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		String validatorKey=handlerMethod.getBeanType().getSimpleName()+"."+handlerMethod.getMethod().getName()+".rules";
		String validateContent=MessageUtil.getMessage(handlerMethod.getBean(), validatorKey);
		if(validateContent==null||"".equals(validateContent)) {//如果没有规则直接放行
			return true;
		}else {
			String rules[]=validateContent.split("\\|");
			Map<String,String> result=new ValidatorRules(handlerMethod.getBean(),rules,request).validate();
			if(result.size()>0) {//有错误则跳转到错误页面
				request.setAttribute("errors", result);
				request.getRequestDispatcher("/errors.jsp").forward(request, response);
				return false;
			}else {//如果没有错误要验证上传文件信息
				boolean flag=MimeValidator.isMime(handlerMethod.getBean(), request);
				if(flag==false) {
					result.put("file", MessageUtil.getMessage(handlerMethod, "invalidate.file.mime.error.msg"));
					request.getRequestDispatcher("errors.jps").forward(request, response);
					return false;
				}
			}
			return true;
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
