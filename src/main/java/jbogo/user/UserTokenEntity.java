package jbogo.modules.user.entities;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 *
 */
@MappedSuperclass
abstract public class UserTokenEntity<USER>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private USER user;

    @NotNull
    private String token;

    @NotNull
    private DateTime expireUdatetime;

    public UserTokenEntity()
    {

    }

    public UserTokenEntity(USER user, int expiresInMinutes)
    {
        this.user = user;
        refreshToken(expiresInMinutes);
    }

    public void refreshToken(int expiresInMinutes)
    {
        this.token = UUID.randomUUID().toString();
        this.expireUdatetime = new DateTime().plus(expiresInMinutes * 60 * 1000);
    }

    public USER getUser()
    {
        return user;
    }

    public String getToken()
    {
        return token;
    }

    public boolean hasExpired()
    {
        return this.expireUdatetime.isBeforeNow();
    }
}
