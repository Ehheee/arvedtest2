package thething.arved.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Used when I couldn't get UTF-8 support in URLs to work. Eventually it turned out that a spring filter was always filtering requests before any other.
 * This happened with all filter order configurations in web.xml. To overcome this, filter was added straight to tomcat server and UTF-8 started to work.
 * Kept in the project for future references on creating filters and also in case there would be similar issues again and I would need to debug by logging rows
 * or throwing exceptions from this filter.
 * @author Kaur
 *
 */

public class CharsetFilter implements Filter{
	private Log logger = LogFactory.getLog(getClass());
	private String encoding;

	public void init(FilterConfig config) throws ServletException{
		encoding = config.getInitParameter("requestEncoding");
		if( encoding==null ) encoding="UTF-8";
	}

 	public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)throws IOException, ServletException{
 		// Respect the client-specified character encoding
 		// (see HTTP specification section 3.4.1)
 		if (request instanceof HttpServletRequest) {
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		logger.info("Doing charsetfilter " + request.getCharacterEncoding() + " " + url);
		}if(null == request.getCharacterEncoding()){
			request.setCharacterEncoding(encoding);
		}

	/**
	* Set the default response content type and encoding
	*/
	response.setContentType("text/html; charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
	next.doFilter(request, response);
	}
	
 	public void destroy(){}
	}