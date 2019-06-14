/*
 * The MIT License
 *
 * Copyright 2016 CloudBees, Inc.
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

package com.cloudbees.jenkins.plugins.bitbucket;

import edu.umd.cs.findbugs.annotations.NonNull;
import jenkins.scm.api.SCMRevision;
import jenkins.scm.api.mixin.ChangeRequestCheckoutStrategy;
import jenkins.scm.api.mixin.ChangeRequestSCMRevision;

/**
 * Revision of a pull request.
 *
 * @since 2.2.0
 */
public class PullRequestSCMRevision<R extends SCMRevision> extends ChangeRequestSCMRevision<PullRequestSCMHead> {

    /**
     * Standardize serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The pull head revision.
     */
    @NonNull
    private final R pull;

    /**
     * Constructor.
     *
     * @param head   the head.
     * @param target the target revision.
     * @param pull   the pull revision.
     */
    public PullRequestSCMRevision(@NonNull PullRequestSCMHead head, @NonNull R target, @NonNull R pull) {
        super(head, target);
        this.pull = pull;
    }

    /**
     * Gets the pull revision.
     *
     * @return the pull revision.
     */
    @NonNull
    public R getPull() {
        return pull;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equivalent(ChangeRequestSCMRevision<?> o) {
        if (!(o instanceof PullRequestSCMRevision)) {
            return false;
        }
        PullRequestSCMRevision other = (PullRequestSCMRevision) o;
        return getHead().equals(other.getHead()) && pull.equals(other.pull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int _hashCode() {
        return pull.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getHead() instanceof PullRequestSCMHead
                && ((PullRequestSCMHead) getHead()).getCheckoutStrategy() == ChangeRequestCheckoutStrategy.MERGE
                ? getPull().toString() + "+" + getTarget().toString()
                : getPull().toString();
    }

}
