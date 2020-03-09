#! /usr/local/bin/perl
#      $@",$3$3$K$"$J$?$N2CF~$7$F$$$k%W%m%P%$%@$N(Jperl$@8@8l$N%Q%9$r;XDj$7$^$9(J
#	$@$[$H$s$I$N>l9g!"(J!/usr/local/bin/perl
#-----------------------------------------------------
#$@"-(Jjcode$@JQ49%i%$%V%i%j(J
require 'jcode.pl';

#-----------------------------------------------------
#$@%m%0$rJ]B8$9$k%G!<%?%U%!%$%kL>(J
$datafile = 'webhandler.dat';
#-----------------------------------------------------
#$@$"$J$?$N%a!<%k%"%I%l%9$r;XDj$7$^$9!#(J
#$@$3$N%"%I%l%9$K%a!<%k$,FO$-$^$9(J
$mailto = 'kita@ei.sanken.osaka-u.ac.jp';
#-----------------------------------------------------
#$@Aw?.8e$"$J$?$N%Z!<%8$KLa$k$?$a$N%[!<%`%Z!<%8%"%I%l%9(J
$homepage = 'http://www.ei.sanken.osaka-u.ac.jp/~kita/;
#-----------------------------------------------------
#$@Aw$i$l$F$-$?%a!<%k$NBjL>$r@_Dj(J
$subject = 'Test';
#-----------------------------------------------------
#sendmail$@$N%Q%9!!MxMQ$G$-$J$$%W%m%P%$%@$O(J '' $@$K$9$k!#(J
$sendmail = '';
#-----------------------------------------------------
#$@L$5-F~$r5v2D$7$J$$9`L\L>!J%U%#!<%k%IL>!K$r$$$/$D$G$b(J
#$@Aw?.$5$l$?%U%)!<%`$N9`L\$K<!$N9`L\L>(J(name$@%W%m%Q%F%#(J)
#$@$,$"$j!"K,Ld<T$,L$5-F~$J$i$PAw?.$rCf;_$7$^$9!#(J
#$CHECK[0] = '$@$*L>A0(J';
#$CHECK[1] = 'email';
#$CHECK[2] = '$@$40U8+(J';
#$CHECK[3] = '$@$44uK>(J';
#$CHECK[4] = '$@FbMF(J';
#-----------------------------------------------------
#$@%m%0$r=87W$9$k:GBg7o?t(J
$max = 1000;
#----------------------------------------------------------
#$@3F9`L\!!:GBgI=<(9T?t(J 0$@$K$9$k$H$9$Y$F$rI=<((J
$max_vew = 20;
#----------------------------------------------------------
#$@%0%i%U$N:GBgI=<(I}!J%T%/%;%k!K(J
$graphvew = 200;
#-----------------------------------------------------
#$@%m%0$r=87W$7$J$$%U%#!<%k%IL>(J
#$@K,Ld<T$+$i$N%a%C%;!<%8$J$I$OKh2s0[$J$k0Y=87W$N0UL#$,$J$$!#(J
#$@!J%a!<%k$K$OAw?.$5$l$k!K(J
#$NO_LOG[0] = '$@%3%a%s%H(J';
#$NO_LOG[1] = '$@$40U8+(J';
#$NO_LOG[2] = '$@$44uK>(J';
#$NO_LOG[3] = '$@%a%b(J';
#$NO_LOG[4] = '$@$*L>A0(J';
#$NO_LOG[5] = 'name';
#$NO_LOG[6] = 'email';
#$NO_LOG[7] = 'HP';
#=======================================================================================
#			$@$3$3$+$i$OJQ99$9$kI,MW$O$"$j$^$;$s(J
#=======================================================================================
$ENV{'TZ'} = "JST-9"; 
@DATE = localtime(time);
$DATE[5] += 1900;
$DATE[4] = sprintf("%02d",$DATE[4] + 1);
$DATE[3] = sprintf("%02d",$DATE[3]);
$DATE[2] = sprintf("%02d",$DATE[2]);
$DATE[1] = sprintf("%02d",$DATE[1]);
$DATE[6] = ('$@F|(J','$@7n(J','$@2P(J','$@?e(J','$@LZ(J','$@6b(J','$@EZ(J') [$DATE[6]];
$DATE = "$DATE[5]$@G/(J$DATE[4]$@7n(J$DATE[3]$@F|(J($DATE[6])";
$DATE_TIME = "$DATE[5]$@G/(J$DATE[4]$@7n(J$DATE[3]$@F|(J($DATE[6]) $DATE[2]$@;~(J$DATE[1]$@J,(J";
$hostaddr = &domain_name;
if ($ENV{'REQUEST_METHOD'} eq "POST") {
	read(STDIN, $QUERY_DATA, $ENV{'CONTENT_LENGTH'});
} else { $QUERY_DATA = $ENV{'QUERY_STRING'}; }
@pairs = split(/&/,$QUERY_DATA);
foreach $pair (@pairs) {
	($name, $value) = split(/=/, $pair);
	$name =~ tr/+/ /;
	$name =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg;
	$value =~ tr/+/ /;
	$value =~ s/%([a-fA-F0-9][a-fA-F0-9])/pack("C", hex($1))/eg;
	&jcode'convert(*name,'sjis');
	&jcode'convert(*value,'sjis');
	if (!($value =~ /\n/) && $value =~ /\r/) { $value =~ s/\r/\n/g; }
	if ($name eq 'email') { $email = $value; }
	if ($name eq 'action') { $action = $value;
	} else {
		$name =~ s/</&lt;/g;
		$name =~ s/>/&gt;/g;
		$name =~ s/"/&quot;/g;
		$name =~ s/\t//g;
		$name =~ s/=/&eq;/g;
		$value =~ s/</&lt;/g;
		$value =~ s/>/&gt;/g;
		$value =~ s/"/&quot;/g;
		$value =~ s/\t//g;
		$value =~ s/=/&eq;/g;
		push(@NAME,$name);
		push(@VALUE,$value);
		foreach $buff (@CHECK) {
			if ($buff eq $name) {
				if ($buff eq 'email') {
					if ($value !~ /.+\@.+\..+/) { &error(bad_email); } 
				} else {
					if ($value eq '') { &error(bad_data); } 
				}
			}
		}
		$QUERY{$name} = $value;
	}
}
if (!($email =~ /.+\@.+\..+/)) { $email = 'nobody@xxx.xxx.or.jp'; }
$count = @NAME;

#$@3NG':Q$_$J$i%a!<%k$rAw?.$9$k(J
if ($action == 1) {
	&data_read;
	if (@DATA >= $max) { pop(@DATA); }
	$record = "DATE=$DATE\tHOST=$hostaddr";
	foreach (sort keys %QUERY) {
		$key = $_;
		$value = $QUERY{$key};
		if ($key && $value) {
			$match = 0;
			foreach $line (@NO_LOG) {
				if ($line eq $key) { $match = 1; last; }
			}
			if (!$match) { $record .= "\t$key\=$value"; }
		}
	}
	$record .= "\n";
	unshift(@DATA,$record);
	&data_save;
	if ($sendmail ne '') {
		$msg ="DATE = $DATE_TIME\n";
		$msg .= "HOST = $hostaddr\n";
		$msg .= "AGENT = $ENV{'HTTP_USER_AGENT'}\n";
		$msg .= "----------------------------------------------------\n\n";
		foreach (0..$count-1) {
			if ($VALUE[$_] ne '') { $msg .= "$NAME[$_] = $VALUE[$_]\n"; }
		}
		$msg .= "----------------------------------------------------\n";
		$msg =~ s/&eq;/=/g;
		&jcode'convert(*subject,'jis');
		&jcode'convert(*msg,'jis');
		if (&sendmail($subject, $email, $mailto, $cc, $bcc, $msg)) { &error(send_mail); }
		else { &error(no_error); }
		exit;
	} else { print "Location: $homepage\n\n"; }
} elsif ($action eq 'vew') {
	&data_read;
	$datacount = @DATA;
	foreach $line (@DATA) {
		@FIELD = split(/\t/,$line);
		foreach $pair (@FIELD) {
			($name, $value) = split(/=/, $pair);
			$name =~ s/&eq;/=/g;
			$value =~ s/&eq;/=/g;
			$value =~ s/\n//g;
			$FIELD_VALUE{"$name\t$value"}++;
		}
	}
	print "Content-type: text/html\n\n";
	print "<html><head><title>access</title></head>\n";
	print "<body bgcolor=#000055 text=#FFFFFF link=#FF0000 vlink=#0000FF>\n";
	print "<div align=center><center>\n";
	if ($max_vew != 0) {
		print "$@>e0L(J $max_vew $@0L$^$GI=(J\$@<($7$F$$$^$9!#(J\n";
	}
	$field_name = '';
	foreach (sort keys %FIELD_VALUE) {
		$key = $_;
		($name, $value) = split(/\t/, $key);
		if ($name ne 'DATE') {
			if ($field_name ne $name) {
				if (@data > 0) { &table_vew($field_name); }
				$field_name = $name;
				$flag = 0; @data = ''; $datamax = 0; $samples = 0;
			}
			$count = $FIELD_VALUE{$key};
			$scount = sprintf("%05d",$count);
			$line = "$scount\t$value\t$count\n";
			push(@data,$line);
			$samples += $count;
			if ($datamax < $count) { $datamax = $count; }
		}
	}
	if (@data > 0) { &table_vew($field_name); }
	print "</center></div>\n";
	print "<p align=right><font size=2><a href=http://www2.inforyoma.or.jp/~terra/>WebHandler Terra</a></font></p>\n";
	print "</body></html>\n";
	exit;
} else {
	print "Content-type: text/html\n\n";
	print "<html><head><title>WebHandler</title></head>\n";
	print "<body bgcolor=#FFFFFF><font size=5>$@FbMF3NG'(J</font>\n";
		print "<div align=center><center>\n";
		print "<form method=post action=webhandler.cgi>\n";
		print "<table border><tr>\n";
			print "<td align=center>$@9`L\(J</td>\n";
			print "<td align=center>$@FbMF(J</td></tr>\n";
			foreach (0..$count-1) {
				if ($VALUE[$_] ne '') {
					print "<input type=hidden name=\"$NAME[$_]\" value=\"$VALUE[$_]\">\n";
					print "<tr><td>$NAME[$_]</td>\n";
					$VALUE[$_] =~ s/\r/<br>/g;
					print "<td>$VALUE[$_]</td></tr>\n";
				}
			}
		print "</table>\n";
	print "<p><input type=hidden name=action value=1>\n";
	print "<input type=submit value='$@3NG'(J OK $@%a!<%k$KAw?.(J'><p>\n";
	print "</form>\n";
	print "<hr><i>$@Aw?.@h!'(J<a href=\"mailto:$mailto\">$mailto</a><i>\n";
	print "</body></html>\n";
	exit;
}
#=======================================================================================
sub table_vew {
	local($title) = $_[0];
	local($value, $gyo, $vew);
	@data = reverse(sort(@data));
	print "<table border=1 width=600><tr><td align=center colspan=5 bgcolor=#000000><font color=#FFFFFF>$title [$samples]</font></td></tr>\n";
	$gyo = 1; $vew = 1;
	foreach $dummy (@data) {
		($s,$value,$c) = split(/\t/,$dummy);
		if ($c != 0) {
			if ($samples == 0) {
				$per = 0;
				$chrlen = 0;
			} else {
				$per = $c / $samples * 100;
				if ($datamax != 0) { $chrlen = int($c / $datamax * $graphvew); }
			}
			print "<tr><td align=center>$vew</td>\n";
			print "<td>",$value,"</td>\n";
			print "<td align=right>$c</td>\n";
			print "<td align=right>\n";
			printf "%10.2f\n" , $per;
			print "\%</td>\n";
			print "<td>";
			if ($chrlen < 1) { print "  "; }
			else {
				print "<img src=bar.gif width=$chrlen height=10>\n";
			}
			print "</td></tr>\n";
			$gyo++;
			if ($max_vew != 0 && $gyo > $max_vew) { last; }
		}
		$vew++;
	}
	print "</table><p>\n";
}
#=======================================================================================
sub sendmail {
	local($subject, $from, $to, $cc, $bcc, $body) = @_;
	local(@TO) = split(/\,/, $to);
	local(@CC) = split(/\,/, $cc);
	local(@BCC) = split(/\,/,$bcc);
	local($attach_file) = '';
	local($mailto) = '';
	local($i);
	$i = 1;
	foreach $ml (@TO, @CC, @BCC) {
		if ($ml =~ /([#-9A-~\-\_]+\@[#-9A-~\-\_\.]+)/) {
			if ($i == 1) { $mailto = "$1"; }
			else { $mailto .= "\,$1"; }
		}
		$i++;
	}
	if (!$mailto) { return(1); }
	if (!open(MAIL,"| $sendmail -t -n -oi $mailto")) { return(1); }
	print MAIL "FormMailer: FormMail v1.5\n";
	if (!$bcc) { print MAIL "To: $to\n"; }
	print MAIL "From: $from\n";
	print MAIL "CC: $cc\n" if $cc;
	print MAIL "Subject: $subject\n";
	print MAIL "Content-Transfer-Encoding: 7bit\n";
	print MAIL "Content-Type: text/plain;\n\n";
	print MAIL $body;
	print MAIL "\n";
	close(MAIL);
	0;
}

#=======================================================================================
sub domain_name {
	local($addr) = $ENV{'REMOTE_ADDR'};
	local($_) = gethostbyaddr(pack("C4",split(/\./,$addr)),2);
	if ($_ eq '') { $_ = $addr; }
	else {
		if (/.+\.(.+)\.(.+)\.(.+)$/) { $_ = "\*\.$1\.$2\.$3"; }
		elsif (/.+\.(.+)\.(.+)$/) { $_ = "\*\.$1\.$2"; }
		elsif (/.+\.(.+)$/) { $_ = "\*\.$1"; }
		else { $_ = "on the internet"; }
	}
	$_;
}
#=======================================================================================
sub data_read {
	if (open(DB,"$datafile")) {
		@DATA = <DB>;
		close(DB);
	}
}
#=======================================================================================
sub data_save {
	$tmpfile = 'webhandler.tmp';
	foreach (1 .. 10) {
		unless (-f $tmpfile) { $tmpflag = 1; last; }
		$tmpflag = 0;
		sleep(1);
	}
	if ($tmpflag == 1) {
		$tmp_dummy = "$$\.tmp";
		if (!open(TMP,">$tmp_dummy")) { &error(bad_tmpfile); }
		close(TMP);
		chmod 0666,$tmp_dummy;
		if (!open(TMP,">$tmp_dummy")) { &error(bad_tmpfile); }
		print TMP @DATA;
		close(TMP);
		foreach (1 .. 10) {
			unless (-f $tmpfile) {
				if (!open(TMP,">$tmpfile")) { &error(bad_tmpfile); }
				close(TMP);
				rename($tmp_dummy,$datafile);
				unlink $tmpfile;
				$tmpflag = 1;
				last;
			}
			$tmpflag = 0;
			sleep(1);
		}
		if (-f $tmp_dummy) { unlink $tmp_dummy; }
	}
	$tmpflag;
}
#=======================================================================================
sub error {
	$error = $_[0];
	if ($error eq 'no_error')	{ $msg = '$@$"$j$,$H$&$4$6$$$^$7$?!#@5>o$KAw?.$7$^$7$?!#(J'; }
	elsif ($error eq 'bad_email')	{ $msg = '<p><b>$@#E#R#R#O#R(J</b></p>$@%a!<%k%"%I%l%9$,IT@5$G$9!#(J'; }
	elsif ($error eq 'bad_data')	{ $msg = '<p><b>$@#E#R#R#O#R(J</b></p>$@:GDc8BEYI,MW$J9`L\$,5-F~$5$l$F$$$^$;$s!#(J'; }
	elsif ($error eq 'send_mail')	{ $msg = '<p><b>$@#E#R#R#O#R(J</b></p>$@%a!<%k$NAw?.$K<:GT$7$^$7$?!#(J'; }
	else { $msg = '<p><b>$@#E#R#R#O#R(J</b></p>$@860xITL@$N%(%i!<$G=hM}$r7QB3$G$-$^$;$s!#(J'; }
	print "Content-type: text/html\n\n";
	print "<html><head><title>formmail</title></head>\n";
	print "<body bgcolor=#FFFFFF>\n";
	print "<div align=center><center>\n";
	print "<p>$@!!(J</p><p>$@!!(J</p>\n";
	print "<table border=5 width =70%><tr>\n";
		print "<td align=center><p>$@!!(J</p>\n";
		print "<p><font size=6><b><i>$msg</i></b></font></p>";
		print "<p><a href=$homepage>[$@%[!<%`$KLa$k(J]</font></a></p><p>$@!!(J</p></td>\n";
	print "</tr></table>\n";
	print "</center></div>\n";
	print "<p align=right><font size=2><a href=http://www2.inforyoma.or.jp/~terra/>WebHandler Terra</a></font></p>\n";
	print "</body></html>\n";
	exit;
}

