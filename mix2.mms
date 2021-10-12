x	    IS  	$3
bits    GREG
jim     GREG
bob     GREG

//String  BYTE    "Hello", #a, 0

Hello   ADD     $2, bits, 10
        SET     bits, $2
        SET     jim, bits
        JMP     F1
        ADD     $5, bits,  2
Main    ADD     $9, bits, 3
        STO     $2, 10, 5
        JMP     Hello
        LDO     $2, 10, 5
    	BN      $2, WHAT
H1	    SUB     $3, $30, x
WHAT	SUB 	$2, $3, 1
    	STB     $1, 1, 0
	    TRIP