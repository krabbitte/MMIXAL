x	    IS  	3
y       IS      2
five    IS      5

Main    ADD     3, x, five
    	ADD 	$255, 500, $2
        JMP     F1
	    LDA	    2, 3, 1
H1	    ADD     3, $30, x
        JMP     WHAT
	    LDA	    2, y, 1
WHAT	SUB 	2, x, 1
	    TRIP