# Brainfuck interpreter by MIAOU Team — SI3 2016-2017 Polytech Nice

## Build

This project can be built using the command:

    ./build.sh

This shell script tells Maven to build the Jar archive and the Javadoc.

## Run

The brainfuck interpreter can be run using the command:

    ./bfck -p <program file>.bf

The following parameters are supported:

    	--check       check for conditional jumps consistency (ie. each JUMP instruction is bound to a BACK one)
    	--rewrite     write the shortened representation of the input program on the standard output
    	--translate   translate a textual representation of a program into an image-based one on the standard output
    	--trace       write a log file named <program file>.log
    	--generate    generate code in another language
    	--nometrics   disable final metrics report
   		-i            input file for In instructions
   		-o            output file for Out instructions
