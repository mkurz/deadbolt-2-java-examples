package controllers;

import models.AuthorisedUser;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

public class Application extends Controller
{
    private final HttpExecutionContext ec;

    @Inject
    public Application(final HttpExecutionContext ec)
    {
        this.ec = ec;
    }

    public CompletionStage<Result> index()
    {
        return CompletableFuture.supplyAsync(() -> AuthorisedUser.findByUserName("steve"))
                                .thenApplyAsync(user -> ok(index.render(user)),
                                                ec.current());
    }
}
