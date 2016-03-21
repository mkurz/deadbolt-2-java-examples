/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package controllers;

import actions.CustomRestrict;
import actions.RoleGroup;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import play.mvc.Controller;
import play.mvc.Result;
import security.MyRoles;
import views.html.accessOk;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Restrict({@Group("foo"),
        @Group("bar")})
public class RestrictController extends Controller
{
    public CompletionStage<Result> index()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @Restrict({@Group({"foo", "bar"})})
    public CompletionStage<Result> restrictOne()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @Restrict({@Group({"hurdy", "gurdy"}), @Group("foo")})
    public CompletionStage<Result> restrictTwo()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @Restrict({@Group("foo"), @Group("!bar")})
    public CompletionStage<Result> restrictThree()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @Restrict(@Group({"hurdy", "foo"}))
    public CompletionStage<Result> restrictFour()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @Restrict(@Group({"foo", "!bar"}))
    public CompletionStage<Result> restrictFive()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }


    @CustomRestrict(value = {@RoleGroup({MyRoles.foo, MyRoles.bar}),
                             @RoleGroup(MyRoles.hurdy)},
                    config = @Restrict({}))
    public CompletionStage<Result> customRestrictOne()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @CustomRestrict(value = {@RoleGroup({MyRoles.hurdy, MyRoles.foo}),
                             @RoleGroup({MyRoles.hurdy, MyRoles.bar})},
                    config = @Restrict({}))
    public CompletionStage<Result> customRestrictTwo()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }
}
