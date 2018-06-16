package util;

public class MessagesUtility {

    public static String getSimpleMessageFromException(String message) {
        return message.substring(message.lastIndexOf(':') + 1);
    }
}
