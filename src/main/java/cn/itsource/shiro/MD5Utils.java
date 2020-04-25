package cn.itsource.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
    /*加密方式*/
    private static String hashAlgorithmName = "MD5";
    /*加密次数*/
    private static Integer hashIterations = 9;
    /*盐值*/
    private static ByteSource salt = ByteSource.Util.bytes("abidingly");

    /**
     * 返回加密后的md5密码
     */
    public static String getMD5Password(String source) {
        return new SimpleHash(hashAlgorithmName, source, salt, hashIterations).toString();
    }
}
