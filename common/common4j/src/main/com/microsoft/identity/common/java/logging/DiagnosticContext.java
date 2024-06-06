// Copyright (c) Microsoft Corporation.
// All rights reserved.
//
// This code is licensed under the MIT License.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files(the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions :
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
package com.microsoft.identity.common.java.logging;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public enum DiagnosticContext {
    INSTANCE;

    public static final String CORRELATION_ID = "correlation_id";
    public static final String THREAD_ID = "thread_id";
    public static final String THREAD_NAME = "thread_name";

    // This is thread-safe.
    @SuppressFBWarnings("SE_BAD_FIELD_STORE")
    private transient final ThreadLocal<IRequestContext> REQUEST_CONTEXT_THREAD_LOCAL =
            new ThreadLocal<IRequestContext>() {
                @Override // This is the default value for the RequestContext if it's unset
                protected RequestContext initialValue() {
                    final RequestContext defaultRequestContext = new RequestContext();
                    defaultRequestContext.put(THREAD_ID, String.valueOf(Thread.currentThread().getId()));
                    defaultRequestContext.put(THREAD_NAME, Thread.currentThread().getName());
                    defaultRequestContext.put(CORRELATION_ID, "UNSET");
                    return defaultRequestContext;
                }
            };

    /**
     * Set the request context of the executing thread.
     *
     * @param requestContext IRequestContext
     */
    public void setRequestContext(final IRequestContext requestContext) {
        if (null == requestContext) {
            clear();
            return;
        }

        requestContext.put(THREAD_ID, String.valueOf(Thread.currentThread().getId()));
        requestContext.put(THREAD_NAME, Thread.currentThread().getName());
        REQUEST_CONTEXT_THREAD_LOCAL.set(requestContext);
    }

    /**
     * Get the request context of the executing thread.
     *
     * @return IRequestContext
     */
    public IRequestContext getRequestContext() {
        return REQUEST_CONTEXT_THREAD_LOCAL.get();
    }

    /**
     * Clear the local request context thread.
     */
    public void clear() {
        REQUEST_CONTEXT_THREAD_LOCAL.remove();
    }

}
