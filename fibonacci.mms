n       IS     21

Main    SET    $10, n
        ADD    $1,  $1,  1
        ADD    $2,  $2,  1
        JMP    LOOP

LOOP    ADD    $3,  $1,  $2
        SET    $1,  $2
        SET    $2,  $3
        CMP    $9,  $3,  $10
        BN     $9,  LOOP
	    TRIP