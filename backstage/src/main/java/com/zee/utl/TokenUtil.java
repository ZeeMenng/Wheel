package com.zee.utl;

import java.security.Key;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.zee.set.enumer.OperResult;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	/**
	 * 生成JWT，Json Web Token
	 * 使用签名算法和生成的signingKey生成最终的Token
	 * 
	 * iss： 该JWT的签发者 
	 * sub：该JWT所面向的用户 
	 * aud：接受该JWT的一方
	 * exp：什么时候过期，一个时间戳
	 * iat：什么时候签发的，也是一个时间戳 
	 * 
	 * @param claims,有效载荷
	 * @return Token
	 */
	public static String createAccessToken(Claims claims, String secret) {

		// ObjectMapper objectMapper = new ObjectMapper();
		// @SuppressWarnings("unchecked")
		// Map<String, Object> props = objectMapper.convertValue(user,
		// Map.class);
		// claims.putAll(props);

		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(claims);

		jwtBuilder.setExpiration(DateUtils.addSecond(DateUtils.getCurrentTime(), CustomSymbolic.JWT_ACCESS_DEAD_TIME));

		jwtBuilder.setIssuer(CustomSymbolic.JWT_ISSUER);
		jwtBuilder.setIssuedAt(DateUtils.getCurrentTime());
		jwtBuilder.setId(Tools.getUUID());
		jwtBuilder.signWith(SignatureAlgorithm.HS256, getKeyInstance(secret));
		return jwtBuilder.compact();
	}

	public static String createRefreshToken(Claims claims, String secret) {

		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(claims);
		jwtBuilder.setExpiration(DateUtils.addHour(DateUtils.getCurrentTime(), CustomSymbolic.JWT_REFRESH_DEAD_TIME));
		jwtBuilder.setIssuer(CustomSymbolic.JWT_ISSUER);
		jwtBuilder.setIssuedAt(DateUtils.getCurrentTime());
		jwtBuilder.setId(Tools.getUUID());
		jwtBuilder.signWith(SignatureAlgorithm.HS256, getKeyInstance(secret));
		return jwtBuilder.compact();

	}

	// 解析Token，同时也能验证Token，当验证失败返回null
	public static Jws<Claims> parserJavaWebToken(String jwt, String secret) {
		try {
			Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance(CustomSymbolic.JWT_SECRET)).parseClaimsJws(jwt);
			return jwtClaims;
		} catch (ExpiredJwtException e) {
			throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(),"Token信息已过期，请重新获取授权！" + e.getLocalizedMessage());
		} catch (Exception e) {
			throw new GlobalException("Token信息不正确！" + e.getLocalizedMessage());
		}
	}

	// 随机生成5位动态密钥
	public static String createSecret() {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 使用HS256算法和密钥Secret生成签名
	 * @return signKey
	 */
	private static Key getKeyInstance(String secret) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

		return signingKey;
	}

}
