wIE4=(navigator.appVersion.charAt(0)>=4 && (navigator.appVersion).indexOf("MSIE") != -1);
wNN4=(navigator.appVersion.charAt(0)>=4 && (navigator.appName).indexOf("Netscape") != -1);
idc=0;
function blink_topic_sentence(){ BLINK('ç≈ãﬂÇÃòbëË'); }
function BLINK(bun,time){
	if(bun==null){bun="";}
	if(time==null){time=700;}
	id='BLINK' + idc++;
	if(wNN4){
		document.write('<blink>' + bun + '<\/blink>');
	}else if(wIE4){
		document.write('<span id="' + id + '">' + bun +'<\/span>');
		Show(id,time);
	}else{
		document.write(bun);
	}
}
function Hide(id,time){
	document.all(id).style.visibility='hidden';
	setTimeout('Show("' + id + '",' + time + ')',time);
}
function Show(id,time){
	document.all(id).style.visibility='visible';
	setTimeout('Hide("' + id + '",' + time + ')',time);
}
