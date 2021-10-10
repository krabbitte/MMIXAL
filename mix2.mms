x	    IS  	3

Main    SET     $2, -50
        ADD     $2, $2, 10
        STO     $2, 10, 5
        LDO     $2, 10, 5
    	BN      $2, WHAT
H1	    SUB     $3, $30, x
        JMP     WHAT
WHAT	SUB 	$2, $3, 1
    	STB     $1, 1, 0
	    TRIP