package de.culo.lucyo;

import java.net.URI;

public final class CameraCalls {

    private CameraCalls() {
        // hide this
    }

    private static final String IP = "http://192.168.0.121:88/";

    private static final String USERNAME = "user2";

    private static final String PASSWORD = "media2";

    private static final String PREFIX = "/cgi-bin/CGIProxy.fcgi?";

    public static URI takeAPictureURI() {
        return URI.create(IP + "CGIProxy.fcgi?cmd=snapPicture2&usr=user2&pwd=media2");
    }

}
