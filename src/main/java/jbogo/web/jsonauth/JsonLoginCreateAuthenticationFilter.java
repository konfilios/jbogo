package jbogo.web.jsonauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Override;import java.lang.RuntimeException;import java.lang.String;import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a login using JSON.
 */
public class JsonLoginCreateAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    {
        if (!request.getMethod().equals("PUT")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // JSON-Decode request body and retrieve LoginDto.
        LoginCreateCmd loginCreateCmd;
        try {
            loginCreateCmd = new ObjectMapper().readValue(request.getInputStream(), LoginCreateCmd.class);
        } catch (IOException e) {
//            e.printStackTrace();
            Logger.getLogger(JsonLoginCreateAuthenticationFilter.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
//            return null;
        }

        // The rest of the code is copied from the parent class
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
            loginCreateCmd.username, loginCreateCmd.password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    static public class LoginCreateCmd
    {
        public String username;

        public String password;
    }
}