package com.jd.MethodLimit.htmlLayout;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LoggingEvent;

import com.jd.MethodLimit.bean.MethodExecuteLimitBean;
import com.jd.MethodLimit.bean.MethodExecuteLogBean;

public class MethodExecuteLogLimitHtmlLayout extends Layout {

	protected final int BUF_SIZE = 256;
	protected final int MAX_CAPACITY = 1024;

	private static DateFormat MMDD_HHMMSS = new SimpleDateFormat("MM-dd HH:mm:ss");
	 
	private static String CLASS_NAME_QPS = "qps";
	static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";

	// output buffer appended to when format() is invoked
	private StringBuffer sbuf = new StringBuffer(BUF_SIZE);

	/**
	 * A string constant used in naming the option for setting the the location
	 * information flag. Current value of this string constant is
	 * <b>LocationInfo</b>.
	 * 
	 * <p>
	 * Note that all option keys are case sensitive.
	 * 
	 * @deprecated Options are now handled using the JavaBeans paradigm. This
	 *             constant is not longer needed and will be removed in the
	 *             <em>near</em> term.
	 * 
	 */
	public static final String LOCATION_INFO_OPTION = "LocationInfo";

	/**
	 * A string constant used in naming the option for setting the the HTML
	 * document title. Current value of this string constant is <b>Title</b>.
	 */
	public static final String TITLE_OPTION = "Title";

	// Print no location info by default
	boolean locationInfo = false;

	String title = "方法执行QPS日志";
	
	/**
	 * The <b>LocationInfo</b> option takes a boolean value. By default, it is
	 * set to false which means there will be no location information output by
	 * this layout. If the the option is set to true, then the file name and
	 * line number of the statement at the origin of the log statement will be
	 * output.
	 * 
	 * <p>
	 * If you are embedding this layout within an
	 * {@link org.apache.log4j.net.SMTPAppender} then make sure to set the
	 * <b>LocationInfo</b> option of that appender as well.
	 */
	public void setLocationInfo(boolean flag) {
		locationInfo = flag;
	}

	/**
	 * Returns the current value of the <b>LocationInfo</b> option.
	 */
	public boolean getLocationInfo() {
		return locationInfo;
	}

	/**
	 * The <b>Title</b> option takes a String value. This option sets the
	 * document title of the generated HTML document.
	 * 
	 * <p>
	 * Defaults to 'Log4J Log Messages'.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the current value of the <b>Title</b> option.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the content type output by this layout, i.e "text/html".
	 */
	public String getContentType() {
		return "text/html";
	}

	/**
	 * No options to activate.
	 */
	public void activateOptions() {
	}

	public String format(LoggingEvent event) {

		if (sbuf.capacity() > MAX_CAPACITY) {
			sbuf = new StringBuffer(BUF_SIZE);
		} else {
			sbuf.setLength(0);
		}
		Object obj = event.getMessage();
		if(obj instanceof MethodExecuteLimitBean){
			MethodExecuteLimitBean logBean = (MethodExecuteLimitBean)obj ;
			sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

			sbuf.append("<td>");
			sbuf.append(logBean.getIp());
			sbuf.append("</td>" + Layout.LINE_SEP);
			
			sbuf.append("<td>");
			sbuf.append(logBean.getHostIp());
			sbuf.append("</td>" + Layout.LINE_SEP);
			
			sbuf.append("<td>");
			sbuf.append(MMDD_HHMMSS.format(new Date(Long.parseLong(logBean.getStartSeconds()+"000"))));
			sbuf.append("</td>" + Layout.LINE_SEP);
			
			sbuf.append("<td>");
			sbuf.append(logBean.getMethodSign());
			sbuf.append("</td>" + Layout.LINE_SEP);
			
	 
			
			sbuf.append("<td class='"+CLASS_NAME_QPS+"'>");
			sbuf.append(logBean.getQps().get() );
			sbuf.append("</td>" + Layout.LINE_SEP);
			
			sbuf.append("<td>");
			sbuf.append(logBean.getWarnLimit());
			sbuf.append("</td>" + Layout.LINE_SEP);
			
			sbuf.append("<td>");
			sbuf.append(logBean.getUnavailableLimit());
			sbuf.append("</td>" + Layout.LINE_SEP+"</tr>");
		}

		return sbuf.toString();
	}

	void appendThrowableAsHTML(String[] s, StringBuffer sbuf) {
		if (s != null) {
			int len = s.length;
			if (len == 0)
				return;
			sbuf.append(Transform.escapeTags(s[0]));
			sbuf.append(Layout.LINE_SEP);
			for (int i = 1; i < len; i++) {
				sbuf.append(TRACE_PREFIX);
				sbuf.append(Transform.escapeTags(s[i]));
				sbuf.append(Layout.LINE_SEP);
			}
		}
	}

	/**
	 * Returns appropriate HTML headers.
	 */
	public String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
						+ Layout.LINE_SEP);
		sbuf.append("<html>" + Layout.LINE_SEP);
		sbuf.append("<head>" + Layout.LINE_SEP);
		sbuf.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sbuf.append("<title>" + title + "</title>" + Layout.LINE_SEP);
		sbuf.append("<style type=\"text/css\">" + Layout.LINE_SEP);
		sbuf.append("<!--" + Layout.LINE_SEP);
		sbuf.append("body, table {font-family: arial,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
		sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
		sbuf.append("-->" + Layout.LINE_SEP);
		sbuf.append("</style>" + Layout.LINE_SEP);
		sbuf.append("<script type=\"text/javascript\" src=\"http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js\"></script>");
		sbuf.append("<script type=\"text/javascript\">"
			   +" function showTotalQps(){"
			   +"     var qps = $(\"."+CLASS_NAME_QPS+"\");"
			   +"     var total = 0 ;"
			   +"      for (var i = qps.length - 1; i >= 0; i--) {"
			   +"        total += ($(qps[i]).html()) * 1;"
			   +"     };"
			   +"     alert(\"total qps : \" + total);"
			   +"  }     "
			   + "</script>");
		sbuf.append("</head>" + Layout.LINE_SEP);
		sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
		sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
		sbuf.append("Log session start time " + new java.util.Date() + "<br>" + Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("<button onclick=\"showTotalQps()\">Total QPS</button>");
		sbuf.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">"
				+ Layout.LINE_SEP);
		sbuf.append("<tr>" + Layout.LINE_SEP);
		sbuf.append("<th>请求IP</th>" + Layout.LINE_SEP);
		sbuf.append("<th>主机</th>" + Layout.LINE_SEP);
		sbuf.append("<th>时间</th>" + Layout.LINE_SEP);
		sbuf.append("<th>方法</th>" + Layout.LINE_SEP);
		sbuf.append("<th>QPS</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Warn</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Unavailable</th>" + Layout.LINE_SEP);
  		sbuf.append("</tr>" + Layout.LINE_SEP);
		return sbuf.toString();
	}

	/**
	 * Returns the appropriate HTML footers.
	 */
	public String getFooter() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("</table>" + Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("</body></html>");
		return sbuf.toString();
	}

	/**
	 * The HTML layout handles the throwable contained in logging events. Hence,
	 * this method return <code>false</code>.
	 */
	public boolean ignoresThrowable() {
		return false;
	}
}
