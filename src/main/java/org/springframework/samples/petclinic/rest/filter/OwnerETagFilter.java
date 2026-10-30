/*
 * Copyright 2016-2025 the original author or authors.
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

package org.springframework.samples.petclinic.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Adds a weak {@code ETag} header (W/"&lt;md5 hex of body&gt;") to successful
 * {@code GET /api/owners/{ownerId}} responses, as declared in openapi.yml.
 */
@Component
public class OwnerETagFilter extends OncePerRequestFilter {

    private static final Pattern OWNER_PATH = Pattern.compile("^/api/owners/\\d+$");

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !"GET".equals(request.getMethod())
            || !OWNER_PATH.matcher(urlPathHelper.getPathWithinApplication(request)).matches();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
        try {
            filterChain.doFilter(request, wrapper);
            byte[] body = wrapper.getContentAsByteArray();
            if (wrapper.getStatus() == HttpServletResponse.SC_OK && body.length > 0 && !wrapper.isCommitted()) {
                wrapper.setHeader("ETag", "W/\"" + DigestUtils.md5DigestAsHex(body) + "\"");
            }
        } finally {
            wrapper.copyBodyToResponse();
        }
    }
}
