
#ifndef JDWP_FRAMEID_H
#define JDWP_FRAMEID_H

typedef jlong FrameID;

FrameID createFrameID(jthread thread, FrameNumber fnum);
FrameNumber getFrameNumber(FrameID frame);
jdwpError validateFrameID(jthread thread, FrameID frame);

#endif
