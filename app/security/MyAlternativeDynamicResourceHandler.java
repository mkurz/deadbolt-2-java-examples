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

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.mvc.Http;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyAlternativeDynamicResourceHandler implements DynamicResourceHandler
{
    public CompletionStage<Boolean> isAllowed(final String name,
                                              final Optional<String> meta,
                                              final DeadboltHandler deadboltHandler,
                                              final Http.Context context)
    {
        // look something up in an LDAP directory, etc, and the answer isn't good for the user
        return CompletableFuture.completedFuture(false);
    }

    public CompletionStage<Boolean> checkPermission(final String permissionValue,
                                              final Optional<String> meta,
                                              final DeadboltHandler deadboltHandler,
                                              final Http.Context ctx)
    {
        // Computer says no
        return CompletableFuture.completedFuture(false);
    }
}
