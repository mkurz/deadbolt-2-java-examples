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
package security;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.ExecutionContextProvider;
import be.objectify.deadbolt.java.models.Subject;
import models.AuthorisedUser;
import play.mvc.Http;
import play.mvc.Result;
import views.html.accessFailed;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler
{
    public MyDeadboltHandler(ExecutionContextProvider ecProvider)
    {
        super(ecProvider);
    }

    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.Context context)
    {
        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return CompletableFuture.completedFuture(Optional.empty());
    }

    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.Context context)
    {
        // in a real application, the user name would probably be in the session following a login process
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(AuthorisedUser.findByUserName("steve")),
                                             (Executor) executionContextProvider.get());
    }

    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.Context context)
    {
        return CompletableFuture.completedFuture(Optional.of(new MyDynamicResourceHandler()));
    }

    @Override
    public CompletionStage<Result> onAuthFailure(final Http.Context context,
                                                 final Optional<String> content)
    {
        // you can return any result from here - forbidden, etc
        return CompletableFuture.completedFuture(ok(accessFailed.render()));
    }
}
