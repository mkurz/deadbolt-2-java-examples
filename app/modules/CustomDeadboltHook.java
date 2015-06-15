package modules;

import be.objectify.deadbolt.java.TemplateFailureListener;
import be.objectify.deadbolt.java.cache.HandlerCache;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;
import security.MyCustomTemplateFailureListener;
import security.MyHandlerCache;

import javax.inject.Singleton;

/**
 *  Creates a binding for a custom template failure listener.
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
public class CustomDeadboltHook extends Module
{
    @Override
    public Seq<Binding<?>> bindings(final Environment environment,
                                    final Configuration configuration)
    {
        return seq(bind(TemplateFailureListener.class).to(MyCustomTemplateFailureListener.class).in(Singleton.class),
                   bind(HandlerCache.class).to(MyHandlerCache.class).in(Singleton.class));
    }
}
