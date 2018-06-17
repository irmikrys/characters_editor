package util;

import javax.faces.context.FacesContext;

public class MessagesUtility {

    public static String getSimpleMessageFromException(String message) {
        return message.substring(message.lastIndexOf(':') + 1);
    }

    public static String getParamFromContext(String paramName) {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(paramName);
    }
}
