package jbogo.web.jsonauth;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import java.lang.Object;

/**
 *
 */
public interface JsonLoginSuccessResponseBodyProducer
{
    Object produce(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
