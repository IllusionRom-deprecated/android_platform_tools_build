/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.build.gradle.internal.dsl;

import com.android.annotations.NonNull;
import com.android.builder.NdkConfig;
import com.google.common.collect.Sets;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 */
public class NdkConfigDsl implements NdkConfig, Serializable {
    private static final long serialVersionUID = 1L;

    private String moduleName;
    private String cFlags;
    private String ldLibs;
    private Set<String> abiFilters;

    public NdkConfigDsl() {
    }

    public NdkConfigDsl(@NonNull NdkConfigDsl ndkConfig) {
        moduleName = ndkConfig.moduleName;
        cFlags = ndkConfig.cFlags;
        ldLibs = ndkConfig.ldLibs;
        setSrcDirs(ndkConfig.abiFilters);
    }

    @Override
    @Input @Optional
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    @Input @Optional
    public String getcFlags() {
        return cFlags;
    }

    public void setcFlags(String cFlags) {
        this.cFlags = cFlags;
    }

    @Override
    @Input @Optional
    public String getLdLibs() {
        return ldLibs;
    }

    public void setLdLibs(String ldLibs) {
        this.ldLibs = ldLibs;
    }

    @Override
    @Input @Optional
    public Set<String> getAbiFilters() {
        return abiFilters;
    }

    @NonNull
    public NdkConfigDsl abiFilter(String filter) {
        if (abiFilters == null) {
            abiFilters = Sets.newHashSetWithExpectedSize(2);
        }
        abiFilters.add(filter);
        return this;
    }

    @NonNull
    public NdkConfigDsl abiFilters(String... filters) {
        if (abiFilters == null) {
            abiFilters = Sets.newHashSetWithExpectedSize(2);
        }
        Collections.addAll(abiFilters, filters);
        return this;
    }

    @NonNull
    public NdkConfigDsl setSrcDirs(Iterable<String> filters) {
        if (filters != null) {
            if (abiFilters == null) {
                abiFilters = Sets.newHashSetWithExpectedSize(2);
            } else {
                abiFilters.clear();
            }
            for (String filter : filters) {
                abiFilters.add(filter);
            }
        } else {
            abiFilters = null;
        }
        return this;
    }
}
