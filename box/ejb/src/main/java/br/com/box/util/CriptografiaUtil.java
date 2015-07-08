package br.com.box.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public final class CriptografiaUtil {

    private CriptografiaUtil() {
    }

    public static String criptografarMD5(String valor) {
        return Hashing.md5().hashString(valor, Charsets.UTF_8).toString();
    }

}
