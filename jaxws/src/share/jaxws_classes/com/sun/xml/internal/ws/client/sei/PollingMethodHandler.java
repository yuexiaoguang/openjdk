package com.sun.xml.internal.ws.client.sei;

import java.lang.reflect.Method;

import com.sun.xml.internal.ws.api.databinding.ClientCallBridge;
import com.sun.xml.internal.ws.model.JavaMethodImpl;
import javax.xml.ws.Response;
import javax.xml.ws.WebServiceException;

/**
 * {@link MethodHandler} that handles asynchronous invocations through {@link Response}.
 */
final class PollingMethodHandler extends AsyncMethodHandler {

//    PollingMethodHandler(SEIStub owner, JavaMethodImpl jm, JavaMethodImpl core) {
//        super(owner, jm, core);
//    }

    PollingMethodHandler(SEIStub owner, Method m) {
        super(owner, m);
    }

    Response<?> invoke(Object proxy, Object[] args) throws WebServiceException {
        return doInvoke(proxy,args,null);
    }
}
