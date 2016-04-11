package controllers;

import models.AuthorisedUser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import views.html.index;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Application extends Controller
{
    public CompletionStage<Result> index()
    {
        final Http.Context current = Http.Context.current();
        return CompletableFuture.supplyAsync(() -> AuthorisedUser.findByUserName("steve"))
                                .thenApply(user -> {
                                    // filthy hack to get around the missing context issue
                                    // todo: steve: better: add a context parameter to the template constraints
                                    Http.Context.current.set(current);
                                    return ok(index.render(user));
                                });
    }
}
