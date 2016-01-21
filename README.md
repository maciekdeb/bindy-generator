### Status
[![Build Status](https://api.travis-ci.org/maciekdeb/bindy-generator.svg)](https://travis-ci.org/maciekdeb/bindy-generator)

# Apache Camel Bindy Generator

In order to compile this program the maven software will be needed.

```mvn clean install```

## Usage

Usage: bindy-generator [options] <FileName> <ClassNameWithPackage>
  Options:
    -a, --autospanLine
       
       Default: false
    -l, --crlf
       crlf
    -g, --generateHeaderColumns
       
       Default: false
    -o, --isOrdered
       
       Default: false
    -q, --quote
       
  * -s, --separator
       field separator
    -x, --skipFirstLine
       skip the first line of CSV
       Default: false
    -f
       -f<field_number>=<field_options> ex. -f1=pos(2)
       Syntax: -fkey=value
       Default: {}

