package jbogo.web.jsonauth;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import java.lang.Object;

/**
 * Produces a response body for GET /login.
 */
public interface JsonLoginDtoProducer
{
    Object produce(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
