package util;

import exception.UnauthorizedException;
import exception.errorcode.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    @Value("${json.web.token.secret.key}")
    String secret;

    public String genJsonWebToken(Long id){
        Map<String, Object> headers = new HashMap<String, Object>(); // header
        headers.put("typ", "JWT");
        headers.put("alg","HS256");
        Map<String, Object> payloads = new HashMap<String, Object>(); //payload
        payloads.put("id", id );
        Calendar calendar = Calendar.getInstance(); // singleton object java calendar
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 24); // access token expire 24h later
        Date exp = calendar.getTime();

        return Jwts.builder().setHeader(headers).setClaims(payloads).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
    }

 
    public boolean isValid(String token) throws Exception{
        if ( token == null) {
            throw new UnauthorizedException(ErrorCode.INVALID_JWT);
        }
        else if ( !token.startsWith("Bearer ") ){
            throw new UnauthorizedException(ErrorCode.INVALID_JWT);
        }
        token = token.substring(7);
        try {
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e1){
            throw new UnauthorizedException(ErrorCode.INVALID_JWT);
        }
        catch(Throwable e2){
            throw new UnauthorizedException(ErrorCode.INVALID_JWT);
        }
        return true;
    }

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.substring(7)).getBody();
        return Long.parseLong(String.valueOf(claims.get("id")));
    }

    public String getTokenFromServlet(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}