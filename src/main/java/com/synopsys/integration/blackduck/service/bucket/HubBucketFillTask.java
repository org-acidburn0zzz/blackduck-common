/**
 * hub-common
 *
 * Copyright (C) 2018 Black Duck Software, Inc.
 * http://www.blackducksoftware.com/
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
package com.synopsys.integration.blackduck.service.bucket;

import com.synopsys.integration.blackduck.api.UriSingleResponse;
import com.synopsys.integration.blackduck.api.core.BlackDuckResponse;
import com.synopsys.integration.blackduck.service.HubService;

public class HubBucketFillTask implements Runnable {
    private final HubService hubService;
    private final HubBucket hubBucket;
    private final UriSingleResponse<? extends BlackDuckResponse> uriSingleResponse;

    public HubBucketFillTask(final HubService hubService, final HubBucket hubBucket, final UriSingleResponse<? extends BlackDuckResponse> uriSingleResponse) {
        this.hubService = hubService;
        this.hubBucket = hubBucket;
        this.uriSingleResponse = uriSingleResponse;
    }

    @Override
    public void run() {
        if (!hubBucket.contains(uriSingleResponse.uri)) {
            try {
                final BlackDuckResponse blackDuckResponse = hubService.getResponse(uriSingleResponse);
                hubBucket.addValid(uriSingleResponse.uri, blackDuckResponse);
            } catch (final Exception e) {
                // it is up to the consumer of the bucket to log or handle any/all Exceptions
                hubBucket.addError(uriSingleResponse.uri, e);
            }
        }
    }

}
