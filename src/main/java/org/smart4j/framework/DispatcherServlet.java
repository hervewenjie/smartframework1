package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengwenjie on 2017/2/6.
 * 以上过程都是在为这一步做准备, 我们现在需要写一个Servlet, 让它来处理所有的请求
 * 从 HttpServletRequest 对象中获取请求方法与请求路径, 通过ControllerHelper#getHandler方法
 * 获取Handler对象, 拿到Handler对象后我们可以方便地获取Controller类, 进而通过BeanHelper.getBean方法
 * 获取Controller对象实例
 * 随后可以从 HttpServletRequest 对象中获取所有请求参数, 并将其初始化到一个名为 Param 的对象中
 *
 * MVC框架中最核心的 DispatchServlet 类
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 加载工具类, 运行静态块
        HelperLoader.init();
        // 获取 ServletContext 对象, 用于注册 Servlet
        ServletContext servletContext = servletConfig.getServletContext();
        // 注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

        System.out.println("Init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service");
        // 获取请求方法与请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath   = req.getPathInfo();
        // 获取 Action 处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler!=null){
            // 获取Controller 类及其 Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean    = BeanHelper.getBean(controllerClass);
            // 创建请求参数对象
            // 把URL参数和body参数都放到 Map 中
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName  = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            Param param = new Param(paramMap);

            // 调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            // 处理 Action 方法返回值
            if (result instanceof View){
                // 返回 JSP 页面
                View view = (View) result;
                String path = view.getPath();
                if (path.startsWith("/")){
                    resp.sendRedirect(req.getContextPath() + path);
                } else {
                    Map<String, Object> model = view.getModel();
                    for (Map.Entry<String, Object> entry : model.entrySet()){
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    // /WEB-INF/jsp/hello.jsp
                    // 加载和解析JSP工作由Tomcat处理
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                }

            } else if (result instanceof Data){
                // 返回 JSON 数据
                Data data = (Data) result;
                Object model = data.getModel();
                if (model!=null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }

}
