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

package com.microsoft.identity.common.java.flighting;

import lombok.NonNull;

/**
 * List of Active Common flights.
 */
public enum CommonFlight implements IFlightConfig {
    /**
     * Flight to control whether or not to use Network capability for performing network check.
     */
    USE_NETWORK_CAPABILITY_FOR_NETWORK_CHECK("UseNetworkCapabilityForNetworkCheck", false),
    /**
     * Flight to control whether to expose the CCS (CachedCredService) request ID in TokenResponse.
     * This flight is default-on 
     */
    EXPOSE_CCS_REQUEST_ID_IN_TOKENRESPONSE("ExposeCcsRequestIdInTokenResponse", true);

    private String key;
    private Object defaultValue;
    CommonFlight(@NonNull String key, @NonNull Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Object getDefaultValue() {
        return this.defaultValue;
    }
}
