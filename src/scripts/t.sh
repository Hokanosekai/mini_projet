#!/bin/bash

# This script is to sanitise XML files to remove any BOM characters

has_bom() { head -c3 "$1" | LC_ALL=C grep -qe '\xef\xbb\xbf'; }

for filename in *.xml ; do
  if has_bom ${filename}; then
    tail -c +4 ${filename} > temp.xml
    mv temp.xml ${filename}
  fi
done
