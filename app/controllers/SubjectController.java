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
import security.NoUserDeadboltHandler;
import views.html.accessOk;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class SubjectController extends Controller
{
    public static Result index()
    {
        return ok(accessOk.render());
    }

    @SubjectPresent
    public static Result subjectPresent()
    {
        return ok(accessOk.render());
    }

    @SubjectPresent(handlerKey = "foo")
    public static Result subjectPresent_namedHandler()
    {
        return ok(accessOk.render());
    }

    @SubjectPresent(handler = NoUserDeadboltHandler.class)
    public static Result subjectPresent_notLoggedIn()
    {
        return ok(accessOk.render());
    }

    @SubjectNotPresent(handler = NoUserDeadboltHandler.class)
    public static Result subjectNotPresent()
    {
        return ok(accessOk.render());
    }

    @SubjectNotPresent(handlerKey = "foo")
    public static Result subjectNotPresent_namedHandler()
    {
        return ok(accessOk.render());
    }

    @SubjectNotPresent
    public static Result subjectNotPresent_loggedIn()
    {
        return ok(accessOk.render());
    }
}
