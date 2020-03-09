<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fmp="http://www.filemaker.com/fmpxmlresult" exclude-result-prefixes="fmp">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:variable name="rowColor">
		<xsl:value-of select="'#cccccc'"/>
	</xsl:variable>
	<xsl:variable name="groupColor">
		<xsl:value-of select="'#999999'"/>
	</xsl:variable>
	<xsl:variable name="headerColor">
		<xsl:value-of select="'#9999cc'"/>
	</xsl:variable>
	<xsl:variable name="footerColor">
		<xsl:value-of select="'#9999cc'"/>
	</xsl:variable>
<!--

-->
	<xsl:variable name="dbNumCols">
		<xsl:value-of select="count(fmp:FMPXMLRESULT/fmp:METADATA/child::*) "/>
	</xsl:variable>

<!--

-->
	<xsl:template match="fmp:FMPXMLRESULT">
		<html>
			<head>
				<title>
					<xsl:text>Publications</xsl:text>
				</title>			
			</head>
			<body>
				<table border="1" cellPadding="1" cellSpacing="1">
					<xsl:call-template name="header"/>
					<xsl:for-each select="fmp:RESULTSET/fmp:ROW">
						<xsl:variable name="prevrow" select="preceding-sibling::fmp:ROW[position()=1]/fmp:COL[1]/fmp:DATA"/>
						<xsl:if test="position()=1 or $prevrow!=fmp:COL[1]/fmp:DATA">
							<xsl:call-template name="groupRow"/>
						</xsl:if>
						<xsl:variable name="prevrow2" select="preceding-sibling::fmp:ROW[position()=1]/fmp:COL[2]/fmp:DATA"/>
						<xsl:if test="position()=1 or $prevrow2!=fmp:COL[2]/fmp:DATA">
							<xsl:call-template name="groupRow2"/>
						</xsl:if>
						<tr>
						<xsl:for-each select="fmp:COL">
							<td>
								<xsl:if test="position() &gt;= 3">
									<xsl:value-of select="fmp:DATA"/>
								</xsl:if>
							</td>
						</xsl:for-each>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
<!--

-->
	<xsl:template name="header">
<!-- cut
		<tr>
			<td align="middle">
				<xsl:attribute name="colspan"><xsl:call-template name="numfields"/></xsl:attribute>
				<xsl:text>データベース名: </xsl:text>
				<xsl:value-of select="fmp:DATABASE/@NAME"/>
			</td>
		</tr>
		<tr>
			<td align="middle">
				<xsl:attribute name="colspan"><xsl:call-template name="numfields"/></xsl:attribute>
				<xsl:text>レコード数: </xsl:text>
				<xsl:value-of select="fmp:DATABASE/@RECORDS"/>
			</td>
		</tr>
-->
		<tr>
			<xsl:for-each select="fmp:METADATA/fmp:FIELD">
				<td align="middle">
					<xsl:value-of select="@NAME"/>
				</td>
			</xsl:for-each>
		</tr>
	</xsl:template>
<!--

-->
	<xsl:template name="groupRow">
		<tr>
			<td align="left">
				<xsl:attribute name="bgcolor"><xsl:value-of select="$groupColor"/></xsl:attribute>
				<xsl:attribute name="colspan"><xsl:value-of select="$dbNumCols"/></xsl:attribute>
				<font size="4">
					<xsl:value-of select="fmp:COL[1]/fmp:DATA"/>
				</font>
			</td>
		</tr>
	</xsl:template>
	<xsl:template name="groupRow2">
		<tr>
			<td> </td>
			<td align="left">
				<xsl:attribute name="bgcolor"><xsl:value-of select="$groupColor"/></xsl:attribute>
				<xsl:attribute name="colspan"><xsl:value-of select="$dbNumCols - 1"/></xsl:attribute>
				<font size="3">
					<xsl:value-of select="fmp:COL[2]/fmp:DATA"/>
				</font>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
