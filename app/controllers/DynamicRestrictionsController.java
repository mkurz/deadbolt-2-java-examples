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

import be.objectify.deadbolt.java.actions.Dynamic;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.accessOk;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@SubjectPresent
public class DynamicRestrictionsController extends Controller
{
    public static F.Promise<Result> index()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @Dynamic("pureLuck")
    public static F.Promise<Result> pureLuck()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @Dynamic(value = "pureLuck", handlerKey = "altHandler")
    public static F.Promise<Result> noWayJose()
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }

    @Dynamic(value = "viewProfile")
    public static F.Promise<Result> viewProfile(final String userName)
    {
        return F.Promise.promise(() -> ok(accessOk.render()));
    }
}
