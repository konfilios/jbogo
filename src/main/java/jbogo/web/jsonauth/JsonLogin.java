package jbogo.web.jsonauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

/**
 * .
 */
public class JsonLogin
{
    static public HttpSecurity configure(HttpSecurity http, String loginUri)
        throws Exception
    {
        JsonLoginDtoProducer loginDtoProducer = (req, resp, auth) -> auth.getPrincipal();
        // @formatter:off

        return http
            .exceptionHandling()
                .authenticationEntryPoint((req, resp, e)->resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()))
//                .accessDeniedHandler((req, resp, e)->resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: " + e.getMessage()))
                .and()
            .apply(new JsonLoginConfigurer<>())
                .loginPage(loginUri)
                .failureHandler((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()))
                .successHandler((req, resp, auth) -> {
                    resp.getWriter().write(new ObjectMapper().writeValueAsString(loginDtoProducer.produce(req, resp, auth)));
                    resp.addHeader("Content-Type", req.getHeader("Content-Type"));
                })
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(loginUri, "DELETE"))
                .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpServletResponse.SC_NO_CONTENT))
                .and()
            .addFilterBefore(new JsonLoginGetAuthenticationFilter(new AntPathRequestMatcher(loginUri, "GET"), loginDtoProducer), LogoutFilter.class)
            ;
        // @formatter:on
    }
}
