#!/usr/bin/csh

echo "lock" > .lock
/opt/sfw/bin/wget -q -O fmout.xml -i fmurl
/usr/local/bin/Xalan -o out.html fmout.xml gu.xsl 
echo "Location: out.html"
echo ""
echo "<html><head></head><body></body></html>"
rm .lock
