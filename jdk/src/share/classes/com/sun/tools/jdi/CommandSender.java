package com.sun.tools.jdi;

import com.sun.jdi.*;
import java.util.EventListener;

interface CommandSender {
    PacketStream send();
}
