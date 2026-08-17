package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
