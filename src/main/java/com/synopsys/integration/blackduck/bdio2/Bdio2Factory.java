/**
 * blackduck-common
 *
 * Copyright (c) 2020 Synopsys, Inc.
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
package com.synopsys.integration.blackduck.bdio2;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.blackducksoftware.bdio2.BdioMetadata;
import com.blackducksoftware.bdio2.BdioObject;
import com.blackducksoftware.bdio2.model.Component;
import com.blackducksoftware.bdio2.model.Project;
import com.blackducksoftware.common.value.Product;
import com.blackducksoftware.common.value.ProductList;
import com.synopsys.integration.bdio.graph.DependencyGraph;
import com.synopsys.integration.bdio.model.dependency.Dependency;
import com.synopsys.integration.bdio.model.externalid.ExternalId;

public class Bdio2Factory {

    public Bdio2Document createBdio2Document(final BdioMetadata bdioMetadata, final Project project, final DependencyGraph dependencyGraph) {
        final List<Component> components = createAndLinkComponents(dependencyGraph, project);
        return new Bdio2Document(bdioMetadata, project, components);
    }

    public BdioMetadata createBdioMetadata(final String codeLocationName, final ZonedDateTime creationDateTime, final ProductList.Builder productListBuilder) {
        return new BdioMetadata()
                   .id(LegacyUtilitiesClone.toNameUri(codeLocationName))
                   .name(codeLocationName)
                   .creationDateTime(creationDateTime)
                   .publisher(
                       productListBuilder
                           .addProduct(Product.java())
                           .addProduct(Product.os())
                           .build()
                   );
    }

    public Project createProject(final ExternalId projectExternalId, final String projectName, final String projectVersionName) {
        return new Project(projectExternalId.createBdioId().toString())
                   .identifier(projectExternalId.createExternalId())
                   .name(projectName)
                   .version(projectVersionName);
    }

    public List<Component> createAndLinkComponents(final DependencyGraph dependencyGraph, final Project project) {
        return createAndLinkComponentsFromGraph(dependencyGraph, project::dependency, dependencyGraph.getRootDependencies(), new HashMap<>());
    }

    private List<Component> createAndLinkComponentsFromGraph(final DependencyGraph dependencyGraph, final DependencyFunction dependencyFunction, final Set<Dependency> dependencies, final Map<ExternalId, Component> existingComponents) {
        final List<Component> addedComponents = new ArrayList<>();

        for (final Dependency dependency : dependencies) {
            final Component component = componentFromDependency(dependency);
            dependencyFunction.dependency(new com.blackducksoftware.bdio2.model.Dependency().dependsOn(component));

            if (!existingComponents.containsKey(dependency.getExternalId())) {
                addedComponents.add(component);

                existingComponents.put(dependency.getExternalId(), component);
                final List<Component> children = createAndLinkComponentsFromGraph(dependencyGraph, component::dependency, dependencyGraph.getChildrenForParent(dependency), existingComponents);
                addedComponents.addAll(children);
            }
        }

        return addedComponents;
    }

    private Component componentFromDependency(final Dependency dependency) {
        return new Component(dependency.getExternalId().createBdioId().toString())
                   .name(dependency.getName())
                   .version(dependency.getVersion())
                   .identifier(dependency.getExternalId().createExternalId())
                   .namespace(dependency.getExternalId().getForge().getName());
    }

    @FunctionalInterface
    private interface DependencyFunction {
        BdioObject dependency(@Nullable com.blackducksoftware.bdio2.model.Dependency dependency);
    }
}
