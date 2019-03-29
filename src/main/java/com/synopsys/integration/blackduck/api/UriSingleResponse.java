/**
 * blackduck-common
 *
 * Copyright (c) 2019 Synopsys, Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.synopsys.integration.blackduck.api;

import com.synopsys.integration.blackduck.api.core.BlackDuckResponse;
import com.synopsys.integration.blackduck.api.core.LinkResponse;

public class UriSingleResponse<T extends BlackDuckResponse> extends LinkResponse {
    private final String uri;
    private final Class<T> responseClass;

    public UriSingleResponse(final String uri, final Class<T> responseClass) {
        this.uri = uri;
        this.responseClass = responseClass;
    }

    @Override
    public String toString() {
        return uri;
    }

    public String getUri() {
        return uri;
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }

}
