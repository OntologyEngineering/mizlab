#! /usr/local/bin/perl
print "Content-type: text/plain\n";
print "\n";
if(defined($ENV{"CONTENT_LENGTH"})) {
	$len = $ENV{"CONTENT_LENGTH"} ;
	$i = 0;
	open(F,">data/filedata");
	$fn;

	while ( $i < $len ){
		$c = getc;	
		$i++;
		print F $c;
	}

	close(F);


}


