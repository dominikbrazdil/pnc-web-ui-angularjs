/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2019 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.rest.api.endpoints;

import static org.jboss.pnc.rest.configuration.SwaggerConstants.INVALID_CODE;
import static org.jboss.pnc.rest.configuration.SwaggerConstants.INVALID_DESCRIPTION;
import static org.jboss.pnc.rest.configuration.SwaggerConstants.SERVER_ERROR_CODE;
import static org.jboss.pnc.rest.configuration.SwaggerConstants.SERVER_ERROR_DESCRIPTION;
import static org.jboss.pnc.rest.configuration.SwaggerConstants.SUCCESS_CODE;
import static org.jboss.pnc.rest.configuration.SwaggerConstants.SUCCESS_DESCRIPTION;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.pnc.dto.response.ErrorResponse;
import org.jboss.pnc.rest.endpoints.internal.api.Client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cache statistics")
@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Client
public interface CacheEndpoint {

    @Operation(summary = "Get general statistics related to Hibernate.", responses = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_DESCRIPTION, content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, description = SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/statistics")
    public Response getGenericStats();

    @Operation(summary = "Get statistics of all entities in second-level cache.", responses = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_DESCRIPTION, content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, description = SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = INVALID_CODE, description = INVALID_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/entity-statistics")
    public Response getSecondLevelCacheEntitiesStats();

    @Operation(summary = "Get statistics of all cache region names in second-level cache.", responses = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_DESCRIPTION, content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, description = SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = INVALID_CODE, description = INVALID_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/region-statistics")
    public Response getSecondLevelCacheRegionsStats();

    @Operation(summary = "Get statistics of all collections in second-level cache.", responses = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_DESCRIPTION, content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, description = SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = INVALID_CODE, description = INVALID_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @GET
    @Path("/collection-statistics")
    public Response getSecondLevelCacheCollectionsStats();

    @Operation(summary = "Delete all content from second level cache. Needs to be admin", responses = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS_DESCRIPTION, content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = SERVER_ERROR_CODE, description = SERVER_ERROR_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = INVALID_CODE, description = INVALID_DESCRIPTION, content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
    @DELETE
    public Response clearCache();

}
