package controllers;

import models.AuthorisedUser;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;

public class Application extends Controller
{
    public static F.Promise<Result> index()
    {
        return F.Promise.promise(() -> AuthorisedUser.findByUserName("steve"))
                .map(user -> ok(index.render(user)));
    }
}