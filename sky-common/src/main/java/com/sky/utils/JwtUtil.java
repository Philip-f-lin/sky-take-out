package com.sky.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成 jwt
     * 使用 Hs256 演算法, 私匙使用固定秘鑰
     *
     * @param secretKey jwt 秘鑰
     * @param ttlMillis jwt 過期時間(毫秒)
     * @param claims    設定的訊息
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定簽名的時候使用的簽章演算法，也就是 header 那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 產生 JWT 的時間
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 設定 jwt 的 body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有聲明，一定要先設定這個自己創建的私有的聲明，這個是給 builder 的 claim 賦值，一旦寫在標準的聲明賦值之後，就是覆蓋了那些標準的聲明的
                .setClaims(claims)
                // 設定簽名使用的簽名演算法和簽名使用的秘鑰
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 設定過期時間
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token解密
     *
     * @param secretKey jwt 秘鑰 此秘鑰一定要保留好在服務端, 不能暴露出去, 否則 sign 就可以被偽造, 如果對接多個客戶端建議改造成多個
     * @param token     加密後的 token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 得到 DefaultJwtParser
        Claims claims = Jwts.parser()
                // 設定簽名的秘鑰
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 設定需要解析的 jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
