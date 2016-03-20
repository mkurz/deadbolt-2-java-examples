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

import be.objectify.deadbolt.java.DeadboltAnalyzer;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Permission;
import play.Logger;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDynamicResourceHandler implements DynamicResourceHandler
{
    private static final Map<String, Optional<DynamicResourceHandler>> HANDLERS = new HashMap<>();

    private static final DynamicResourceHandler DENY = new DynamicResourceHandler()
    {
        @Override
        public CompletionStage<Boolean> isAllowed(String name, Optional<String> meta, DeadboltHandler deadboltHandler, Http.Context context)
        {
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }

        @Override
        public CompletionStage<Boolean> checkPermission(String value, Optional<String> meta, DeadboltHandler deadboltHandler, Http.Context context)
        {
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
    };

    static
    {
        HANDLERS.put("pureLuck",
                     Optional.of(new AbstractDynamicResourceHandler()
                     {
                         public CompletionStage<Boolean> isAllowed(final String name,
                                                                   final Optional<String> meta,
                                                                   final DeadboltHandler deadboltHandler,
                                                                   final Http.Context context)
                         {
                             return CompletableFuture.supplyAsync(() -> System.currentTimeMillis() % 2 == 0);
                         }
                     }));
        HANDLERS.put("viewProfile",
                     Optional.of(new AbstractDynamicResourceHandler()
                     {
                         public CompletionStage<Boolean> isAllowed(final String name,
                                                             final Optional<String> meta,
                                                             final DeadboltHandler deadboltHandler,
                                                             final Http.Context context)
                         {
                             return deadboltHandler.getSubject(context)
                                                   .thenApplyAsync(subjectOption -> {
                                                       final boolean[] allowed = {false};
                                                       if (new DeadboltAnalyzer().hasRole(subjectOption, "admin"))
                                                       {
                                                           allowed[0] = true;
                                                       }
                                                       else
                                                       {
                                                           subjectOption.ifPresent(subject -> {
                                                               // for the purpose of this example, we assume a call to view profile is probably
                                                               // a get request, so the query string is used to provide info
                                                               Map<String, String[]> queryStrings = context.request().queryString();
                                                               String[] requestedNames = queryStrings.get("userName");
                                                               allowed[0] = requestedNames != null
                                                                       && requestedNames.length == 1
                                                                       && requestedNames[0].equals(subject.getIdentifier());
                                                           });
                                                       }

                                                       return allowed[0];
                                                   });
                         }
                     }));
    }

    public CompletionStage<Boolean> isAllowed(final String name,
                                              final Optional<String> meta,
                                              final DeadboltHandler deadboltHandler,
                                              final Http.Context context)
    {
        return HANDLERS.get(name)
                       .orElseGet(() -> {
                           Logger.error("No handler available for " + name);
                           return DENY;
                       })
                       .isAllowed(name,
                                  meta,
                                  deadboltHandler,
                                  context);
    }

    public CompletionStage<Boolean> checkPermission(final String permissionValue,
                                                    final Optional<String> meta,
                                                    final DeadboltHandler deadboltHandler,
                                                    final Http.Context ctx)
    {
        return deadboltHandler.getSubject(ctx)
                              .thenApplyAsync(subjectOption -> {
                                  final boolean[] permissionOk = {false};
                                  subjectOption.ifPresent(subject -> {
                                      List<? extends Permission> permissions = subject.getPermissions();
                                      for (Iterator<? extends Permission> iterator = permissions.iterator(); !permissionOk[0] && iterator.hasNext(); )
                                      {
                                          Permission permission = iterator.next();
                                          permissionOk[0] = permission.getValue().contains(permissionValue);
                                      }
                                  });

                                  return permissionOk[0];
                              });
    }
}
