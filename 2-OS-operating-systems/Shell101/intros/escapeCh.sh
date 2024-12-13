#!/bin/sh
echo "Hello 'World'\n"
echo "Hello \"World\"\n"
#'\' is used to allow printing characters with meaning in strings
#Characters: ", $, `, \
X=10
echo "Quote=\"; Backslash=\\; Backstick=\`; Dollar=\$; X=${X}.\n"
