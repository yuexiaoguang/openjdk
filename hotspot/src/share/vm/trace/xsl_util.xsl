<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<!-- utilities used when generating code -->

<xsl:variable name="newline">
  <xsl:text>&#xA;</xsl:text>
</xsl:variable>

<xsl:variable name="indent1">
  <xsl:text>&#xA;  </xsl:text>
</xsl:variable>

<xsl:variable name="indent2">
  <xsl:text>&#xA;    </xsl:text>
</xsl:variable>

<xsl:variable name="indent3">
  <xsl:text>&#xA;      </xsl:text>
</xsl:variable>

<xsl:variable name="indent4">
  <xsl:text>&#xA;        </xsl:text>
</xsl:variable>

<xsl:variable name="quote">
  <xsl:text>"</xsl:text>
</xsl:variable>

<xsl:template name="file-header">
  <xsl:text>/* AUTOMATICALLY GENERATED FILE - DO NOT EDIT */</xsl:text>
</xsl:template>

<xsl:template name="string-replace-all">
  <xsl:param name="text" />
  <xsl:param name="replace" />
  <xsl:param name="by" />
  <xsl:choose>
    <xsl:when test="contains($text, $replace)">
      <xsl:value-of select="substring-before($text,$replace)" />
      <xsl:value-of select="$by" />
      <xsl:call-template name="string-replace-all">
        <xsl:with-param name="text" select="substring-after($text,$replace)" />
        <xsl:with-param name="replace" select="$replace" />
        <xsl:with-param name="by" select="$by" />
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:value-of select="$text" />
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>


</xsl:stylesheet>
