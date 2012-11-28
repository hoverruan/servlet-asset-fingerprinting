package com.github.hoverruan.assetfingerprinting;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import javax.servlet.jsp.PageContext;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Hover Ruan
 */
public class ResourceFunctions {
    private static Map<String, String> hashTokenCache = new HashMap<String, String>();

    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    public static String resource(PageContext pageContext, String path) throws Exception {
        String hashToken = null;

        try {
            readLock.lock();

            if (hashTokenCache.containsKey(path)) {
                hashToken = hashTokenCache.get(path);
            } else {
                try {
                    readLock.unlock();

                    try {
                        writeLock.lock();

                        URL resource = pageContext.getServletContext().getResource(path);
                        if (resource != null) {
                            byte[] content = IOUtils.toByteArray(resource.openStream());
                            MessageDigest md5 = MessageDigest.getInstance("MD5");
                            hashToken = Hex.encodeHexString(md5.digest(content));
                            hashTokenCache.put(path, hashToken);
                        }
                    } finally {
                        writeLock.unlock();
                    }
                } finally {
                    readLock.lock();
                }
            }
        } finally {
            readLock.unlock();
        }

        return "/asset/" + hashToken + path;
    }
}
