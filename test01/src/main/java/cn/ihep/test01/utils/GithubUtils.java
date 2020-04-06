package cn.ihep.test01.utils;

public class GithubUtils {
    private final static String CLIENT_SECRET = "fb7e6356470240473baff2ea3d831da0ea39b487";

    private final static String CLINENT_ID = "5f10603639ec23117aac";

    private final static String TOKEN_URL = "https://github.com/login/oauth/access_token";

    private final static String INFO_URL = "https://api.github.com/user";

    /**
     * 返回一条完整的带有参数的token请求链接
     *
     * @param code
     * @return
     */
    public static String getTokenUrl(String code) {
        return TOKEN_URL + "?client_id=" + CLINENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + code;
    }

    public static String getInfoUrl(String token) {
        return INFO_URL + "?access_token=" + token;
    }
}
