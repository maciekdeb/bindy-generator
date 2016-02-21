### Status [![Build Status](https://api.travis-ci.org/maciekdeb/bindy-generator.svg)](https://travis-ci.org/maciekdeb/bindy-generator)

# Apache Camel Bindy Generator

This application automatically generates domain classes for library http://camel.apache.org/bindy.html

In order to compile this program the maven software will be needed.

```mvn clean install```

## Usage

```
Usage: bindy-generator -f [file name] -c [class name] [options] [command {fixed, csv}]
  Options:
    -p, --path
       path for generated java class
       Default: .
  * -c
       ClassNameWithPackage
  * -f
       FileName
    -field
       -field<number>=<options> ex. -field1=pos(2)
       Syntax: -fieldkey=value
       Default: {1=pos(1),length(23), 3=pos(4),length(1),trim}
  Commands:
    fixed      Generates fixed length based domain class
      Usage: fixed [options]
        Options:
          --crlf
             
             Default: WINDOWS
          --hasFooter
             
          --hasHeader
             
          --ignoreTrailingChars
             
             Default: false
          --isFooter
             
          --isHeader
             
          --length
             
             Default: 0
          --paddingChar
             
             Default:  
          --skipFooter
             
             Default: false
          --skipHeader
             
             Default: false

    csv      Generates csv based domain class
      Usage: csv [options]
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
             allow to specify a quote character of the fields when CSV is
             generated
             Default: "
          -s, --separator
             field separator
          -x, --skipFirstLine
             skip the first line of CSV
             Default: false
```
Examples
```
-f order.csv -c com.shop.Order -field1=pos(1),length(23) -field3=pos(4),length(1),trim csv --separator ,
```
