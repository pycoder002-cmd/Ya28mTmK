package org.springframework.core.io;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface WritableResource extends Resource {
    OutputStream getOutputStream() throws IOException;

    boolean isWritable();
}
