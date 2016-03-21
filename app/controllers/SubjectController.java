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

import be.objectify.deadbolt.java.actions.SubjectNotPresent;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.accessOk;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class SubjectController extends Controller
{
    public CompletionStage<Result> index()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @SubjectPresent
    public CompletionStage<Result> subjectPresent()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @SubjectPresent(handlerKey = "noUserHandler")
    public CompletionStage<Result> subjectPresent_notLoggedIn()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @SubjectNotPresent(handlerKey = "noUserHandler")
    public CompletionStage<Result> subjectNotPresent()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }

    @SubjectNotPresent
    public CompletionStage<Result> subjectNotPresent_loggedIn()
    {
        return CompletableFuture.completedFuture(ok(accessOk.render()));
    }
}
