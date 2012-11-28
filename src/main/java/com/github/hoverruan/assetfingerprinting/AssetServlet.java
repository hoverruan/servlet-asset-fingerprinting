package com.github.hoverruan.assetfingerprinting;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author Hover Ruan
 */
public class AssetServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        addCacheHeaders(response);

        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        String requestResourcePath = requestURI.substring(servletPath.length());
        int realPathOffset = requestResourcePath.indexOf('/', 1);
        String realResourcePath = requestResourcePath.substring(realPathOffset);

        request.getRequestDispatcher(realResourcePath).forward(servletRequest, servletResponse);
    }

    private void addCacheHeaders(HttpServletResponse response) {
        long tenYearsAfter = tenYearsAfterInTimestamp();

        response.addDateHeader("Expires", tenYearsAfter);
        response.addHeader("Cache-Control", "max-age=" + (tenYearsAfter - System.currentTimeMillis()) / 1000);
    }

    private static long tenYearsAfterInTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 10);

        return calendar.getTime().getTime();
    }
}
