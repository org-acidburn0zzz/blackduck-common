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

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.synopsys.integration.blackduck.api.UriSingleResponse;
import com.synopsys.integration.blackduck.api.core.BlackDuckResponse;

public class HubBucket {
    private final Map<String, HubBucketItem<BlackDuckResponse>> bucket = new ConcurrentHashMap<>();

    public boolean contains(final String uri) {
        return bucket.containsKey(uri);
    }

    public Set<String> getAvailableUris() {
        return bucket.keySet();
    }

    public HubBucketItem<BlackDuckResponse> get(final String uri) {
        return bucket.get(uri);
    }

    public <T extends BlackDuckResponse> T get(final String uri, final Class<T> responseClass) {
        final UriSingleResponse<T> uriSingleResponse = new UriSingleResponse<>(uri, responseClass);
        return get(uriSingleResponse);
    }

    public <T extends BlackDuckResponse> T get(final UriSingleResponse<T> uriSingleResponse) {
        final String uri = uriSingleResponse.uri;
        if (contains(uri)) {
            final HubBucketItem<BlackDuckResponse> bucketItem = get(uri);
            if (bucketItem.hasValidResponse()) {
                final Optional<BlackDuckResponse> optionalBlackDuckResponse = bucketItem.getBlackDuckResponse();
                if (optionalBlackDuckResponse.isPresent()) {
                    final BlackDuckResponse blackDuckResponse = optionalBlackDuckResponse.get();
                    if (blackDuckResponse.getClass().equals(uriSingleResponse.responseClass)) {
                        return getResponseFromBucket(bucketItem);
                    }
                }
            }
        }
        return null;
    }

    private <T extends BlackDuckResponse> T getResponseFromBucket(final HubBucketItem<BlackDuckResponse> bucketItem) {
        // the mapping of uri -> response type are assumed to be correct, so returning T is possible
        return (T) bucketItem.getBlackDuckResponse().orElse(null);
    }

    public Optional<BlackDuckResponse> getResponse(final String uri) {
        return bucket.get(uri).getBlackDuckResponse();
    }

    public Optional<Exception> getError(final String uri) {
        return bucket.get(uri).getE();
    }

    public void addValid(final String uri, final BlackDuckResponse blackDuckResponse) {
        bucket.put(uri, new HubBucketItem<>(uri, blackDuckResponse));
    }

    public void addError(final String uri, final Exception e) {
        bucket.put(uri, new HubBucketItem<>(uri, e));
    }

    public HubBucketItem<BlackDuckResponse> remove(final String uri) {
        return bucket.remove(uri);
    }

}
