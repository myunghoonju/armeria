/*
 * Copyright 2025 LY Corporation
 *
 * LY Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.internal.server.annotation;

import static java.util.Objects.requireNonNull;

import java.lang.annotation.Annotation;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import com.linecorp.armeria.common.annotation.Nullable;
import com.linecorp.armeria.common.logging.BeanFieldInfo;

final class JacksonBeanFieldInfo implements BeanFieldInfo {

    private final BeanDescription classBean;
    private final BeanProperty property;

    JacksonBeanFieldInfo(BeanDescription classBean, BeanProperty property) {
        this.classBean = requireNonNull(classBean, "classBean");
        this.property = requireNonNull(property, "property");
    }

    @Override
    public String name() {
        return property.getName();
    }

    @Nullable
    @Override
    public <T extends Annotation> T getFieldAnnotation(Class<T> annotationClass) {
        return property.getAnnotation(annotationClass);
    }

    @Nullable
    @Override
    public <T extends Annotation> T getClassAnnotation(Class<T> annotationClass) {
        return classBean.getClassAnnotations().get(annotationClass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JacksonBeanFieldInfo that = (JacksonBeanFieldInfo) o;
        return Objects.equal(classBean, that.classBean) &&
               Objects.equal(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(classBean, property);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("classBean", classBean)
                          .add("property", property)
                          .toString();
    }
}
