# Macro definition

## Definition syntax

Syntax to define a specific Macro into a Brainfuck program is as described below.

	DEFINE
	macroname
	AS
	body
	DONE

In the previous representation, `macroname` is defining the name of the Macro and `body` is for the whole body of the Macro (i.e, what each call of the Macro will be replaced by).

`DEFINE` is a keyword beginning the macro definition. It's always followed directly by the name of the macro to be defined. The keyword `AS` separates the name of the macro from its content (its body). To end the definition of the body, we use the `END` keyword.

In this way, each call of the specific `macroname` defined above will be replaced by the `body` before parsing and execution of the program.

## Example

As in the specifications, we can implement the `TO_DIGIT` macro as described below.

	DEFINE
	TO_DIGIT
	AS
	------------------------------------------------
	END

A full program using this definition of the macro will then look like the program below.

	DEFINE
	TO_DIGIT
	AS
	------------------------------------------------
	END

	IN
	TO_DIGIT
