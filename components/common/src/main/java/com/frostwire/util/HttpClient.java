/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
 * Copyright (c) 2011, 2012, FrostWire(R). All rights reserved.
 
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

package com.frostwire.util;

import java.io.File;
import java.io.IOException;


/**
 * A pure java based HTTP client with resume capabilities.
 * @author gubatron
 * @author aldenml
 *
 */
public interface HttpClient {

    public void setListener(HttpClientListener listener);
    
    public HttpClientListener getListener();
    
    public String get(String url);

    public String get(String url, int timeout, String userAgent);

    public void save(String url, File file, boolean resume) throws IOException;

    public void save(String url, File file, boolean resume, int timeout, String userAgent) throws IOException;
    
    public void cancel();
    
    public interface HttpClientListener {
        public void onError(HttpClient client, Exception e);
        
        public void onData(HttpClient client, byte[] buffer, int offset, int length);
        
        public void onComplete(HttpClient client);
        
        public void onCancel(HttpClient client);
    }

    public static class HttpRangeException extends IOException {

        private static final long serialVersionUID = 1891038288667531894L;

        public HttpRangeException(String message) {
            super(message);
        }
    }

    public static final class RangeNotSupportedException extends HttpRangeException {

        private static final long serialVersionUID = -3356618211960630147L;

        public RangeNotSupportedException(String message) {
            super(message);
        }
    }

    public static final class HttpRangeOutOfBoundsException extends HttpRangeException {
        
        private static final long serialVersionUID = -335661829606230147L;

        public HttpRangeOutOfBoundsException(int rangeStart, long expectedFileSize) {
            super("HttpRange Out of Bounds error: start=" + rangeStart + " expected file size=" + expectedFileSize);
        }

    }
}