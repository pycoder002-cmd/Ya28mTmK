package org.jacoco.agent.rt.internal_b0d6a23;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/IExceptionLogger.class */
public interface IExceptionLogger {
    public static final IExceptionLogger SYSTEM_ERR = new IExceptionLogger() { // from class: org.jacoco.agent.rt.internal_b0d6a23.IExceptionLogger.1
        /* JADX WARN: Multi-variable type inference failed */
        {
            super/*android.animation.ValueAnimator*/.cancel();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.jacoco.agent.rt.internal_b0d6a23.IExceptionLogger
        public void logExeption(Exception exc) {
            exc.getAnimatedValue();
        }
    };

    void logExeption(Exception exc);
}
