package org.openmrs.module.hospitalcore.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtil {
    public static String getCurrentLink(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static String getPathInfo(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }

    public static String getSessionString(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession();
        if (session.getAttribute(attributeName) == null)
            return null;
        return session.getAttribute(attributeName).toString();
    }

    public static Object getSessionObject(HttpServletRequest request, String attributeName, Class<?> clazz) {
        HttpSession session = request.getSession();
        if (session != null) {
            Object object = session.getAttribute(attributeName);
            if (object != null)
                try {
                    return object;
                } catch (Exception exception) {}
        }
        return null;
    }

    public static void removeAttribute(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.removeAttribute(attributeName);
    }

    public static String getSessionStringAndRemove(HttpServletRequest request, String attributeName) {
        String value = getSessionString(request, attributeName);
        removeAttribute(request, attributeName);
        return value;
    }
}
