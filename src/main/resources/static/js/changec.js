function changec(n){
    if (n[0]>0.8) {
   //myChart.setOption({
	echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['red']
        }]
        })
} 
	else if(n[0]>0.4){
	    echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['blue']
        }]
        })
	}
	else{
	    echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['green']
        }]
        })
	}
    if (n[1]>0.8) {
   //myChart.setOption({
	echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['red']
        }]
        })
} 
	else if(n[1]>0.4){
	    echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['blue']
        }]
        })
	}
	else{
	    echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['green']
        }]
        })
	}
	if (n[2]>0.8) {
        //myChart.setOption({
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
    else if(n[2]>0.4){
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['blue']
            }]
        })
    }
    else{
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['green']
            }]
        })
    }
    if (n[3]>0.8) {
        //myChart.setOption({
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
    else if(n[3]>0.4){
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['blue']
            }]
        })
    }
    else{
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['green']
            }]
        })
    }
}