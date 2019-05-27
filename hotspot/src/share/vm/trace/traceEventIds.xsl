<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:import href="xsl_util.xsl"/>
<xsl:output method="text" indent="no" omit-xml-declaration="yes"/>

<xsl:template match="/">
  <xsl:call-template name="file-header"/>

#ifndef TRACEFILES_JFREVENTIDS_HPP
#define TRACEFILES_JFREVENTIDS_HPP

#include "utilities/macros.hpp"

#if INCLUDE_TRACE

#include "trace/traceDataTypes.hpp"

/**
 * Enum of the event types in the JVM
 */
enum TraceEventId {
  _traceeventbase = (NUM_RESERVED_EVENTS-1), // Make sure we start at right index.
  
  // Events -> enum entry
<xsl:for-each select="trace/events/event">
  <xsl:value-of select="concat('  Trace', @id, 'Event,', $newline)"/>
</xsl:for-each>
  MaxTraceEventId
};

/**
 * Struct types in the JVM
 */
enum TraceStructId {
<xsl:for-each select="trace/types/content_types/*">
  <xsl:value-of select="concat('  Trace', @id, 'Struct,', $newline)"/>
</xsl:for-each>
<xsl:for-each select="trace/events/*">
  <xsl:value-of select="concat('  Trace', @id, 'Struct,', $newline)"/>
</xsl:for-each>
  MaxTraceStructId
};

typedef enum TraceEventId  TraceEventId;
typedef enum TraceStructId TraceStructId;

#endif
#endif
</xsl:template>

</xsl:stylesheet>
