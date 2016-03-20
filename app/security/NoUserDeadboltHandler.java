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
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.ExecutionContext;
import views.html.accessFailed;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class NoUserDeadboltHandler extends AbstractDeadboltHandler
{
    public NoUserDeadboltHandler(ExecutionContextProvider ecProvider)
    {
        super(ecProvider);
    }

    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.Context context)
    {
        // if the API calls for an Optional, don't return a null!
        // THIS IS A PURPOSEFUL ERROR - DO NOT REPEAT IN YOUR CODE!
        return null;
    }

    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.Context context)
    {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    public CompletionStage<Result> onAuthFailure(final Http.Context context,
                                                 final Optional<String> content)
    {
        final ExecutionContext executionContext = executionContextProvider.get();
        return CompletableFuture.supplyAsync(accessFailed::render,
                                             (Executor) executionContext)
                                .thenApplyAsync(Results::ok,
                                                (Executor) executionContext);
    }

    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context)
    {
        final ExecutionContext executionContext = executionContextProvider.get();
        return CompletableFuture.supplyAsync(() -> Optional.of(new MyAlternativeDynamicResourceHandler()),
                                             (Executor) executionContext);
    }
}
