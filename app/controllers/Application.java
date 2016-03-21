package controllers;

import models.AuthorisedUser;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Application extends Controller
{
    public CompletionStage<Result> index()
    {
        return CompletableFuture.supplyAsync(() -> AuthorisedUser.findByUserName("steve"))
                                .thenApply(user -> ok(index.render(user)));
    }
}