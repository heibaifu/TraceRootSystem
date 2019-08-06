

var pipestartdata = document.getElementById('pipe.start').innerHTML();
var start = pipestartdata.replace("(","");
start = start.replace(")","");
document.getElementById('pipesegmentstart').innerHTML= start;