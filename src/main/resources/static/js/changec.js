function changec(n){
    if (n[0]>0.8) {
   //myChart.setOption({
	echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['red']
        }]
        })
} 
	else if(n[0]>0.1){
	    echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['green']
        }]
        })
	}
	else{
	    echarts.init(document.getElementById('liquid1')).setOption ({
        series:[{
            color:['red']
        }]
        })
	}
    if (n[1]>0.7) {
   //myChart.setOption({
	echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['red']
        }]
        })
} 
	else if(n[1]>0.03){
	    echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['green']
        }]
        })
	}
	else{
	    echarts.init(document.getElementById('liquid2')).setOption ({
        series:[{
            color:['red']
        }]
        })
	}
	if (n[2]>0.6) {
        //myChart.setOption({
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
    else if(n[2]>0.2){
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['green']
            }]
        })
    }
    else{
        echarts.init(document.getElementById('liquid3')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
    if (n[3]>0.9) {
        //myChart.setOption({
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
    else if(n[3]>0.3){
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['green']
            }]
        })
    }
    else{
        echarts.init(document.getElementById('liquid4')).setOption ({
            series:[{
                color:['red']
            }]
        })
    }
}