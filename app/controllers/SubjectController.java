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
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import security.NoUserDeadboltHandler;
import views.html.accessOk;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class SubjectController extends Controller
{
    public static F.Promise<Result> index()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @SubjectPresent
    public static F.Promise<Result> subjectPresent()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @SubjectPresent(handlerKey = "noUserHandler")
    public static F.Promise<Result> subjectPresent_notLoggedIn()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @SubjectNotPresent(handlerKey = "noUserHandler")
    public static F.Promise<Result> subjectNotPresent()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @SubjectNotPresent
    public static F.Promise<Result> subjectNotPresent_loggedIn()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }
}
