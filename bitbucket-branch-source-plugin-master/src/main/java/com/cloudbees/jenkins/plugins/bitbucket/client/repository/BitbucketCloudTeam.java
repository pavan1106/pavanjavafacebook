/*
 * The MIT License
 *
 * Copyright (c) 2016-2017, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cloudbees.jenkins.plugins.bitbucket.client.repository;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketHref;
import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketTeam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import java.util.Map;

public class BitbucketCloudTeam implements BitbucketTeam {

    @JsonProperty("username")
    private String name;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("links")
    @JsonDeserialize(keyAs = String.class, contentUsing = BitbucketHref.Deserializer.class)
    private Map<String,List<BitbucketHref>> links;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonIgnore
    public Map<String, List<BitbucketHref>> getLinks() {
        return links;
    }

    @JsonIgnore
    public void setLinks(Map<String, List<BitbucketHref>> links) {
        this.links = links;
    }
}
