package org.jacoco.agent.rt.internal_b0d6a23;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import org.jacoco.agent.rt.IAgent;
import org.jacoco.agent.rt.internal_b0d6a23.core.JaCoCo;
import org.jacoco.agent.rt.internal_b0d6a23.core.data.ExecutionDataWriter;
import org.jacoco.agent.rt.internal_b0d6a23.core.runtime.AbstractRuntime;
import org.jacoco.agent.rt.internal_b0d6a23.core.runtime.AgentOptions;
import org.jacoco.agent.rt.internal_b0d6a23.core.runtime.RuntimeData;
import org.jacoco.agent.rt.internal_b0d6a23.output.FileOutput;
import org.jacoco.agent.rt.internal_b0d6a23.output.IAgentOutput;
import org.jacoco.agent.rt.internal_b0d6a23.output.NoneOutput;
import org.jacoco.agent.rt.internal_b0d6a23.output.TcpClientOutput;
import org.jacoco.agent.rt.internal_b0d6a23.output.TcpServerOutput;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/Agent.class */
public class Agent implements IAgent {
    private static Agent singleton;
    private final AgentOptions options;
    private final IExceptionLogger logger;
    private final RuntimeData data = new RuntimeData();
    private IAgentOutput output;
    private Callable<Void> jmxRegistration;

    public static synchronized Agent getInstance(AgentOptions options) {
        if (singleton == null) {
            Agent agent = new Agent(options, IExceptionLogger.SYSTEM_ERR);
            agent.startup();
            Runtime.getRuntime().addShutdownHook(new Thread() { // from class: org.jacoco.agent.rt.internal_b0d6a23.Agent.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Agent.this.shutdown();
                }
            });
            singleton = agent;
        }
        return singleton;
    }

    public static synchronized Agent getInstance() throws IllegalStateException {
        if (singleton == null) {
            throw new IllegalStateException("JaCoCo agent not started.");
        }
        return singleton;
    }

    Agent(AgentOptions options, IExceptionLogger logger) {
        this.options = options;
        this.logger = logger;
    }

    public RuntimeData getData() {
        return this.data;
    }

    public void startup() {
        try {
            String sessionId = this.options.getSessionId();
            if (sessionId == null) {
                sessionId = createSessionId();
            }
            this.data.setSessionId(sessionId);
            this.output = createAgentOutput();
            this.output.startup(this.options, this.data);
            if (this.options.getJmx()) {
                this.jmxRegistration = new JmxRegistration(this);
            }
        } catch (Exception e) {
            this.logger.logExeption(e);
        }
    }

    public void shutdown() {
        try {
            if (this.options.getDumpOnExit()) {
                this.output.writeExecutionData(false);
            }
            this.output.shutdown();
            if (this.jmxRegistration != null) {
                this.jmxRegistration.call();
            }
        } catch (Exception e) {
            this.logger.logExeption(e);
        }
    }

    IAgentOutput createAgentOutput() {
        AgentOptions.OutputMode controllerType = this.options.getOutput();
        switch (controllerType) {
            case file:
                return new FileOutput();
            case tcpserver:
                return new TcpServerOutput(this.logger);
            case tcpclient:
                return new TcpClientOutput(this.logger);
            case none:
                return new NoneOutput();
            default:
                throw new AssertionError(controllerType);
        }
    }

    private String createSessionId() {
        String host;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            host = "unknownhost";
        }
        return host + "-" + AbstractRuntime.createRandomId();
    }

    public String getVersion() {
        return JaCoCo.VERSION;
    }

    public String getSessionId() {
        return this.data.getSessionId();
    }

    public void setSessionId(String id) {
        this.data.setSessionId(id);
    }

    public void reset() {
        this.data.reset();
    }

    public byte[] getExecutionData(boolean reset) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ExecutionDataWriter writer = new ExecutionDataWriter(buffer);
            this.data.collect(writer, writer, reset);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void dump(boolean reset) throws IOException {
        this.output.writeExecutionData(reset);
    }
}
