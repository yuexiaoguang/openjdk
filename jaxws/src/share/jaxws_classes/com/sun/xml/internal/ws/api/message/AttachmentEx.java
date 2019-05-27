package com.sun.xml.internal.ws.api.message;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;

/**
 * Attachment extended interface exposing custom MIME headers.
 */
public interface AttachmentEx extends Attachment {
        /**
         * MIME header
         */
        public interface MimeHeader {
                /**
                 * MIME header name
                 * @return name
                 */
                public String getName();
                /**
                 * MIME header value
                 * @return value
                 */
                public String getValue();
        }

        /**
         * Iterator of custom MIME headers associated with this attachment
         * @return MIME header iterator
         */
        public @NotNull Iterator<MimeHeader> getMimeHeaders();
}
