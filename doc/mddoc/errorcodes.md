# Error codes documentation

bfck executable might return different error codes depending on the execution.

 * **0**: normal; no errors or warnings detected.

 * **1**: when the interpreted program causes an overflow. In other words, when the program is trying to increment or to decrement a memory case outside the limits (value between 0 and 255).

 * **2**: when data pointer goes outside of memory bounds. Those boundaries are defined by 0 memory case and 29999 memory case. 

 * **3**: when file specified for Input Stream (i.e with the -i option when lauching bfck executable) is not found / doesn't exist.

 * **4**: when a mismatch between the Jump and Back instruction counters has been found (Jump without a Back, or the contrary).

 * **5**: when arguments passed through the bfck executable are not correct (not recognized option, etc...).

 * **6**: when an invalid instruction is tried to be parsed. In other words, when a specific data from the interpreted file isn't matching any syntax of any implemented instruction.

 * **7**: when the program tries to read more characters than those available on the Input Stream.

 * **8**: when the program meets an issue while reading or writing in the Input or Output Stream.

 * **9**: when the specified path for Output Stream (i.e with the -o option when launching bfck executable) is not accessible / does not exist.

 * **10**: when Macro definition structure is not used correctly (see the Macro Definition Documentation for more details).
