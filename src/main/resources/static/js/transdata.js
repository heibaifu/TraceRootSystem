

    var pipestartdata = document.getElementById('pipe.start').innerHTML;
    var pipeenddata = document.getElementById("pipe.end").innerHTML;



    var _parentWin = window.parent
    _parentWin.pipesegment.pipesegmentend.value = pipeenddata;
    _parentWin.pipesegment.pipesegmentstart.value = pipestartdata;