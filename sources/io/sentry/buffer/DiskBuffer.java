package io.sentry.buffer;

import io.sentry.event.Event;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class DiskBuffer implements Buffer {
    public static final String FILE_SUFFIX = ".sentry-event";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) DiskBuffer.class);
    private final File bufferDir;
    private int maxEvents;

    public DiskBuffer(File file, int i) {
        this.bufferDir = file;
        this.maxEvents = i;
        String str = "Could not create or write to disk buffer dir: " + file.getAbsolutePath();
        try {
            file.mkdirs();
            if (!file.isDirectory() || !file.canWrite()) {
                throw new RuntimeException(str);
            }
            logger.debug(Integer.toString(getNumStoredEvents()) + " stored events found in dir: " + file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(str, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[Catch: all -> 0x007b, Throwable -> 0x007e, SYNTHETIC, TRY_LEAVE, TryCatch #4 {Throwable -> 0x007e, blocks: (B:5:0x000f, B:9:0x001a, B:27:0x0077, B:34:0x0073, B:28:0x007a), top: B:4:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[Catch: Exception -> 0x0095, FileNotFoundException -> 0x00d1, SYNTHETIC, TRY_LEAVE, TryCatch #10 {FileNotFoundException -> 0x00d1, Exception -> 0x0095, blocks: (B:3:0x0001, B:11:0x001f, B:54:0x0088, B:51:0x0091, B:58:0x008d, B:52:0x0094), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private io.sentry.event.Event fileToEvent(java.io.File r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            java.lang.String r3 = r7.getAbsolutePath()     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            r2.<init>(r3)     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            r1.<init>(r2)     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
            java.lang.Object r3 = r2.readObject()     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            if (r2 == 0) goto L1d
            r2.close()     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
        L1d:
            if (r1 == 0) goto L22
            r1.close()     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
        L22:
            io.sentry.event.Event r3 = (io.sentry.event.Event) r3     // Catch: java.lang.Exception -> L25
            return r3
        L25:
            r1 = move-exception
            org.slf4j.Logger r2 = io.sentry.buffer.DiskBuffer.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error casting Object to Event: "
            r3.append(r4)
            java.lang.String r4 = r7.getAbsolutePath()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.error(r3, r1)
            boolean r1 = r7.delete()
            if (r1 != 0) goto L60
            org.slf4j.Logger r1 = io.sentry.buffer.DiskBuffer.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to delete Event: "
            r2.append(r3)
            java.lang.String r7 = r7.getAbsolutePath()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r1.warn(r7)
        L60:
            return r0
        L61:
            r3 = move-exception
            r4 = r0
            goto L6a
        L64:
            r3 = move-exception
            throw r3     // Catch: java.lang.Throwable -> L66
        L66:
            r4 = move-exception
            r5 = r4
            r4 = r3
            r3 = r5
        L6a:
            if (r2 == 0) goto L7a
            if (r4 == 0) goto L77
            r2.close()     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L7b
            goto L7a
        L72:
            r2 = move-exception
            r4.addSuppressed(r2)     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
            goto L7a
        L77:
            r2.close()     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
        L7a:
            throw r3     // Catch: java.lang.Throwable -> L7b java.lang.Throwable -> L7e
        L7b:
            r2 = move-exception
            r3 = r0
            goto L84
        L7e:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> L80
        L80:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L84:
            if (r1 == 0) goto L94
            if (r3 == 0) goto L91
            r1.close()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            goto L94
        L8c:
            r1 = move-exception
            r3.addSuppressed(r1)     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
            goto L94
        L91:
            r1.close()     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
        L94:
            throw r2     // Catch: java.lang.Exception -> L95 java.io.FileNotFoundException -> Ld1
        L95:
            r1 = move-exception
            org.slf4j.Logger r2 = io.sentry.buffer.DiskBuffer.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error reading Event file: "
            r3.append(r4)
            java.lang.String r4 = r7.getAbsolutePath()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.error(r3, r1)
            boolean r1 = r7.delete()
            if (r1 != 0) goto Ld0
            org.slf4j.Logger r1 = io.sentry.buffer.DiskBuffer.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to delete Event: "
            r2.append(r3)
            java.lang.String r7 = r7.getAbsolutePath()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            r1.warn(r7)
        Ld0:
            return r0
        Ld1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.buffer.DiskBuffer.fileToEvent(java.io.File):io.sentry.event.Event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Event getNextEvent(Iterator<File> it) {
        Event fileToEvent;
        while (it.hasNext()) {
            File next = it.next();
            if (next.getAbsolutePath().endsWith(FILE_SUFFIX) && (fileToEvent = fileToEvent(next)) != null) {
                return fileToEvent;
            }
        }
        return null;
    }

    private int getNumStoredEvents() {
        int i = 0;
        for (File file : this.bufferDir.listFiles()) {
            if (file.getAbsolutePath().endsWith(FILE_SUFFIX)) {
                i++;
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[Catch: all -> 0x00c3, Throwable -> 0x00c5, SYNTHETIC, TRY_LEAVE, TryCatch #6 {, blocks: (B:15:0x0096, B:19:0x00a0, B:33:0x00bf, B:40:0x00bb, B:34:0x00c2), top: B:14:0x0096, outer: #2 }] */
    @Override // io.sentry.buffer.Buffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void add(io.sentry.event.Event r7) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.buffer.DiskBuffer.add(io.sentry.event.Event):void");
    }

    @Override // io.sentry.buffer.Buffer
    public void discard(Event event) {
        File file = new File(this.bufferDir, event.getId().toString() + FILE_SUFFIX);
        if (file.exists()) {
            logger.debug("Discarding Event from offline storage: " + file.getAbsolutePath());
            if (file.delete()) {
                return;
            }
            logger.warn("Failed to delete Event: " + file.getAbsolutePath());
        }
    }

    @Override // io.sentry.buffer.Buffer
    public Iterator<Event> getEvents() {
        final Iterator it = Arrays.asList(this.bufferDir.listFiles()).iterator();
        return new Iterator<Event>() { // from class: io.sentry.buffer.DiskBuffer.1
            private Event next;

            {
                this.next = DiskBuffer.this.getNextEvent(it);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Event next() {
                Event event = this.next;
                this.next = DiskBuffer.this.getNextEvent(it);
                return event;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
