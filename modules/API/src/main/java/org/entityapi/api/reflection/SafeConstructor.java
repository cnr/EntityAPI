/*
 * This file is part of EntityAPI.
 *
 * EntityAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EntityAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EntityAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.entityapi.api.reflection;

import org.entityapi.api.plugin.EntityAPI;

import java.lang.reflect.Constructor;

public class SafeConstructor<T> {

    private Constructor<T> constructor;
    private Class[] params;

    public SafeConstructor(Constructor constructor) {
        setConstructor(constructor);
    }

    public SafeConstructor(Class<?> coreClass, Class<?>... params) {
        try {
            Constructor constructor = coreClass.getConstructor(params);
            setConstructor(constructor);
        } catch (NoSuchMethodException e) {
            EntityAPI.LOGGER_REFLECTION.warning("No such constructor!");
        }
    }

    protected void setConstructor(Constructor constructor) {
        if (constructor == null) {
            throw new UnsupportedOperationException("Cannot create a new constructor!");
        }
        this.constructor = constructor;
        this.params = constructor.getParameterTypes();
    }

    public Constructor getConstructor() {
        return this.constructor;
    }

    public T newInstance(Object... params) {
        try {
            return (T) this.getConstructor().newInstance(params);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
