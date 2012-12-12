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
package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class AuthorisedUser extends Model implements Subject
{
    @Id
    public Long id;

    public String userName;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permissions;

    public static final Finder<Long, AuthorisedUser> find = new Finder<Long, AuthorisedUser>(Long.class,
                                                                                             AuthorisedUser.class);

    public List<? extends Role> getRoles()
    {
        return roles;
    }
    
    public List<? extends Permission> getPermissions()
    {
        return permissions;
    }
    
    public static AuthorisedUser findByUserName(String userName)
    {
        return find.where()
                   .eq("userName",
                       userName)
                   .findUnique();
    }
}
