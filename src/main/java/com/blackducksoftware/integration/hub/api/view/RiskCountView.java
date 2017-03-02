/**
 * Hub Common
 *
 * Copyright (C) 2017 Black Duck Software, Inc.
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
/*
 *
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 2.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.blackducksoftware.integration.hub.api.view;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.blackducksoftware.integration.hub.model.type.RiskCountCountEnum;
import com.google.gson.annotations.SerializedName;

/**
 * RiskCountView
 */
public class RiskCountView {
    @SerializedName("count")
    private Integer count = null;

    @SerializedName("countType")
    private RiskCountCountEnum countType = null;

    public RiskCountView count(final Integer count) {
        this.count = count;
        return this;
    }

    /**
     * The level of risk within the severity threshold
     *
     * @return count
     **/
    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public RiskCountView countType(final RiskCountCountEnum countType) {
        this.countType = countType;
        return this;
    }

    /**
     * The level of severity being quantified
     *
     * @return countType
     **/
    public RiskCountCountEnum getCountType() {
        return countType;
    }

    public void setCountType(final RiskCountCountEnum countType) {
        this.countType = countType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
