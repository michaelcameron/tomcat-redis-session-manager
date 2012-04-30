package com.warmhealth.catalina.session;

import org.apache.catalina.Manager;
import org.apache.catalina.session.StandardSession;

public class RedisSession extends StandardSession {

    public RedisSession(Manager manager) {
        super(manager);
    }

    @Override
    protected boolean isValidInternal() {
        return isValid;
    }

    @Override
    public boolean isValid() {
        return isValidInternal();
    }

    @Override
    public void setValid(boolean isValid) {
        this.isValid = isValid;
        if (!isValid) {
            String keys[] = keys();
            for (String key : keys) {
                removeAttributeInternal(key, false);
            }
            getManager().remove(this);
        }
    }

    @Override
    public void invalidate() {
        setValid(false);
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}