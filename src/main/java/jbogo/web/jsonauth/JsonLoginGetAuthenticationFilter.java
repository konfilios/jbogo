package jbogo.web.jsonauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responds to GET /login.
 *
 * If the user is authenticated, then a dto produced by loginDtoProducer is returned as response body.
 * If not, then a 404 Not Found is returned.
 */
public class JsonLoginGetAuthenticationFilter extends GenericFilterBean
{
    final private RequestMatcher requestMatcher;

    final private JsonLoginDtoProducer loginDtoProducer;

    public JsonLoginGetAuthenticationFilter(RequestMatcher requestMatcher, JsonLoginDtoProducer loginDtoProducer)
    {
        this.requestMatcher = requestMatcher;
        this.loginDtoProducer = loginDtoProducer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (requestMatcher.matches(req)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated()) {
                resp.getWriter().write(
                    new ObjectMapper().writeValueAsString(loginDtoProducer.produce(req, resp, auth)));
                resp.addHeader("Content-Type", req.getHeader("Content-Type"));
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "You are not logged in.");
            }

            return;
        }

        filterChain.doFilter(req, resp);
    }
}
