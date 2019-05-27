<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:import href="xsl_util.xsl"/>
<xsl:output method="text" indent="no" omit-xml-declaration="yes"/>

<xsl:template match="/">
  <xsl:call-template name="file-header"/>

#ifndef TRACEFILES_JFRTYPES_HPP
#define TRACEFILES_JFRTYPES_HPP

#include "oops/symbol.hpp"
#include "trace/traceDataTypes.hpp"
#include "utilities/globalDefinitions.hpp"
#include "utilities/ticks.hpp"


enum JVMContentType {
  _not_a_content_type = (JVM_CONTENT_TYPES_START - 1),
  
<xsl:for-each select="trace/types/content_types/content_type[@jvm_type]">
  <xsl:value-of select="concat('  CONTENT_TYPE_', @jvm_type, ',',  $newline)"/>
</xsl:for-each>
  NUM_JVM_CONTENT_TYPES
};


enum JVMEventRelations {
  JVM_REL_NOT_AVAILABLE = 0,
  
<xsl:for-each select="trace/relation_decls/relation_decl">
  <xsl:value-of select="concat('  JVM_REL_', @id, ',', $newline)"/>
</xsl:for-each>
  NUM_EVENT_RELATIONS
};

/**
 * Create typedefs for the JRA types:
 *   typedef s8 TYPE_LONG;
 *   typedef s4 TYPE_INTEGER;
 *   typedef const char * TYPE_STRING;
 *   ...
 */
<xsl:for-each select="trace/types/primary_types/primary_type">
typedef <xsl:value-of select="@type"/>  TYPE_<xsl:value-of select="@symbol"/>;
</xsl:for-each>

#endif // JFRFILES_JFRTYPES_HPP
</xsl:template>

</xsl:stylesheet>
