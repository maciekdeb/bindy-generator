### Status [![Build Status](https://api.travis-ci.org/maciekdeb/bindy-generator.svg)](https://travis-ci.org/maciekdeb/bindy-generator)

# Apache Camel Bindy Generator

This application automatically generates domain classes for library http://camel.apache.org/bindy.html

In order to compile this program the maven software will be needed.

```mvn clean install```

## Usage
```
Usage: bindy-generator [options] <FileName> <ClassNameWithPackage>
  Options:
    -a, --autospanLine
       if enabled then the last column is auto spanned to end of line
       Default: false
    -l, --crlf
       allow to define the carriage return character to use
       Default: WINDOWS
    -g, --generateHeaderColumns
       uses to generate the header columns of the CSV generates
       Default: false
    -o, --isOrdered
       allow to change the order of the fields when CSV is generated
       Default: false
    -q, --quote
       allow to specify a quote character of the fields when CSV is generated
       Default: "
  * -s, --separator
       field separator
    -x, --skipFirstLine
       skip the first line of CSV
       Default: false
    -f
       -f<field_number>=<field_options> ex. -f1=pos(2)
       Syntax: -fkey=value
       Default: {}
```
