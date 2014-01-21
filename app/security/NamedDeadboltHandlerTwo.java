package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.AuthorisedUser;
import play.mvc.Http;
import play.mvc.Result;
import views.html.accessFailed;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class NamedDeadboltHandlerTwo extends AbstractDeadboltHandler
{
    public Result beforeAuthCheck(Http.Context context)
    {
        return null;
    }

    public Subject getSubject(Http.Context context)
    {
        // in a real application, the user name would probably be in the session following a login process
        return AuthorisedUser.findByUserName("steve");
    }

    public DynamicResourceHandler getDynamicResourceHandler(Http.Context context)
    {
        return new MyDynamicResourceHandler();
    }

    @Override
    public Result onAuthFailure(Http.Context context,
                                String content)
    {
        // you can return any result from here - forbidden, etc
        return ok(accessFailed.render());
    }
}
