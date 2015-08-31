/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.rest.endpoint;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.jboss.pnc.model.ProductRelease;
import org.jboss.pnc.model.ProductRelease.SupportLevel;
import org.jboss.pnc.rest.provider.ConflictedEntryException;
import org.jboss.pnc.rest.provider.ProductReleaseProvider;
import org.jboss.pnc.rest.restmodel.ProductReleaseRest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;

@Api(value = "/product-releases", description = "Product Release related information")
@Path("/product-releases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductReleaseEndpoint extends AbstractEndpoint<ProductRelease, ProductReleaseRest> {

    private ProductReleaseProvider productReleaseProvider;

    public ProductReleaseEndpoint() {
    }

    @Inject
    public ProductReleaseEndpoint(ProductReleaseProvider productReleaseProvider) {
        super(productReleaseProvider);
        this.productReleaseProvider = productReleaseProvider;
    }

    @ApiOperation(value = "Gets all Product Releases", responseContainer = "List", response = ProductReleaseRest.class)
    @GET
    public Response getAll(@ApiParam(value = "Page index") @QueryParam("pageIndex") @DefaultValue("0") int pageIndex,
            @ApiParam(value = "Pagination size") @DefaultValue("50") @QueryParam("pageSize") int pageSize,
            @ApiParam(value = "Sorting RSQL") @QueryParam("sort") String sortingRsql,
            @ApiParam(value = "RSQL query") @QueryParam("q") String rsql) {
        return super.getAll(pageIndex, pageSize, sortingRsql, rsql);
    }

    @ApiOperation(value = "Gets all Product Releases of the Specified Product Version",
            responseContainer = "List", response = ProductReleaseRest.class)
    @GET
    @Path("/product-versions/{versionId}")
    public Response getAllByProductVersionId(
            @ApiParam(value = "Page index") @QueryParam("pageIndex") @DefaultValue("0") int pageIndex,
            @ApiParam(value = "Pagination size") @DefaultValue("50") @QueryParam("pageSize") int pageSize,
            @ApiParam(value = "Sorting RSQL") @QueryParam("sort") String sortingRsql,
            @ApiParam(value = "RSQL query", required = false) @QueryParam("q") String rsql,
            @ApiParam(value = "Product Version id", required = true) @PathParam("versionId") Integer versionId) {
        return fromCollection(productReleaseProvider.getAllForProductVersion(pageIndex, pageSize, sortingRsql, rsql, versionId));
    }

    @ApiOperation(value = "Gets specific Product Release", response = ProductReleaseRest.class)
    @GET
    @Path("/{id}")
    public Response getSpecific(@ApiParam(value = "Product Release id", required = true) @PathParam("id") Integer id) {
        return super.getSpecific(id);
    }

    @ApiOperation(value = "Creates a new Product Release", response = ProductReleaseRest.class)
    @POST
    public Response createNew(
            @NotNull @Valid ProductReleaseRest productReleaseRest, @Context UriInfo uriInfo) throws ConflictedEntryException {
        return super.createNew(productReleaseRest, uriInfo);
    }

    @ApiOperation(value = "Updates an existing Product Release")
    @PUT
    @Path("/{id}")
    public Response update(@ApiParam(value = "Product Release id", required = true) @PathParam("id") Integer id,
            @NotNull @Valid ProductReleaseRest productReleaseRest, @Context UriInfo uriInfo) throws ConflictedEntryException {
        return super.update(id, productReleaseRest);
    }

    @ApiOperation(value = "Gets all Product Releases Support Level",
            responseContainer = "List", response = SupportLevel.class)
    @GET
    @Path("/support-level")
    public Response getAllSupportLevel() {
        return fromCollection(Arrays.asList(SupportLevel.values()));
    }

}
