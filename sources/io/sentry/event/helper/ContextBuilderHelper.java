package io.sentry.event.helper;

import io.sentry.SentryClient;
import io.sentry.context.Context;
import io.sentry.event.Breadcrumb;
import io.sentry.event.EventBuilder;
import io.sentry.event.User;
import io.sentry.event.interfaces.UserInterface;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ContextBuilderHelper implements EventBuilderHelper {
    private SentryClient sentryClient;

    public ContextBuilderHelper(SentryClient sentryClient) {
        this.sentryClient = sentryClient;
    }

    private UserInterface fromUser(User user) {
        return new UserInterface(user.getId(), user.getUsername(), user.getIpAddress(), user.getEmail(), user.getData());
    }

    @Override // io.sentry.event.helper.EventBuilderHelper
    public void helpBuildingEvent(EventBuilder eventBuilder) {
        Context context = this.sentryClient.getContext();
        List<Breadcrumb> breadcrumbs = context.getBreadcrumbs();
        if (!breadcrumbs.isEmpty()) {
            eventBuilder.withBreadcrumbs(breadcrumbs);
        }
        if (context.getUser() != null) {
            eventBuilder.withSentryInterface(fromUser(context.getUser()));
        }
        Map<String, String> tags = context.getTags();
        if (!tags.isEmpty()) {
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                eventBuilder.withTag(entry.getKey(), entry.getValue());
            }
        }
        Map<String, Object> extra = context.getExtra();
        if (extra.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry2 : extra.entrySet()) {
            eventBuilder.withExtra(entry2.getKey(), entry2.getValue());
        }
    }
}
