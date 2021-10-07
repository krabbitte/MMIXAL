x	    IS  	3
y       IS      2
five    IS      5

Main    ADD     $1, $1, 2
        ADD     $2, $1, -50
    	ADD 	$255, $1, $2
    	BN      $255, WHAT
	    LDA	    $2, 3, 1
H1	    SUB     $3, $30, x
        JMP     WHAT
WHAT	SUB 	$2, $3, 1
    	STB     $1, 1, 0
	    TRIP