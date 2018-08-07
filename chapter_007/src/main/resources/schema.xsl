<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
         <xsl:template match="/">
         <entries>
             <xsl:for-each select="entries/myEntry">
       <entry>
               <xsl:attribute name="href">
                   <xsl:value-of select="value"/>
               </xsl:attribute>
       </entry>
           </xsl:for-each>
         </entries>
        </xsl:template>
        </xsl:stylesheet>